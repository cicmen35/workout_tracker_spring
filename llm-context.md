# LLM Context – Workout Tracker Backend (Spring Boot, Maven, MySQL)

## Project Overview
This project is a backend REST API for a workout tracker application.

Users can:
- Sign up and log in using JWT authentication
- Create, update, delete workout plans
- Schedule workouts for specific dates and times
- Log completed workouts and track progress
- View reports on workout history and exercise progress

The project is built using **Java + Spring Boot**, **Maven**, and **MySQL**.

---

## Technology Stack (FIXED – do not suggest alternatives)

### Core
- Java 17+
- Spring Boot
- Maven

### Spring Modules
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-validation

### Database
- MySQL (primary, required)
- Flyway for database migrations
- Hibernate / JPA for ORM

### Security
- JWT authentication using `io.jsonwebtoken (jjwt)`
- BCrypt password hashing
- Stateless authentication (no sessions)

### Tooling & Utilities
- Lombok (for boilerplate reduction)
- springdoc-openapi (Swagger / OpenAPI 3)
- JUnit 5 for testing
- Testcontainers (MySQL) for integration tests

---

## Architectural Style
- RESTful API
- Layered architecture:
  - controller (API layer)
  - service (business logic)
  - repository (data access)
  - domain/entity (JPA entities)
  - dto (API request/response models)
  - security (JWT + Spring Security config)

Controllers MUST NOT access repositories directly.

---

## Package Structure (ENFORCE)
com.example.workouttracker
├─ api
│ ├─ controller
│ ├─ dto
│ │ ├─ auth
│ │ ├─ exercise
│ │ ├─ plan
│ │ ├─ schedule
│ │ └─ report
│ └─ error
├─ domain
│ ├─ entity
│ └─ enums
├─ repository
├─ service
├─ security
└─ config

---

## Database Schema (AUTHORITATIVE)

### users
- id (PK)
- email (unique)
- password_hash
- display_name
- created_at

### exercises (seeded data)
- id
- name
- description
- category (e.g. cardio, strength, flexibility)
- muscle_group (e.g. chest, back, legs)

### workout_plans
- id
- user_id (FK users)
- title
- notes
- created_at
- updated_at

### workout_plan_items
- id
- plan_id (FK workout_plans)
- exercise_id (FK exercises)
- sets
- reps
- target_weight
- rest_seconds
- item_order

### scheduled_workouts
- id
- user_id (FK users)
- plan_id (FK workout_plans)
- scheduled_at (datetime)
- status (PENDING, COMPLETED, CANCELLED)
- comments

### workout_logs
- id
- scheduled_workout_id (FK scheduled_workouts)
- performed_at
- notes

### workout_log_items
- id
- log_id (FK workout_logs)
- exercise_id (FK exercises)
- sets_done
- reps_done
- weight_used
- rpe (optional)
- item_order

---

## Security Rules (VERY IMPORTANT)
- JWT is required for ALL endpoints except:
  - `/auth/**`
  - `/swagger-ui/**`
  - `/v3/api-docs/**`
- Users may ONLY access their own data
- Ownership checks must happen in the service layer
- JWT subject = user ID (preferred) or email
- Passwords are NEVER stored or logged in plain text

---

## API Endpoints (DO NOT RENAME)

### Authentication
- POST `/auth/signup`
- POST `/auth/login`

### Exercises
- GET `/exercises`

### Workout Plans
- POST `/plans`
- GET `/plans`
- GET `/plans/{id}`
- PUT `/plans/{id}`
- DELETE `/plans/{id}`

### Scheduling & Workouts
- POST `/schedule`
- GET `/schedule?status=PENDING`
- PATCH `/schedule/{id}/comments`
- PATCH `/schedule/{id}/cancel`
- POST `/workouts/{scheduledId}/complete`

### Reports
- GET `/reports/history`
- GET `/reports/progress?exerciseId={id}`

---

## DTO & Validation Rules
- Controllers use DTOs ONLY (never entities)
- Validation annotations are mandatory:
  - @NotBlank, @NotNull, @Email, @Min, @Size
- Request and response DTOs are separated
- IDs are NEVER accepted directly from the client unless required by endpoint

---

## Flyway & Data Seeding
- Flyway migrations live in `src/main/resources/db/migration`
- Schema creation is done ONLY via Flyway
- Exercises are seeded either:
  - via Flyway SQL migration
  - or a CommandLineRunner that checks if data exists

---

## Testing Guidelines
- Unit tests for services
- Controller tests using MockMvc
- Integration tests using Testcontainers (MySQL)
- Authentication & authorization rules must be tested
- Do NOT mock repositories for integration tests

---

## OpenAPI / Swagger
- Use springdoc-openapi
- Annotate controllers with:
  - @Operation
  - @ApiResponses
- DTOs should include example values where useful
- Swagger UI must be accessible without authentication

---

## Coding Rules for the LLM
- Do NOT introduce new frameworks or libraries
- Do NOT change database schema without updating Flyway
- Prefer constructor injection
- Prefer immutable DTOs where possible
- Keep business logic out of controllers
- Favor clarity over cleverness

---

## Project Goal
A clean, secure, well-documented backend API that demonstrates:
- JWT-based authentication
- Proper relational modeling
- CRUD operations
- Business logic enforcement
- Reporting and aggregation
- Production-style Spring Boot architecture
