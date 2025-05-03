package com.example.employee_task_management.dto;

import com.example.employee_task_management.model.AssignmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentResponseDTO {
    private Long id;
    private EmployeeSummaryDTO employee;
    private TaskSummaryDTO task;
    private AssignmentStatus status;
}
