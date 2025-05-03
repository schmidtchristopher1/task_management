package com.example.employee_task_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSummaryDTO {
    private Long id;
    private String title;
    private String deadline;
}
