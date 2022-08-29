package com.martinsam16xyz.marketstackcollector.rest;

import com.martinsam16xyz.marketstackcollector.collector.CollectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
public class CollectDataRestController {

    @Autowired
    CollectData collectData;

    @PostMapping("/job")
    public void runJobOperation(){
        collectData.collectLatestData();
    }

    @PostMapping("/all")
    public void collectAll(){
        collectData.collectAllData();
    }

    @PostMapping("/all/symbols")
    public void collectAllFromCustomSymbols(@RequestParam String symbols){
        collectData.collectAllDataFromCustomSymbols(symbols);
    }
}
