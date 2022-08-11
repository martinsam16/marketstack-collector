package com.martinsam16xyz.marketstackcollector.client.model;

import java.util.List;
@lombok.Data
public class MarketstackResponse {
    private Pagination pagination;
    private List<Data> data;
}
