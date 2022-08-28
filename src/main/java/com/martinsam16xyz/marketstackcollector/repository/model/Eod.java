package com.martinsam16xyz.marketstackcollector.repository.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Eod {
    @Id
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
    @Indexed
    private String symbol;
    @Indexed
    private String exchange;
    @Indexed
    private String date;
}
