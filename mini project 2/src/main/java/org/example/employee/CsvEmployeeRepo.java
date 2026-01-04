package org.example.employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class CsvEmployeeRepo implements EmployeeRepo {
    private final Map<String, Employee> map = new HashMap<>();
    private final Path csvPath;

    public CsvEmployeeRepo(Path csvPath) throws Exception {
        this.csvPath = csvPath;
        if (!Files.exists(csvPath)) {
            Files.createDirectories(csvPath.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(csvPath)) {
                bw.write("id,name,department,salary");
                bw.newLine();
            }
        }
        load();
    }

    @Override
    public synchronized void add(Employee e) throws Exception {
        if (map.containsKey(e.getId())) throw new Exception("Duplicate ID");
        map.put(e.getId(), e);
        save();
    }

    @Override
    public synchronized Employee find(String id) {
        return map.get(id);
    }

    @Override
    public synchronized void update(Employee e) throws Exception {
        if (!map.containsKey(e.getId())) throw new Exception("Not found");
        map.put(e.getId(), e);
        save();
    }

    @Override
    public synchronized boolean remove(String id) throws Exception {
        if (map.remove(id) != null) {
            save();
            return true;
        }
        return false;
    }

    @Override
    public synchronized Collection<Employee> all() {
        return new ArrayList<>(map.values());
    }

    @Override
    public synchronized void load() throws Exception {
        map.clear();
        try (BufferedReader br = Files.newBufferedReader(csvPath)) {
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 4) continue;
                String id = parts[0].trim();
                String name = parts[1].trim();
                String dept = parts[2].trim();
                double sal = 0;
                try { sal = Double.parseDouble(parts[3].trim()); } catch (NumberFormatException ex) { continue; }
                if (!id.isEmpty()) map.put(id, new Employee(id, name, dept, sal));
            }
        }
    }

    @Override
    public synchronized void save() throws Exception {
        Path tmp = csvPath.resolveSibling(csvPath.getFileName().toString() + ".tmp");
        try (BufferedWriter bw = Files.newBufferedWriter(tmp)) {
            bw.write("id,name,department,salary"); bw.newLine();
            for (Employee e : map.values()) {
                bw.write(String.format("%s,%s,%s,%.2f", e.getId(), e.getName(), e.getDepartment(), e.getSalary()));
                bw.newLine();
            }
        }
        Files.move(tmp, csvPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }
}

