package com.ekhokhlin.metrics.repository;

import com.ekhokhlin.metrics.entity.HddEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HddRepository extends JpaRepository<HddEntity, Long> {

    List<HddEntity> findAllByOrderByOperationTimestampDesc();
}
