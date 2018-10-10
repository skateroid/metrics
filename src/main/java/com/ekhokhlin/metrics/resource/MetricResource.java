package com.ekhokhlin.metrics.resource;

import com.ekhokhlin.metrics.dto.MetricCreateRequest;
import com.ekhokhlin.metrics.model.BaseMetric;
import com.ekhokhlin.metrics.service.MetricService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/metrics")
public class MetricResource {
    private MetricService metricService;

    public MetricResource(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("get/{metricName}/{count}")
    public List<BaseMetric> getMetric(@PathVariable(name = "metricName") String metricName,
                                     @PathVariable(name = "count") Integer count) {
        return metricService.getMetric(metricName, count);
    }

    @GetMapping("get/{metricName}")
    public List<BaseMetric> getMetricFixedCount(@PathVariable(name = "metricName") String metricName) {
        return metricService.getMetric(metricName, 3);
    }

    @PostMapping("post")
    public List<BaseMetric> createMetric(@Valid @RequestBody MetricCreateRequest metricCreateRequest) {
        return metricService.createMetric(metricCreateRequest);
    }
}
