package com.example.employee_task_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Task cannot be null")
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference("task-comments")
    private Task task;

    @NotNull(message = "Employee cannot be null")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference("employee-comments")
    private Employee employee;

    @NotBlank(message = "Content cannot be blank")
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
