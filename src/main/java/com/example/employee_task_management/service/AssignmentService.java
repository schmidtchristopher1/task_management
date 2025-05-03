package com.example.employee_task_management.service;

import com.example.employee_task_management.dto.AssignmentResponseDTO;
import com.example.employee_task_management.dto.EmployeeSummaryDTO;
import com.example.employee_task_management.dto.TaskSummaryDTO;
import com.example.employee_task_management.model.Assignment;
import com.example.employee_task_management.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private AssignmentResponseDTO mapToDto(Assignment assignment) {
        EmployeeSummaryDTO employeeDto = new EmployeeSummaryDTO(
                assignment.getEmployee().getId(),
                assignment.getEmployee().getName(),
                assignment.getEmployee().getPosition()
        );
        TaskSummaryDTO taskDto = new TaskSummaryDTO(
                assignment.getTask().getId(),
                assignment.getTask().getTitle(),
                assignment.getTask().getDeadline() != null ? assignment.getTask().getDeadline().toString() : null
        );
        return new AssignmentResponseDTO(
                assignment.getId(),
                employeeDto,
                taskDto,
                assignment.getStatus()
        );
    }

    public List<AssignmentResponseDTO> getAllAssignmentsDto() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<AssignmentResponseDTO> getAssignmentByIdDto(Long id) {
        return assignmentRepository.findById(id)
                .map(this::mapToDto);
    }

    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}