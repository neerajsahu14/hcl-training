package org.example.employee;

import java.util.Collection;

public interface EmployeeRepo {
    void add(Employee e) throws Exception;
    Employee find(String id);
    void update(Employee e) throws Exception;
    boolean remove(String id) throws Exception;
    Collection<Employee> all();
    void load() throws Exception;
    void save() throws Exception;
}

