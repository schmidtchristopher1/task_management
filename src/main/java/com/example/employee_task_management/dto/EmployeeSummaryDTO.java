package com.example.employee_task_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSummaryDTO {
    private Long id;
    private String name;
    private String position;
}
