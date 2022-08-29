package com.martinsam16xyz.marketstackcollector.repository.dto;

import lombok.Data;
@Data
public class Historical {
    private String date;
    private Float value;
    private Float min;
    private Float max;
}
