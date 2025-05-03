package com.example.employee_task_management.controller;

import com.example.employee_task_management.dto.AssignmentRequestDTO;
import com.example.employee_task_management.dto.AssignmentResponseDTO;
import com.example.employee_task_management.dto.AssignmentStatusUpdateDTO;
import com.example.employee_task_management.dto.EmployeeSummaryDTO;
import com.example.employee_task_management.dto.TaskSummaryDTO;
import com.example.employee_task_management.model.Assignment;
import com.example.employee_task_management.model.Employee;
import com.example.employee_task_management.model.Task;
import com.example.employee_task_management.service.AssignmentService;
import com.example.employee_task_management.service.EmployeeService;
import com.example.employee_task_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final EmployeeService employeeService;
    private final TaskService taskService;

    @GetMapping
    public List<AssignmentResponseDTO> getAllAssignments() {
        return assignmentService.getAllAssignmentsDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<AssignmentResponseDTO> createAssignment(@RequestBody AssignmentRequestDTO requestDTO) {
        Employee employee = employeeService.getEmployeeById(requestDTO.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        Task task = taskService.getTaskById(requestDTO.getTaskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        Assignment newAssignment = new Assignment();
        newAssignment.setEmployee(employee);
        newAssignment.setTask(task);
        newAssignment.setStatus(requestDTO.getStatus());

        Assignment savedAssignment = assignmentService.saveAssignment(newAssignment);

        AssignmentResponseDTO responseDto = new AssignmentResponseDTO(
                savedAssignment.getId(),
                new EmployeeSummaryDTO(employee.getId(), employee.getName(), employee.getPosition()),
                new TaskSummaryDTO(task.getId(), task.getTitle(), task.getDeadline() != null ? task.getDeadline().toString() : null),
                savedAssignment.getStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssignmentResponseDTO> updateAssignmentStatus(@PathVariable Long id,
            @RequestBody AssignmentStatusUpdateDTO statusUpdateDTO) {
        return assignmentService.getAssignmentById(id)
                .map(existingAssignment -> {
                    existingAssignment.setStatus(statusUpdateDTO.getStatus());
                    Assignment updatedAssignment = assignmentService.saveAssignment(existingAssignment);

                    AssignmentResponseDTO responseDto = new AssignmentResponseDTO(
                            updatedAssignment.getId(),
                            new EmployeeSummaryDTO(
                                    updatedAssignment.getEmployee().getId(),
                                    updatedAssignment.getEmployee().getName(),
                                    updatedAssignment.getEmployee().getPosition()
                            ),
                            new TaskSummaryDTO(
                                    updatedAssignment.getTask().getId(),
                                    updatedAssignment.getTask().getTitle(),
                                    updatedAssignment.getTask().getDeadline() != null ? updatedAssignment.getTask().getDeadline().toString() : null
                            ),
                            updatedAssignment.getStatus()
                    );
                    return ResponseEntity.ok(responseDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        if (assignmentService.getAssignmentById(id).isPresent()) { // Check existence using entity method
            assignmentService.deleteAssignment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}