-- Delete database if it exists
DROP
DATABASE IF EXISTS employee_tasks_db;

-- Create database
CREATE
DATABASE employee_tasks_db;
USE
employee_tasks_db;

-- Drop tables if they exist (to reset the database)
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS employees;

-- Create Employees table
CREATE TABLE employees
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    email    VARCHAR(191) UNIQUE NOT NULL,
    position VARCHAR(100)        NOT NULL
);

-- Create Tasks table
CREATE TABLE tasks
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    deadline    DATE         NOT NULL
);

-- Create Assignments table (Many-to-Many relationship)
CREATE TABLE assignments
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    task_id     BIGINT NOT NULL,
    status      ENUM ('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);

-- Insert sample employees
INSERT INTO employees (name, email, position)
VALUES ('Alice Johnson', 'alice@example.com', 'Software Developer'),
       ('Bob Smith', 'bob@example.com', 'Project Manager'),
       ('Charlie Brown', 'charlie@example.com', 'QA Engineer');

-- Insert sample tasks
INSERT INTO tasks (title, description, deadline)
VALUES ('Develop API', 'Create RESTful APIs for the project', '2024-02-10'),
       ('Test Application', 'Perform integration and unit testing', '2024-02-15'),
       ('Write Documentation', 'Prepare system documentation for developers', '2024-02-20');

-- Assign employees to tasks
INSERT INTO assignments (employee_id, task_id, status)
VALUES (1, 1, 'In Progress'),
       (2, 1, 'Pending'),
       (3, 2, 'Pending'),
       (1, 3, 'Pending'),
       (2, 3, 'Completed');