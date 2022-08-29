package com.martinsam16xyz.marketstackcollector.repository.dto;

import lombok.Data;
@Data
public class Historical {
    private String date;
    private Float volume;
    private Float open;
    private Float close;
    private Float min;
    private Float max;
}
