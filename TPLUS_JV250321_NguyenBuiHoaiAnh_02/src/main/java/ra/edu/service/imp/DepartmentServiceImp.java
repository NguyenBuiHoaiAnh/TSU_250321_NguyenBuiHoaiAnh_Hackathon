package ra.edu.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.Department;
import ra.edu.repo.DepartmentRepo;
import ra.edu.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    // Display
    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    // Add
    @Override
    public boolean create(Department department) {
        return departmentRepo.add(department);
    }

    // Update
    @Override
    public Department findById(int id) {
        return departmentRepo.findById(id);
    }

    @Override
    public boolean update(Department department) {
        return departmentRepo.update(department);
    }

    // Delete
    @Override
    public boolean delete(int id) {
        return departmentRepo.deleteById(id);
    }
}
