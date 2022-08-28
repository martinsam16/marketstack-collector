package com.martinsam16xyz.marketstackcollector.repository;

import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

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

}
