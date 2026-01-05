package org.example.model;

public class Student {
    private int id;
    private String name;
    private String branch;
    private int semester;
    private double percentage;
    private int yearOfPassing;

    public Student() { }

    public Student(int id, String name, String branch, int semester, double percentage, int yearOfPassing) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.semester = semester;
        this.percentage = percentage;
        this.yearOfPassing = yearOfPassing;
    }

    public Student(String name, String branch, int semester, double percentage, int yearOfPassing) {
        this.name = name;
        this.branch = branch;
        this.semester = semester;
        this.percentage = percentage;
        this.yearOfPassing = yearOfPassing;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public int getYearOfPassing() { return yearOfPassing; }
    public void setYearOfPassing(int yearOfPassing) { this.yearOfPassing = yearOfPassing; }

    @Override
    public String toString() {
        return String.format("Student[id=%d, name=%s, branch=%s, semester=%d, percentage=%.2f, yearOfPassing=%d]",
                id, name, branch, semester, percentage, yearOfPassing);
    }
}

