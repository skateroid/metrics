package com.ekhokhlin.metrics.maper;

import com.ekhokhlin.metrics.dto.BaseMetricCreateRequest;
import com.ekhokhlin.metrics.dto.SsdCreateRequest;
import com.ekhokhlin.metrics.entity.SsdEntity;
import com.ekhokhlin.metrics.model.Ssd;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SsdMapper {

    SsdEntity mapToEntity(SsdCreateRequest ssdCreateRequest);

    List<SsdEntity> mapToEntities(List<SsdCreateRequest> ssdCreateRequests);

    List<Ssd> mapEntitiesToModels(List<SsdEntity> ssdEntities);

    List<SsdCreateRequest> baseToSsdRequests(List<BaseMetricCreateRequest> baseMetricCreateRequests);
}
