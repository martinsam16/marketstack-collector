package com.martinsam16xyz.marketstackcollector.repository;

import com.martinsam16xyz.marketstackcollector.repository.dto.SerieDto;
import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EodRepository extends ReactiveMongoRepository<Eod, String> {

    Flux<Eod> findAllByIdNotNull(final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndDateStartingWith(String date, final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndSymbol(String symbol, final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndExchange(String exchange, final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndExchangeAndDateStartingWith(String exchange, String date, final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndSymbolAndDateStartingWith(String symbol, String date, final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndSymbolAndExchange(String symbol, String exchange, final Pageable pageable);

    Flux<Eod> findAllByIdNotNullAndSymbolAndDateStartingWithAndExchange(String symbol, String date, String exchange, final Pageable pageable);

    Mono<Boolean> existsByDateAndSymbolAndExchange(String date, String symbol, String exchange);

    @Aggregation(pipeline = {
            "{$sort: {date:-1}}",
            """
                    {$group: {
                                _id: '$symbol',
                                historical: {
                                    $push: {
                                        date: '$date',
                                        close: '$close'
                                    }
                    }}}""",
            """
                    {$project: {
                                    _id: 0,
                                    symbol: '$_id',
                                    historical: '$historical'
                                }}"""
    })
    Flux<SerieDto> getHistoricalData();
}
