package org.example.employee;

import org.example.employee.exceptions.DuplicateEmployeeException;
import org.example.employee.exceptions.EmployeeNotFoundException;
import org.example.employee.exceptions.InvalidInputException;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class EmployeeApp {
    private final EmployeeService service;
    private final Scanner sc = new Scanner(System.in);

    public EmployeeApp(EmployeeService service){ this.service = service; }

    public void run(){
        while(true){
            printMenu();
            String c = sc.nextLine().trim();
            try{
                switch(c){
                    case "1": doAdd(); break;
                    case "2": doDisplayAll(); break;
                    case "3": doSearch(); break;
                    case "4": doUpdateSalary(); break;
                    case "5": doDelete(); break;
                    case "6": doSorted(); break;
                    case "7": doDepartments(); break;
                    case "8": System.out.println("Bye"); return;
                    default: System.out.println("Invalid choice");
                }
            }catch(Exception ex){ System.out.println("Error: "+ex.getMessage()); }
            System.out.println();
        }
    }

    private void printMenu(){
        System.out.println("--- Employee Manager ---");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Update Employee Salary");
        System.out.println("5. Delete Employee");
        System.out.println("6. Display Sorted Employees");
        System.out.println("7. Display Departments");
        System.out.println("8. Exit");
        System.out.print("Choose: ");
    }

    private void doAdd() throws Exception{
        System.out.print("ID: "); String id = sc.nextLine().trim();
        System.out.print("Name: "); String name = sc.nextLine().trim();
        System.out.print("Department: "); String dept = sc.nextLine().trim();
        System.out.print("Salary: "); double sal = Double.parseDouble(sc.nextLine().trim());
        service.addEmployee(id,name,dept,sal);
        System.out.println("Added");
    }

    private void doDisplayAll(){
        Collection<Employee> all = service.listAll();
        all.forEach(System.out::println);
    }

    private void doSearch() throws EmployeeNotFoundException{
        System.out.print("ID: "); String id = sc.nextLine().trim();
        Employee e = service.findById(id);
        System.out.println(e);
    }

    private void doUpdateSalary() throws Exception{
        System.out.print("ID: "); String id = sc.nextLine().trim();
        System.out.print("New Salary: "); double s = Double.parseDouble(sc.nextLine().trim());
        service.updateSalary(id,s);
        System.out.println("Updated");
    }

    private void doDelete() throws Exception{
        System.out.print("ID: "); String id = sc.nextLine().trim();
        boolean ok = service.delete(id);
        System.out.println(ok?"Deleted":"Not found");
    }

    private void doSorted(){
        System.out.print("Sort by (id/name/salary): "); String key = sc.nextLine().trim();
        System.out.print("Order (asc/desc): "); boolean asc = sc.nextLine().trim().equalsIgnoreCase("asc");
        List<Employee> list = service.sortedBy(key,asc);
        list.forEach(System.out::println);
    }

    private void doDepartments(){
        service.departments().forEach(System.out::println);
    }

    public static void startFromMain(Path csvPath) throws Exception{
        CsvEmployeeRepo repo = new CsvEmployeeRepo(csvPath);
        EmployeeService service = new EmployeeService(repo);
        new EmployeeApp(service).run();
    }
}

