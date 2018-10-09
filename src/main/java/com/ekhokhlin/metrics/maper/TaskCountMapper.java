package com.ekhokhlin.metrics.maper;

import com.ekhokhlin.metrics.dto.BaseMetricCreateRequest;
import com.ekhokhlin.metrics.dto.TaskCountCreateRequest;
import com.ekhokhlin.metrics.entity.TaskCountEntity;
import com.ekhokhlin.metrics.model.TaskCount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskCountMapper {

    TaskCountEntity mapToEntity(TaskCountCreateRequest taskCountCreateRequest);

    List<TaskCountEntity> mapToEntities(List<TaskCountCreateRequest> taskCountCreateRequests);

    List<TaskCount> mapEntitiesToModels(List<TaskCountEntity> taskCountEntities);

    List<TaskCountCreateRequest> baseToTaskCountRequest(List<BaseMetricCreateRequest> baseMetricCreateRequests);
}
