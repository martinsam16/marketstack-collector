package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.repository.model.Symbol;
import com.martinsam16xyz.marketstackcollector.service.SymbolManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/symbols")
public class SymbolRestController {

    @Autowired
    private SymbolManagerService symbolManagerService;

    @PostMapping
    public Mono<Symbol> save(@RequestBody Symbol symbol) {
        return symbolManagerService.save(symbol);
    }

    @GetMapping
    public Flux<Symbol> all() {
        return symbolManagerService.findAllSymbols();
    }
}
