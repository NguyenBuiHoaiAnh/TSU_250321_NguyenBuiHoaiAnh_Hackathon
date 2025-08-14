package ra.edu.repo;

import ra.edu.model.Department;

import java.util.List;

public interface DepartmentRepo {
    // Display
    List<Department> findAll();

    // Add
    boolean add(Department department);

    // Update
    Department findById(int id);

    boolean update(Department department);

    // Delete
    boolean deleteById(int id);
}
