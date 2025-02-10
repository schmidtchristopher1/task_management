-- Create database
CREATE DATABASE IF NOT EXISTS employee_tasks_db;
USE employee_tasks_db;

-- Drop tables if they exist
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

-- Create Departments table
CREATE TABLE departments
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create Employees table
CREATE TABLE employees
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255)        NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    position      VARCHAR(100)        NOT NULL,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments (id)
);

-- Create Tasks table
CREATE TABLE tasks
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    deadline    DATE         NOT NULL
);

-- Create Assignments table (Many-to-Many: Employees <-> Tasks)
CREATE TABLE assignments
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    task_id     BIGINT NOT NULL,
    status      ENUM ('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (task_id) REFERENCES tasks (id)
);

-- Create Comments table (1:N, Employees comment on Tasks)
CREATE TABLE comments
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id     BIGINT NOT NULL,
    employee_id BIGINT NOT NULL,
    content     TEXT   NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);

-- Insert sample Departments
INSERT INTO departments (name)
VALUES ('Software Development'),
       ('Project Management'),
       ('Quality Assurance');

-- Insert sample Employees
INSERT INTO employees (name, email, position, department_id)
VALUES ('Alice Johnson', 'alice@example.com', 'Software Developer', 1),
       ('Bob Smith', 'bob@example.com', 'Project Manager', 2),
       ('Charlie Brown', 'charlie@example.com', 'QA Engineer', 3);

-- Insert sample Tasks
INSERT INTO tasks (title, description, deadline)
VALUES ('Develop API', 'Create RESTful APIs for the project', '2024-02-10'),
       ('Test Application', 'Perform integration and unit testing', '2024-02-15'),
       ('Write Documentation', 'Prepare system documentation for developers', '2024-02-20');

-- Assign Employees to Tasks
INSERT INTO assignments (employee_id, task_id, status)
VALUES (1, 1, 'In Progress'), -- Alice is working on the API
       (2, 1, 'Pending'),     -- Bob is also assigned but hasn't started
       (3, 2, 'Pending'),     -- Charlie will test the application
       (1, 3, 'Pending'),     -- Alice will write documentation
       (2, 3, 'Completed');
-- Bob finished documentation

-- Insert sample Comments on Tasks
INSERT INTO comments (task_id, employee_id, content)
VALUES (1, 1, 'Started working on the API today.'),
       (1, 2, 'Need more details on the API endpoints.'),
       (2, 3, 'Testing has started, initial results look good.'),
       (3, 1, 'Documentation is halfway done.');
