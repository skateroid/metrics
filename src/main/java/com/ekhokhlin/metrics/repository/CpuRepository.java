package com.ekhokhlin.metrics.repository;

import com.ekhokhlin.metrics.entity.CpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {

    List<CpuEntity> findAllByOrderByOperationTimestampDesc();
}
