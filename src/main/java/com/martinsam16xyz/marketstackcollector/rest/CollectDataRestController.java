package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.job.CollectDataJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
public class CollectDataRestController {

    @Autowired
    CollectDataJob collectDataJob;

    @PostMapping("/job")
    public void runJobOperation(){
        collectDataJob.collectLatestData();
    }

    @PostMapping("/all")
    public void collectAll(){
        collectDataJob.collectAllData();
    }

    @PostMapping("/all/symbols")
    public void collectAllFromCustomSymbols(@RequestParam String symbols){
        collectDataJob.collectAllDataFromCustomSymbols(symbols);
    }
}
