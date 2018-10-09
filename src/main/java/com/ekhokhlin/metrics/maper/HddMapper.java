package com.ekhokhlin.metrics.maper;

import com.ekhokhlin.metrics.dto.BaseMetricCreateRequest;
import com.ekhokhlin.metrics.dto.HddCreateRequest;
import com.ekhokhlin.metrics.entity.HddEntity;
import com.ekhokhlin.metrics.model.Hdd;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HddMapper {

    HddEntity mapToEntity(HddCreateRequest hddCreateRequest);

    List<HddEntity> mapToEntities(List<HddCreateRequest> hddCreateRequests);

    List<Hdd> mapEntitiesToModels(List<HddEntity> hddEntities);

    List<HddCreateRequest> baseToHddRequests(List<BaseMetricCreateRequest> baseMetricCreateRequests);
}
