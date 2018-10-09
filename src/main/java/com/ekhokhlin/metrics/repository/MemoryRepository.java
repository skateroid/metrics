package com.ekhokhlin.metrics.repository;

import com.ekhokhlin.metrics.entity.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<MemoryEntity, Long> {

    List<MemoryEntity> findAllByOrderByOperationTimestampDesc();
}
