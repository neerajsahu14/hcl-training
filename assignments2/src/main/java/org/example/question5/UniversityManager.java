package org.example.question5;

import java.util.*;
import java.util.regex.Pattern;

public class UniversityManager {
    static class Student { String id, name; double marks; String course; Student(String id,String name,double marks,String course){this.id=id;this.name=name;this.marks=marks;this.course=course;} public String toString(){return id+" - "+name+" - "+marks+" - "+course;} }
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        Map<String,Student> hm = new HashMap<>();
        while(true){
            System.out.println("--- University Manager ---");
            System.out.println("1. Add student");
            System.out.println("2. Display all");
            System.out.println("3. Remove by ID");
            System.out.println("4. Search by ID");
            System.out.println("5. Sort by marks");
            System.out.println("6. Convert HashMap->TreeMap");
            System.out.println("7. Count students course-wise");
            System.out.println("8. Unique course names");
            System.out.println("9. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            try{
                switch(c){
                    case "1":
                        System.out.print("ID: "); String id=sc.nextLine().trim();
                        System.out.print("Name: "); String name=sc.nextLine().trim();
                        System.out.print("Marks: "); double marks=Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Course: "); String course=sc.nextLine().trim();
                        hm.put(id,new Student(id,name,marks,course));
                        System.out.println("Added"); break;
                    case "2": hm.values().forEach(System.out::println); break;
                    case "3": System.out.print("ID to remove: "); String rid=sc.nextLine().trim(); System.out.println(hm.remove(rid)==null?"Not found":"Removed"); break;
                    case "4": System.out.print("ID to search: "); String sid=sc.nextLine().trim(); System.out.println(hm.getOrDefault(sid,null)); break;
                    case "5": List<Student> list=new ArrayList<>(hm.values()); list.sort(Comparator.comparingDouble(s->s.marks)); list.forEach(System.out::println); break;
                    case "6": TreeMap<String,Student> tm=new TreeMap<>(hm); System.out.println(tm); break;
                    case "7": Map<String,Long> counts = new HashMap<>(); for(Student s:hm.values()){counts.put(s.course, counts.getOrDefault(s.course,0L)+1);} System.out.println(counts); break;
                    case "8": Set<String> courses = new HashSet<>(); for(Student s:hm.values()) courses.add(s.course); System.out.println(courses); break;
                    case "9": return;
                    default: System.out.println("Invalid");
                }
            }catch(Exception ex){System.out.println("Error: "+ex.getMessage());}
            System.out.println();
        }
    }
}

