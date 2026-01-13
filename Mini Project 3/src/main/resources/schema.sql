-- SQL Script for Mini Project 3 - JDBC Student Management System
-- This script creates the students and users tables with dummy data

-- Create the students table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    eno VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    branch VARCHAR(50) NOT NULL,
    semester INT NOT NULL,
    percentage DOUBLE NOT NULL CHECK (percentage > 0),
    year_of_passing INT NOT NULL
);

-- Create the users table for login
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

-- Insert dummy student records
INSERT INTO students (eno, name, branch, semester, percentage, year_of_passing) VALUES
('E001', 'Raj Kumar', 'CSE', 6, 78.5, 2024),
('E002', 'Priya Singh', 'CSE', 7, 82.3, 2024),
('E003', 'Amit Patel', 'EC', 7, 75.8, 2024),
('E004', 'Sneha Sharma', 'Civil', 6, 80.2, 2024),
('E005', 'Vikram Reddy', 'Civil', 7, 72.5, 2024),
('E006', 'Neha Gupta', 'CSE', 5, 85.1, 2025),
('E007', 'Rohan Verma', 'EC', 7, 79.4, 2024),
('E008', 'Anjali Tiwari', 'Mechanical', 6, 76.6, 2025),
('E009', 'Abhishek Singh', 'CSE', 4, 88.2, 2025),
('E010', 'Divya Nair', 'EC', 6, 81.5, 2024),
('E011', 'Karan Malhotra', 'Civil', 5, 73.9, 2025),
('E012', 'Sanya Kapoor', 'Mechanical', 7, 77.3, 2024)
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- Insert default test user for login
INSERT INTO users (username, password) VALUES
('admin', 'admin123'),
('user', 'user123')
ON DUPLICATE KEY UPDATE password=VALUES(password);

