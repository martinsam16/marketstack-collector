package com.martinsam16xyz.marketstackcollector.repository;

import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EodRepository extends ReactiveMongoRepository<Eod, String> {
    Flux<Eod> findByDate(String date);
}
