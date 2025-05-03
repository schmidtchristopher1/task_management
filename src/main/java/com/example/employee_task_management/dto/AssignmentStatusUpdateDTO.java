package com.example.employee_task_management.dto;

import com.example.employee_task_management.model.AssignmentStatus;
import lombok.Data;

@Data
public class AssignmentStatusUpdateDTO {
    private AssignmentStatus status;
}
