package org.example.model;

public class Student {
    private int id;
    private String eno;
    private String name;
    private String branch;
    private int semester;
    private double percentage;
    private int year_of_passing;

    public Student() {
    }

    public Student(String eno, String name, String branch, int semester, double percentage, int year_of_passing) {
        this.eno = eno;
        this.name = name;
        this.branch = branch;
        this.semester = semester;
        this.percentage = percentage;
        this.year_of_passing = year_of_passing;
    }

    public Student(int id, String eno, String name, String branch, int semester, double percentage, int year_of_passing) {
        this.id = id;
        this.eno = eno;
        this.name = name;
        this.branch = branch;
        this.semester = semester;
        this.percentage = percentage;
        this.year_of_passing = year_of_passing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getYear_of_passing() {
        return year_of_passing;
    }

    public void setYear_of_passing(int year_of_passing) {
        this.year_of_passing = year_of_passing;
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, eno='%s', name='%s', branch='%s', semester=%d, percentage=%.2f, year=%d}",
                id, eno, name, branch, semester, percentage, year_of_passing);
    }
}

