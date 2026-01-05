-- SQL Script for Assignment 4 - JDBC Student Management System
-- This script creates the Students table and inserts dummy data

-- Create the Students table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    branch VARCHAR(50) NOT NULL,
    semester INT NOT NULL,
    percentage DOUBLE NOT NULL,
    year_of_passing INT NOT NULL
);

-- Insert dummy student records
INSERT INTO students (name, branch, semester, percentage, year_of_passing) VALUES
('Raj Kumar', 'CSE', 6, 78.5, 2024),
('Priya Singh', 'CSE', 7, 82.3, 2024),
('Amit Patel', 'EC', 7, 75.8, 2024),
('Sneha Sharma', 'Civil', 6, 80.2, 2024),
('Vikram Reddy', 'Civil', 7, 72.5, 2024),
('Neha Gupta', 'CSE', 5, 85.1, 2025),
('Rohan Verma', 'EC', 7, 79.4, 2024),
('Anjali Tiwari', 'Mechanical', 6, 76.6, 2025),
('Abhishek Singh', 'CSE', 4, 88.2, 2025),
('Divya Nair', 'EC', 6, 81.5, 2024),
('Karan Malhotra', 'Civil', 5, 73.9, 2025),
('Sanya Kapoor', 'Mechanical', 7, 77.3, 2024);

-- Optional: Verify the data
SELECT * FROM students;

