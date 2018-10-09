package com.ekhokhlin.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseMetricCreateRequest {

    private String name;
    private Double value;
    private String status;
    private String params;
    private OffsetDateTime timestamp;
    private OffsetDateTime requestTimestamp;
}
