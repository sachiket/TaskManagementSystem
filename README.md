# Task Management System

Task Management System is a simple Spring Boot application for managing tasks. It provides CRUD functionality for tasks, allowing you to create, update, delete, and assign tasks to users. The project is built with Spring Boot, JPA, and a PostgreSQL database.

## Features

- **Create, Read, Update, Delete (CRUD) Tasks**: Easily manage tasks in the system.
- **Task Assignment**: Assign tasks to users by their unique ID.
- **Status Management**: Update the status of tasks (e.g., "Pending", "Completed").
- **Spring Boot & JPA Integration**: Simplifies the persistence layer for storing tasks in the database.
- **RESTful API**: Fully compliant with REST principles.
- **Exception Handling**: Custom exception handling for error cases (e.g., task not found).
- **Database**: Supports SQL (configurable for other databases).

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Technologies](#technologies)
- [Tests](#tests)
- [Contributing](#contributing)
- [License](#license)

## Installation

### Prerequisites

Ensure you have the following installed:
- Java 17+
- Gradle
- PostgreSQL (or your preferred database)

### Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/sachiket/task-management-system.git
    cd task-management-system
    ```

2. Create a PostgreSQL database (or configure your preferred DB in `application.properties`):
    ```sql
    CREATE DATABASE task_management;
    ```

3. Update the `src/main/resources/application.properties` file with your database configuration:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/task_management
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Build the project:
    ```bash
    ./gradlew build
    ```

5. Run the application:
    ```bash
    ./gradlew bootRun
    ```

The application will start on `http://localhost:8081`.

## Usage

### Running the API

You can interact with the Task Management System using tools like Postman or `curl`.

- **Get all tasks**:  
  `GET /api/tasks`  
  Example:
  ```bash
  curl -X GET http://localhost:8080/api/tasks
