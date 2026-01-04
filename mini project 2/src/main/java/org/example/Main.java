package org.example;


import org.example.employee.EmployeeApp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        Path csv = Paths.get("mini project 2", "src", "main", "resources", "data", "employees.csv");
        EmployeeApp.startFromMain(csv);
    }
}