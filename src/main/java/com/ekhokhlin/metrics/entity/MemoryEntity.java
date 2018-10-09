package com.ekhokhlin.metrics.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "memory")
public class MemoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MemoryIdGenerator")
    @SequenceGenerator(name = "MemoryIdGenerator", sequenceName = "memory_id_seq")
    private Long id;

    private String name;
    private Double value;
    private String status;
    private String params;
    private OffsetDateTime timestamp;
    private OffsetDateTime requestTimestamp;
    private OffsetDateTime operationTimestamp;
}
