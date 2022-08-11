package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.repository.model.Key;
import com.martinsam16xyz.marketstackcollector.service.KeyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/access-key")
public class KeysRestController {

    @Autowired
    private KeyManagerService keyManagerService;

    @PostMapping
    public Mono<Key> save(@RequestBody Key key){
        return keyManagerService.save(key);
    }
}
