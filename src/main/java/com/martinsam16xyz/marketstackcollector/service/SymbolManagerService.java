package com.martinsam16xyz.marketstackcollector.service;

import com.martinsam16xyz.marketstackcollector.repository.SymbolRepository;
import com.martinsam16xyz.marketstackcollector.repository.model.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SymbolManagerService {

    @Autowired
    private SymbolRepository symbolRepository;

    public Mono<Symbol> save(Symbol symbol){
        return symbolRepository.save(symbol);
    }

    public Flux<Symbol> findAllSymbols(){
        return symbolRepository.findAll();
    }
}
