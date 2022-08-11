package com.martinsam16xyz.marketstackcollector.client.model;

@lombok.Data
public class Data {
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
