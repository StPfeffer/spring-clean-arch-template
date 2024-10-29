# Clean Architecture with Spring Boot Template

This module serves as a foundational template that provides common entities, utility classes, request/response DTOs, and
core custom data structures. It follows **Clean Architecture** principles and is built using **Java 21** and **Spring
Boot**.

- [Overview](#overview)
- [Project Setup](#project-setup)
    - [Environment Configuration](#environment-configuration)
    - [Docker Compose Setup](#docker-compose-setup)
    - [Database Configuration](#database-configuration)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)

## Overview

This template is designed to offer reusable components that promote consistency and reduce redundancy across different
modules. It provides a foundation of shared logic, utility functions, and core entities, ensuring centralized business
logic for easy management and updating.

## Project Setup

### Environment Configuration

To configure your environment, create two files—`.env` and `.env.properties`—at the root of your project, with identical
values for the properties as shown below:

1. **.env**:
    ```properties
    POSTGRES_VERSION=16.2
    POSTGRES_USER=user
    POSTGRES_PASSWORD=user
    POSTGRES_DB=user
    POSTGRES_LOCAL_URL=localhost
    POSTGRES_LOCAL_PORT=5432
    POSTGRES_DOCKER_URL=database
    POSTGRES_DOCKER_PORT=5432
    ```

2. **.env.properties**:
    ```properties
    POSTGRES_USER=user
    POSTGRES_PASSWORD=user
    POSTGRES_DB=user
    POSTGRES_LOCAL_URL=localhost
    POSTGRES_LOCAL_PORT=5432
    POSTGRES_DOCKER_URL=database
    POSTGRES_DOCKER_PORT=5432
    ```

These files ensure consistent configuration across different environments within the project.

### Docker Compose Setup

A Docker Compose file is provided to help you quickly set up and manage the PostgreSQL database needed by this module.
The configuration automates the setup of the database container, ensuring consistent development and testing
environments.

1. **docker-compose.yml**:
    ```yaml
    version: '3.8'

    services:
      database:
        container_name: database
        image: postgres:${POSTGRES_VERSION}
        restart: always
        environment:
          POSTGRES_USER: ${POSTGRES_USER}
          POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
          POSTGRES_DB: ${POSTGRES_DB}
        ports:
          - '${POSTGRES_LOCAL_PORT}:${POSTGRES_DOCKER_PORT}'
        volumes:
          - pgdata:/var/lib/postgresql/data
        healthcheck:
          test: ["CMD-SHELL", "sh -c 'pg_isready -U ${POSTGRES_USER} -d ${POSTGRES_DB}'"]
          interval: 10s
          timeout: 5s
          retries: 5

    volumes:
      pgdata: {}
    ```

2. **Steps to Run PostgreSQL Container**:
    - Ensure Docker is installed and running.
    - Navigate to the root directory where the `docker-compose.yml` file is located.
    - Run the following command to start the PostgreSQL container:
      ```bash
      docker-compose up -d
      ```
    - PostgreSQL will be accessible using the configurations from your `.env` file.

This setup provides a reliable and replicable database environment for local development.

### Database Configuration

This template uses PostgreSQL as its primary database. Ensure that PostgreSQL is installed and configured based on the
settings in the `.env` and `.env.properties` files to maintain consistent behavior during local development.

## Technology Stack

- **Java 21**: The core language for developing this module.
- **Spring Boot**: Provides the framework for application development.
- **PostgreSQL**: Database technology for persistent storage.
- **Docker**: Containerization tool for consistent development environments.

## Architecture

This template adheres to **Clean Architecture** principles, emphasizing a clear separation of concerns. The architecture
consists of the following layers:

- **Domain Layer**: Contains core logic, DTOs, use cases, and common entities.
- **Service Layer**: Handles reusable logic and orchestration of business services.
- **Infrastructure Layer**: Manages database interactions and other system dependencies.
- **Presentation Layer**: Exposes APIs and utilities for consumption by other modules.

This architecture promotes maintainability, scalability, and ease of testing.