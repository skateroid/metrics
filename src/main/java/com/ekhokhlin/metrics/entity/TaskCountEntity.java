package com.ekhokhlin.metrics.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "task_count")
public class TaskCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskCountIdGenerator")
    @SequenceGenerator(name = "TaskCountIdGenerator", sequenceName = "task_count_id_seq")
    private Long id;

    private String name;
    private Double value;
    private String status;
    private String params;
    private OffsetDateTime timestamp;
    private OffsetDateTime requestTimestamp;
    private OffsetDateTime operationTimestamp;
}
