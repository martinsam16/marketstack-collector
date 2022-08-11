package com.martinsam16xyz.marketstackcollector.model.client;

import lombok.Data;
@Data
public class Pagination {
    private Integer limit;
    private Integer offset;
    private Integer count;
    private Integer total;
}
