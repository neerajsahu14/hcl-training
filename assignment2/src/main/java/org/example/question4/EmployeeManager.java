package org.example.question4;

import java.util.*;
import java.util.regex.Pattern;

interface EmployeeRepo {
    void add(Employee e);
    Employee find(String id);
    boolean remove(String id);
    Collection<Employee> all();
}

class Employee {
    String id; String name; double salary;
    public Employee(String id, String name, double salary){this.id=id;this.name=name;this.salary=salary;}
    public String toString(){return id+" - "+name+" - "+salary;}
}

class HashMapEmployeeRepo implements EmployeeRepo {
    Map<String,Employee> map = new HashMap<>();
    public void add(Employee e){map.put(e.id,e);}
    public Employee find(String id){return map.get(id);}
    public boolean remove(String id){return map.remove(id)!=null;}
    public Collection<Employee> all(){return map.values();}
}

public class EmployeeManager {
    private static final Pattern ID = Pattern.compile("^E\\d{1,5}$");
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        EmployeeRepo repo = new HashMapEmployeeRepo();
        while(true){
            System.out.println("--- Employee Manager ---");
            System.out.println("1. Add employee");
            System.out.println("2. Display all");
            System.out.println("3. Search by ID");
            System.out.println("4. Remove by ID");
            System.out.println("5. Demo null key/value (Hashtable doesn't allow nulls)");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            try{
                switch(c){
                    case "1":
                        System.out.print("ID (e.g. E12): ");
                        String id = sc.nextLine().trim();
                        if(!ID.matcher(id).matches()){System.out.println("Invalid ID");break;}
                        System.out.print("Name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Salary: ");
                        double sal = Double.parseDouble(sc.nextLine().trim());
                        repo.add(new Employee(id,name,sal));
                        System.out.println("Added");
                        break;
                    case "2":
                        repo.all().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.print("ID to search: ");
                        String sid = sc.nextLine().trim();
                        Employee e = repo.find(sid);
                        System.out.println(e==null?"Not found":e);
                        break;
                    case "4":
                        System.out.print("ID to remove: ");
                        String rid = sc.nextLine().trim();
                        System.out.println(repo.remove(rid)?"Removed":"Not found");
                        break;
                    case "5":
                        demoNulls();
                        break;
                    case "6":
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }catch(Exception ex){System.out.println("Error: "+ex.getMessage());}
            System.out.println();
        }
    }
    private static void demoNulls(){
        Map<String,Employee> hm = new HashMap<>();
        hm.put(null,new Employee("E0","NullKey",0));
        hm.put("E1",null);
        System.out.println("HashMap allows null key and null value: "+hm);
        Hashtable<String,Employee> ht = new Hashtable<>();
        try{ ht.put(null,new Employee("E2","X",1)); }catch(Exception ex){ System.out.println("Hashtable null key error: "+ex.getClass().getSimpleName()); }
        try{ ht.put("E3",null); }catch(Exception ex){ System.out.println("Hashtable null value error: "+ex.getClass().getSimpleName()); }
        TreeMap<String,Employee> tm = new TreeMap<>();
        tm.put("E5", new Employee("E5","T",5));
        System.out.println("TreeMap sample: "+tm);
    }
}

