package com.martinsam16xyz.marketstackcollector.repository;

import com.martinsam16xyz.marketstackcollector.repository.model.Symbol;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends ReactiveMongoRepository<Symbol, String> {
}
