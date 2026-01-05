package org.example;

import org.example.service.DBConnection;
import java.sql.Connection;

/**
 * Question 1: Write a program to demonstrate JDBC connection establishment to MySQL.
 * If connections is established then "Connection Successful" message will displayed
 * otherwise "Unable to connect" message should be displayed.
 */
public class Question1 {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("Connection Successful");
                conn.close();
            } else {
                System.out.println("Unable to connect");
            }
        } catch (Exception e) {
            System.out.println("Unable to connect");
            System.err.println("Error: " + e.getMessage());
        }
    }
}

