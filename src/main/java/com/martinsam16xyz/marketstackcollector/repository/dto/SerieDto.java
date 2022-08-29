package com.martinsam16xyz.marketstackcollector.repository.dto;

import lombok.Data;

import java.util.List;

@Data
public class SerieDto {
    private String symbol;
    private List<Historical> historical;
}
