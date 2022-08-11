package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.repository.EodRepository;
import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/eod")
public class EodRestController {

    @Autowired
    private EodRepository eodRepository;

    @GetMapping
    public Flux<Eod> all() {
        return eodRepository.findAll();
    }

}
