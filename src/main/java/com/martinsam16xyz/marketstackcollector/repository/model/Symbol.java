package com.martinsam16xyz.marketstackcollector.repository.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Symbol {
    @Id
    private String id;
    private String symbol;
}
