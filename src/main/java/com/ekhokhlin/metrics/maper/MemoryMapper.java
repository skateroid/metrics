package com.ekhokhlin.metrics.maper;

import com.ekhokhlin.metrics.dto.BaseMetricCreateRequest;
import com.ekhokhlin.metrics.dto.MemoryCreateRequest;
import com.ekhokhlin.metrics.entity.MemoryEntity;
import com.ekhokhlin.metrics.model.Memory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoryMapper {

    MemoryEntity mapToEntity(MemoryCreateRequest memoryCreateRequest);

    List<MemoryEntity> mapToEntities(List<MemoryCreateRequest> memoryCreateRequests);

    List<Memory> mapEntitiesToModels(List<MemoryEntity> memoryEntities);

    List<MemoryCreateRequest> baseToMemoryRequests(List<BaseMetricCreateRequest> baseMetricCreateRequests);
}
