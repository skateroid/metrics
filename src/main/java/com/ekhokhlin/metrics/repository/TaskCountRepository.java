package com.ekhokhlin.metrics.repository;

import com.ekhokhlin.metrics.entity.TaskCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCountRepository extends JpaRepository<TaskCountEntity, Long> {

    List<TaskCountEntity> findAllByOrderByOperationTimestampDesc();
}
