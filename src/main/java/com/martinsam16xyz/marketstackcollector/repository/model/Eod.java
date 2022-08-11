package com.martinsam16xyz.marketstackcollector.repository.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class Eod {
    @MongoId
    private String id;
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float volume;
    private Float adj_high;
    private Float adj_low;
    private Float adj_close;
    private Float adj_open;
    private Float adj_volume;
    private Float split_factor;
    private Float dividend;
    private String symbol;
    private String exchange;
    private String date;
}
