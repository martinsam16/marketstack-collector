package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.repository.EodRepository;
import com.martinsam16xyz.marketstackcollector.repository.model.Eod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/eod")
public class EodRestController {

    @Autowired
    private EodRepository eodRepository;

    @GetMapping
    public Flux<Eod> all(@RequestParam int page,
                         @RequestParam int size,
                         @RequestParam(required = false) String symbol) {
        Pageable pageable = PageRequest.of(page, size);
        if (symbol == null || symbol.isBlank())
            return eodRepository.findAllByIdNotNull(pageable);

        return eodRepository.findAllByIdNotNullAndSymbol(symbol, pageable);
    }

}
