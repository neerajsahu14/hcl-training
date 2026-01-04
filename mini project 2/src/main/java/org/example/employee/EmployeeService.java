package org.example.employee;

import org.example.employee.exceptions.DuplicateEmployeeException;
import org.example.employee.exceptions.EmployeeNotFoundException;
import org.example.employee.exceptions.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) { this.repo = repo; }

    public void addEmployee(String id, String name, String dept, double salary) throws Exception {
        if (id == null || id.trim().isEmpty()) throw new InvalidInputException("ID cannot be empty");
        if (dept == null || dept.trim().isEmpty()) throw new InvalidInputException("Department cannot be empty");
        if (salary <= 0) throw new InvalidInputException("Salary must be positive");
        if (repo.find(id) != null) throw new DuplicateEmployeeException("Employee ID already exists");
        Employee e = new Employee(id.trim(), name.trim(), dept.trim(), salary);
        repo.add(e);
    }

    public Collection<Employee> listAll() { return repo.all(); }

    public Employee findById(String id) throws EmployeeNotFoundException {
        Employee e = repo.find(id);
        if (e == null) throw new EmployeeNotFoundException("Employee not found");
        return e;
    }

    public void updateSalary(String id, double newSalary) throws Exception {
        if (newSalary <= 0) throw new InvalidInputException("Salary must be positive");
        Employee e = repo.find(id);
        if (e == null) throw new EmployeeNotFoundException("Employee not found");
        e.setSalary(newSalary);
        repo.update(e);
    }

    public boolean delete(String id) throws Exception { return repo.remove(id); }

    public List<Employee> sortedBy(String key, boolean asc) {
        Comparator<Employee> cmp;
        switch (key.toLowerCase()){
            case "name": cmp = Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER); break;
            case "salary": cmp = Comparator.comparingDouble(Employee::getSalary); break;
            default: cmp = Comparator.comparing(Employee::getId); break;
        }
        if (!asc) cmp = cmp.reversed();
        return repo.all().stream().sorted(cmp).collect(Collectors.toList());
    }

    public Set<String> departments(){
        return repo.all().stream().map(Employee::getDepartment).filter(d->d!=null && !d.isEmpty()).collect(Collectors.toSet());
    }
}

