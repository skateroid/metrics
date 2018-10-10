package com.ekhokhlin.metrics.service;

import com.ekhokhlin.metrics.MetricNames;
import com.ekhokhlin.metrics.Status;
import com.ekhokhlin.metrics.dto.*;
import com.ekhokhlin.metrics.entity.*;
import com.ekhokhlin.metrics.exception.BadRequestException;
import com.ekhokhlin.metrics.maper.*;
import com.ekhokhlin.metrics.model.*;
import com.ekhokhlin.metrics.repository.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.min;

@Service
public class MetricService {

    private CpuRepository cpuRepository;
    private MemoryRepository memoryRepository;
    private HddRepository hddRepository;
    private SsdRepository ssdRepository;
    private TaskCountRepository taskCountRepository;

    private CpuMapper cpuMapper;
    private MemoryMapper memoryMapper;
    private HddMapper hddMapper;
    private SsdMapper ssdMapper;
    private TaskCountMapper taskCountMapper;

    public MetricService(CpuRepository cpuRepository, MemoryRepository memoryRepository, HddRepository hddRepository,
                         SsdRepository ssdRepository, TaskCountRepository taskCountRepository, CpuMapper cpuMapper,
                         MemoryMapper memoryMapper, HddMapper hddMapper, SsdMapper ssdMapper,
                         TaskCountMapper taskCountMapper) {

        this.cpuRepository = cpuRepository;
        this.memoryRepository = memoryRepository;
        this.hddRepository = hddRepository;
        this.ssdRepository = ssdRepository;
        this.taskCountRepository = taskCountRepository;
        this.cpuMapper = cpuMapper;
        this.memoryMapper = memoryMapper;
        this.hddMapper = hddMapper;
        this.ssdMapper = ssdMapper;
        this.taskCountMapper = taskCountMapper;
    }

    public List<BaseMetric> createMetric(MetricCreateRequest metricCreateRequest) {
        List<BaseMetricCreateRequest> cpuBaseMetricCreateRequests = metricCreateRequest.getMetrics().stream()
                .filter(it -> it.getName().equals("cpu"))
                .collect(Collectors.toList());
        List<CpuCreateRequest> cpuCreateRequests = cpuMapper.baseToCpuRequests(cpuBaseMetricCreateRequests);

        List<BaseMetricCreateRequest> memoryBaseMetricCreateRequests = metricCreateRequest.getMetrics().stream()
                .filter(it -> it.getName().equals("memory"))
                .collect(Collectors.toList());
        List<MemoryCreateRequest> memoryCreateRequests = memoryMapper
                .baseToMemoryRequests(memoryBaseMetricCreateRequests);

        List<BaseMetricCreateRequest> hddBaseMetricCreateRequests = metricCreateRequest.getMetrics().stream()
                .filter(it -> it.getName().equals("hdd"))
                .collect(Collectors.toList());
        List<HddCreateRequest> hddCreateRequests = hddMapper.baseToHddRequests(hddBaseMetricCreateRequests);

        List<BaseMetricCreateRequest> ssdBaseMetricCreateRequests = metricCreateRequest.getMetrics().stream()
                .filter(it -> it.getName().equals("ssd"))
                .collect(Collectors.toList());
        List<SsdCreateRequest> ssdCreateRequests = ssdMapper.baseToSsdRequests(ssdBaseMetricCreateRequests);

        List<BaseMetricCreateRequest> taskCountBaseMetricCreateRequests = metricCreateRequest.getMetrics().stream()
                .filter(it -> it.getName().equals("taskCount"))
                .collect(Collectors.toList());
        List<TaskCountCreateRequest> taskCountCreateRequests = taskCountMapper
                .baseToTaskCountRequest(taskCountBaseMetricCreateRequests);

        List<CpuEntity> cpuEntities = cpuMapper.mapToEntities(cpuCreateRequests);
        cpuEntities.forEach(it -> {
            it.setOperationTimestamp(OffsetDateTime.now());
            if (it.getStatus() == null) {
                it.setStatus(Status.UP.name());
            }
        });

        List<MemoryEntity> memoryEntities = memoryMapper.mapToEntities(memoryCreateRequests);
        memoryEntities.forEach(it -> {
            it.setOperationTimestamp(OffsetDateTime.now());
            if (it.getStatus() == null) {
                it.setStatus(Status.UP.name());
            }
        });

        List<HddEntity> hddEntities = hddMapper.mapToEntities(hddCreateRequests);
        hddEntities.forEach(it -> {
            it.setOperationTimestamp(OffsetDateTime.now());
            if (it.getStatus() == null) {
                it.setStatus(Status.UP.name());
            }
        });

        List<SsdEntity> ssdEntities = ssdMapper.mapToEntities(ssdCreateRequests);
        ssdEntities.forEach(it -> {
            it.setOperationTimestamp(OffsetDateTime.now());
            if (it.getStatus() == null) {
                it.setStatus(Status.UP.name());
            }
        });

        List<TaskCountEntity> taskCountEntities = taskCountMapper.mapToEntities(taskCountCreateRequests);
        taskCountEntities.forEach(it -> {
            it.setOperationTimestamp(OffsetDateTime.now());
            if (it.getStatus() == null) {
                it.setStatus(Status.UP.name());
            }
        });

        List<Long> cpuIds = cpuRepository.saveAll(cpuEntities).stream()
                .map(CpuEntity::getId)
                .collect(Collectors.toList());
        List<Long> memoryIds = memoryRepository.saveAll(memoryEntities).stream()
                .map(MemoryEntity::getId)
                .collect(Collectors.toList());
        List<Long> hddIds = hddRepository.saveAll(hddEntities).stream()
                .map(HddEntity::getId)
                .collect(Collectors.toList());
        List<Long> ssdIds = ssdRepository.saveAll(ssdEntities).stream()
                .map(SsdEntity::getId)
                .collect(Collectors.toList());
        List<Long> taskCountIds = taskCountRepository.saveAll(taskCountEntities).stream()
                .map(TaskCountEntity::getId)
                .collect(Collectors.toList());

        List<BaseMetric> createdMetrics = new ArrayList<>();
        createdMetrics.addAll(cpuMapper.mapEntitiesToModels(cpuRepository.findAllById(cpuIds)));
        createdMetrics.addAll(memoryMapper.mapEntitiesToModels(memoryRepository.findAllById(memoryIds)));
        createdMetrics.addAll(hddMapper.mapEntitiesToModels(hddRepository.findAllById(hddIds)));
        createdMetrics.addAll(ssdMapper.mapEntitiesToModels(ssdRepository.findAllById(ssdIds)));
        createdMetrics.addAll(taskCountMapper.mapEntitiesToModels(taskCountRepository.findAllById(taskCountIds)));

        return createdMetrics;
    }

