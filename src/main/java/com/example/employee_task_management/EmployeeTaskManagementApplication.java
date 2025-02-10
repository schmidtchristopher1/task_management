package com.example.employee_task_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.employee_task_management")
public class EmployeeTaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeTaskManagementApplication.class, args);
        System.out.println("Hello, Employee Task Management!");
    }

}
