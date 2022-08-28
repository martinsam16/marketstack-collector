package com.martinsam16xyz.marketstackcollector.job;

import com.martinsam16xyz.marketstackcollector.client.feign.MarketstackApi;
import com.martinsam16xyz.marketstackcollector.client.model.MarketstackResponse;
import com.martinsam16xyz.marketstackcollector.repository.EodRepository;
import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import com.martinsam16xyz.marketstackcollector.repository.model.Key;
import com.martinsam16xyz.marketstackcollector.repository.model.Symbol;
import com.martinsam16xyz.marketstackcollector.service.KeyManagerService;
import com.martinsam16xyz.marketstackcollector.service.SymbolManagerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class CollectDataJob {

    @Autowired
    private EodRepository eodRepository;

    @Autowired
    private KeyManagerService keyManagerService;

    @Autowired
    private SymbolManagerService symbolManagerService;

    @Autowired
    private MarketstackApi marketstackApi;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Scheduled(cron = "0 0 18 * * MON-FRI", zone = "America/Lima")
    public void collectDaily() throws InterruptedException {
        log.info("Ejecutando daily collect..");
        this.collectLatestData();
    }

    public void collectLatestData() {
        log.info("collectLatestData()");
        symbolManagerService.findAllSymbols()
                .map(Symbol::getSymbol)
                .reduce((s, s2) -> s + "," + s2)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(symbols -> {
                    keyManagerService.getkeyWithMinorUsage()
                            .publishOn(Schedulers.boundedElastic())
                            .doOnNext(key -> {
                                marketstackApi.getLatestEod(symbols, key.getAccessKey())
                                        .doOnNext(this::saveData)
                                        .doOnSuccess(response -> {
                                            this.updateQuota(key, symbols);
                                        })
                                        .subscribe();
                            })
                            .subscribe();
                })
                .subscribe();
    }

    public void collectAllData() {
        log.info("collectAllData()");
        symbolManagerService.findAllSymbols()
                .map(Symbol::getSymbol)
                .reduce((s, s2) -> s + "," + s2)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(symbols -> {
                    keyManagerService.getkeyWithMinorUsage()
                            .publishOn(Schedulers.boundedElastic())
                            .doOnNext(key -> {
                                marketstackApi.getAll(symbols, key.getAccessKey(), 1000)
                                        .doOnNext(this::saveData)
                                        .doOnSuccess(response -> {
                                            this.updateQuota(key, symbols);
                                        })
                                        .subscribe();
                            })
                            .subscribe();
                })
                .subscribe();
    }

    public void collectAllDataFromCustomSymbols(String symbols) {
        log.info("collectAllDataFromCustomSymbols()");
        keyManagerService.getkeyWithMinorUsage()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(key -> {
                    marketstackApi.getAll(symbols, key.getAccessKey(), 1000)
                            .doOnNext(this::saveData)
                            .doOnSuccess(response -> {
                                this.updateQuota(key, symbols);
                            })
                            .subscribe();
                })
                .subscribe();
    }

    private void saveData(MarketstackResponse response) {
        response.getData().forEach(data -> {
            Eod eod = modelMapper.map(data, Eod.class);
            eodRepository.save(eod)
                    .publishOn(Schedulers.boundedElastic())
                    .doOnNext(saved -> {
                        log.info("SYMBOL: " + saved.getSymbol() + ", DATE: " + saved.getDate());
                    })
                    .subscribe();
        });
    }

    private void updateQuota(Key key, String symbols) {
        key.setUsage(key.getUsage() + symbols.split(",").length);
        keyManagerService.save(key)
                .doOnSuccess(success -> {
                    log.warn("key usage updated to: " + success.getUsage());
                })
                .subscribe();
    }
}
