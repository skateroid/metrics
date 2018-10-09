package com.ekhokhlin.metrics.repository;

import com.ekhokhlin.metrics.entity.SsdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SsdRepository extends JpaRepository<SsdEntity, Long> {

    List<SsdEntity> findAllByOrderByOperationTimestampDesc();
}
