package com.ekhokhlin.metrics.maper;

import com.ekhokhlin.metrics.dto.BaseMetricCreateRequest;
import com.ekhokhlin.metrics.dto.CpuCreateRequest;
import com.ekhokhlin.metrics.entity.CpuEntity;
import com.ekhokhlin.metrics.model.Cpu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CpuMapper {

    CpuEntity mapToEntity(CpuCreateRequest cpuCreateRequest);

    List<CpuEntity> mapToEntities(List<CpuCreateRequest> cpuCreateRequests);

    List<Cpu> mapEntitiesToModels(List<CpuEntity> cpuEntities);

    List<CpuCreateRequest> baseToCpuRequests(List<BaseMetricCreateRequest> baseMetricCreateRequests);
}
