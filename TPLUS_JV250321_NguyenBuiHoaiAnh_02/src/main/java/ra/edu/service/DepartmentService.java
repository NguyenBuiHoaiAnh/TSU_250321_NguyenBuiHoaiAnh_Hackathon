package ra.edu.service;

import ra.edu.model.Department;

import java.util.List;

public interface DepartmentService {
    // Display
    List<Department> findAll();

    // Add
    boolean create(Department department);

    // Update
    Department findById(int id);

    boolean update(Department department);

    // Delete
    boolean delete(int id);
}
