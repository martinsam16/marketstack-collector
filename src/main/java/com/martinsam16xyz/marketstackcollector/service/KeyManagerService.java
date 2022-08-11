package com.martinsam16xyz.marketstackcollector.service;

import com.martinsam16xyz.marketstackcollector.repository.KeyManagerRepository;
import com.martinsam16xyz.marketstackcollector.repository.model.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class KeyManagerService {

    @Autowired
    private KeyManagerRepository keyManagerRepository;

    public Mono<Key> getkeyWithMinorUsage(){
        return keyManagerRepository.findAllByOrderByUsageAsc().next();
    }

    public Mono<Key> save(Key key){
        return keyManagerRepository.save(key);
    }
}
