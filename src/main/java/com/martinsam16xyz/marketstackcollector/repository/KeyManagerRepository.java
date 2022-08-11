package com.martinsam16xyz.marketstackcollector.repository;

import com.martinsam16xyz.marketstackcollector.repository.model.Key;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface KeyManagerRepository extends ReactiveMongoRepository<Key, String> {
    Flux<Key> findAllByOrderByUsageAsc();
}
