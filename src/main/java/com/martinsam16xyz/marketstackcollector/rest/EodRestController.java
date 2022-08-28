package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.repository.EodRepository;
import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/eod")
public class EodRestController {

    @Autowired
    private EodRepository eodRepository;

    @GetMapping
    public Flux<Eod> all(@RequestParam int page,
                         @RequestParam int size,
                         @RequestParam(required = false) String symbol,
                         @RequestParam(required = false) String date,
                         @RequestParam(required = false) String exchange) {
        Pageable pageable = PageRequest.of(page, size);
        if (symbol != null && date != null && exchange != null) {
            return eodRepository.findAllByIdNotNullAndSymbolAndDateStartingWithAndExchange(symbol, date, exchange, pageable);
        } else if (symbol != null && date != null) {
            return eodRepository.findAllByIdNotNullAndSymbolAndDateStartingWith(symbol, date, pageable);
        } else if (symbol != null && exchange != null) {
            return eodRepository.findAllByIdNotNullAndSymbolAndExchange(symbol, exchange, pageable);
        } else if (exchange != null && date != null) {
            return eodRepository.findAllByIdNotNullAndExchangeAndDateStartingWith(exchange, date, pageable);
        } else if (symbol != null) {
            return eodRepository.findAllByIdNotNullAndSymbol(symbol, pageable);
        } else if (date != null) {
            return eodRepository.findAllByIdNotNullAndDateStartingWith(date, pageable);
        } else if (exchange != null) {
            return eodRepository.findAllByIdNotNullAndExchange(exchange, pageable);
        }
        return eodRepository.findAllByIdNotNull(pageable);

    }

}
