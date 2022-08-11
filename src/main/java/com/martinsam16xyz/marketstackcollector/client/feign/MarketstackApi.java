package com.martinsam16xyz.marketstackcollector.client.feign;

import com.martinsam16xyz.marketstackcollector.client.model.MarketstackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "marketstack", url = "${marketstack.api.url}")
public interface MarketstackApi {

    @GetMapping("/latest")
    Mono<MarketstackResponse> getLatestEod(@RequestParam("symbols") String symbols,
                                           @RequestParam(value = "access_key") String accessKey);
}
