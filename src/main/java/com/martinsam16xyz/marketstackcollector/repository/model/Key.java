package com.martinsam16xyz.marketstackcollector.repository.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class Keys {
    @MongoId
    private String id;
    private String accessKey;
    private Integer usage;
}
