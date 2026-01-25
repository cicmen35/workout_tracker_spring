-- Create workout_tracker database schema
-- V1__Create_schema.sql

-- Users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Exercises table (seeded data)
CREATE TABLE exercises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(100),
    muscle_group VARCHAR(100)
);

-- Workout plans table
CREATE TABLE workout_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Workout plan items table
CREATE TABLE workout_plan_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plan_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    sets_count INT NOT NULL,
    reps_count INT NOT NULL,
    target_weight DECIMAL(5,2),
    rest_seconds INT,
    item_order INT NOT NULL,
    FOREIGN KEY (plan_id) REFERENCES workout_plans(id) ON DELETE CASCADE,
    FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE RESTRICT
);

-- Scheduled workouts table
CREATE TABLE scheduled_workouts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    plan_id BIGINT NOT NULL,
    scheduled_at DATETIME NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
    comments TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_id) REFERENCES workout_plans(id) ON DELETE CASCADE
);

-- Workout logs table (history/progress)
CREATE TABLE workout_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    scheduled_workout_id BIGINT NOT NULL,
    performed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    FOREIGN KEY (scheduled_workout_id) REFERENCES scheduled_workouts(id) ON DELETE CASCADE
);

-- Workout log items table
CREATE TABLE workout_log_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    log_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    sets_done INT NOT NULL,
    reps_done INT NOT NULL,
    weight_used DECIMAL(5,2),
    rpe INT CHECK (rpe >= 1 AND rpe <= 10),
    item_order INT NOT NULL,
    FOREIGN KEY (log_id) REFERENCES workout_logs(id) ON DELETE CASCADE,
    FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE RESTRICT
);

-- Indexes for performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_workout_plans_user_id ON workout_plans(user_id);
CREATE INDEX idx_workout_plan_items_plan_id ON workout_plan_items(plan_id);
CREATE INDEX idx_scheduled_workouts_user_id ON scheduled_workouts(user_id);
CREATE INDEX idx_scheduled_workouts_scheduled_at ON scheduled_workouts(scheduled_at);
CREATE INDEX idx_workout_logs_scheduled_workout_id ON workout_logs(scheduled_workout_id);
CREATE INDEX idx_workout_log_items_log_id ON workout_log_items(log_id);