    public List<BaseMetric> getMetric(String metricName, Integer count) {
        List<String> metricNames = Arrays.stream(MetricNames.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if (!metricNames.contains(metricName.toUpperCase()) && !metricName.equalsIgnoreCase("all")) {
            throw new BadRequestException(String.format("Metric with name: %s not found", metricName));
        }
        if (!metricName.equalsIgnoreCase("all")) {
            switch (metricName.toUpperCase()) {
                case "CPU": {
                    List<BaseMetric> cpuMetrics = new ArrayList<>(cpuMapper.mapEntitiesToModels(
                            cpuRepository.findAllByOrderByOperationTimestampDesc()));
                    return cpuMetrics.subList(0, min(cpuMetrics.size(), count));
                }
                case "MEMORY": {
                    List<BaseMetric> memoryMetrics = new ArrayList<>(memoryMapper.mapEntitiesToModels(
                            memoryRepository.findAllByOrderByOperationTimestampDesc()));
                    return memoryMetrics.subList(0, min(memoryMetrics.size(), count));
                }
                case "HDD": {
                    List<BaseMetric> hddMetrics = new ArrayList<>(hddMapper.mapEntitiesToModels(
                            hddRepository.findAllByOrderByOperationTimestampDesc()));
                    return hddMetrics.subList(0, min(hddMetrics.size(), count));
                }
                case "SSD": {
                    List<BaseMetric> ssdMetrics = new ArrayList<>(ssdMapper.mapEntitiesToModels(
                            ssdRepository.findAllByOrderByOperationTimestampDesc()));
                    return ssdMetrics.subList(0, min(ssdMetrics.size(), count));
                }
                case "TASKCOUNT": {
                    List<BaseMetric> taskCountMetrics = new ArrayList<>(taskCountMapper.mapEntitiesToModels(
                            taskCountRepository.findAllByOrderByOperationTimestampDesc()));
                    return taskCountMetrics.subList(0, min(taskCountMetrics.size(), count));
                }
            }
        } else {
            return getAllMetrics(count);
        }
        return null;
    }

    private List<BaseMetric> getAllMetrics(Integer count) {
        List<Cpu> cpuMetrics = cpuMapper.mapEntitiesToModels(cpuRepository
                .findAllByOrderByOperationTimestampDesc());
        List<BaseMetric> allMetrics = new ArrayList<>(cpuMetrics.subList(0, min(cpuMetrics.size(), count)));

        List<Memory> memoryMetrics = memoryMapper.mapEntitiesToModels(memoryRepository
                .findAllByOrderByOperationTimestampDesc());
        allMetrics.addAll(memoryMetrics.subList(0, min(memoryMetrics.size(), count)));

        List<Hdd> hddMetrics = hddMapper.mapEntitiesToModels(hddRepository
                .findAllByOrderByOperationTimestampDesc());
        allMetrics.addAll(hddMetrics.subList(0, min(hddMetrics.size(), count)));

        List<Ssd> ssdMetrics = ssdMapper.mapEntitiesToModels(ssdRepository
                .findAllByOrderByOperationTimestampDesc());
        allMetrics.addAll(ssdMetrics.subList(0, min(ssdMetrics.size(), count)));

        List<TaskCount> taskCountMetrics = taskCountMapper.mapEntitiesToModels(taskCountRepository
                .findAllByOrderByOperationTimestampDesc());
        allMetrics.addAll(taskCountMetrics.subList(0, min(taskCountMetrics.size(), count)));

        return allMetrics;
    }
}
