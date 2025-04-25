# Employee Task Management API

## Project Description

This application is a RESTful web service built using Spring Boot, Spring Data JPA (Hibernate), and Maven. It allows managing employees, departments, tasks, and their assignments, including comments on tasks.

**Main Features:**
*   Department Management (CRUD)
*   Employee Management (CRUD) with department assignment
*   Task Management (CRUD)
*   Assigning tasks to employees (CRUD) with status tracking (PENDING, IN_PROGRESS, COMPLETED)
*   Adding comments to tasks by employees

## Requirements

*   Java Development Kit (JDK) version 22 or later
*   Apache Maven (or use the included Maven Wrapper `mvnw`)
*   MySQL database

## Database Setup

1.  Ensure you have a running MySQL server.
2.  Create a database named `employee_tasks_db`. You can use the command from `script.sql`:
    ```sql
    CREATE DATABASE IF NOT EXISTS employee_tasks_db;
    ```
3.  Execute the SQL script `src/main/resources/scripts/script.sql` on your MySQL server. This script will create the necessary tables and insert sample data.
    ```bash
    # Example using mysql client
    mysql -u <username> -p < employee_tasks_db < src/main/resources/scripts/script.sql
    ```

## Application Configuration

1.  Navigate to the file `src/main/resources/application.properties`.
2.  Set the login credentials for your MySQL database:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/employee_tasks_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    spring.datasource.username=<your_db_username>
    spring.datasource.password=<your_db_password>
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Hibernate properties
    spring.jpa.hibernate.ddl-auto=update # Or 'validate' if you don't want automatic schema changes
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```
    *Note: This file is in `.gitignore` to prevent accidental commits of sensitive data. If you don't have it yet, create it.*

## Build

Open a terminal in the project's root directory and run the following command using the Maven Wrapper:

*   On Windows:
    ```bash
    .\mvnw clean package
    ```
*   On Linux/macOS:
    ```bash
    ./mvnw clean package
    ```
This command compiles the code, runs tests, and packages the application into an executable JAR file in the `target/` directory.

## Running the Application

After a successful build, run the application using the following command:

```bash
java -jar target/employee_task_management-0.0.1-SNAPSHOT.jar
```

The application will run on port 8080 by default.

## Documentation

*   **ER Diagram:** The database schema design can be found in the file `src/main/resources/scripts/ER-Diagram.pdf`.
*   **SQL Script:** The script for creating the database and inserting sample data is in `src/main/resources/scripts/script.sql`.
*   **API Documentation (Swagger UI):** After starting the application, the interactive API documentation generated using SpringDoc OpenAPI is available at:
    [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
    *   To access Swagger UI and API endpoints, use the default login credentials defined in `SecurityConfig.java`:
        *   Username: `testuser`
        *   Password: `password`

## Endpoints

The application provides REST API endpoints for managing resources (Departments, Employees, Tasks, Assignments, Comments). A detailed list of endpoints, their parameters, and usage examples can be found in the Swagger UI. All major CRUD operations (GET, POST, PUT, DELETE) are implemented.
