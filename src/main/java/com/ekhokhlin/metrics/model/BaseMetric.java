package com.ekhokhlin.metrics.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BaseMetric {

    private String name;
    private Double value;
    private String status;
    private String params;
    private OffsetDateTime timestamp;
    private OffsetDateTime requestTimestamp;
    private OffsetDateTime operationTimestamp;
}
