package org.example.question3;

import java.util.*;
import java.util.regex.Pattern;

interface StudentRepo {
    void add(Student s);
    boolean removeByRoll(int roll);
    Student findByRoll(int roll);
    List<Student> listAll();
}

class Student {
    int roll;
    String name;
    double marks;
    public Student(int roll, String name, double marks) {
        this.roll = roll; this.name = name; this.marks = marks;
    }
    public String toString() { return String.format("%d - %s - %.2f", roll, name, marks); }
}

class ArrayListStudentRepo implements StudentRepo {
    private List<Student> list = new ArrayList<>();
    public void add(Student s) { list.add(s); }
    public boolean removeByRoll(int roll) { return list.removeIf(st -> st.roll == roll); }
    public Student findByRoll(int roll) { return list.stream().filter(st->st.roll==roll).findFirst().orElse(null); }
    public List<Student> listAll() { return Collections.unmodifiableList(list); }
}

public class StudentManager {
    private static final Pattern NAME = Pattern.compile("^[A-Za-z ]{3,50}$");
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        StudentRepo repo = new ArrayListStudentRepo();
        while (true) {
            System.out.println("--- Student Manager ---");
            System.out.println("1. Add student");
            System.out.println("2. Display all");
            System.out.println("3. Remove by roll");
            System.out.println("4. Search by roll");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            try {
                switch (c) {
                    case "1":
                        System.out.print("Roll: ");
                        int r = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: ");
                        String n = sc.nextLine().trim();
                        if (!NAME.matcher(n).matches()) { System.out.println("Invalid name"); break; }
                        System.out.print("Marks: ");
                        double m = Double.parseDouble(sc.nextLine().trim());
                        repo.add(new Student(r,n,m));
                        System.out.println("Added");
                        break;
                    case "2":
                        repo.listAll().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.print("Roll to remove: ");
                        int rr = Integer.parseInt(sc.nextLine().trim());
                        boolean ok = repo.removeByRoll(rr);
                        System.out.println(ok?"Removed":"Not found");
                        break;
                    case "4":
                        System.out.print("Roll to search: ");
                        int rs = Integer.parseInt(sc.nextLine().trim());
                        Student s = repo.findByRoll(rs);
                        System.out.println(s==null?"Not found":s);
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }
}

