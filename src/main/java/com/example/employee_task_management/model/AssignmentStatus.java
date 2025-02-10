package com.example.employee_task_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AssignmentStatus {
    @JsonProperty("PENDING")
    PENDING,

    @JsonProperty("IN_PROGRESS")
    IN_PROGRESS,

    @JsonProperty("COMPLETED")
    COMPLETED
}
