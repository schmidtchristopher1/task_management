package com.example.employee_task_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_task_management.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
