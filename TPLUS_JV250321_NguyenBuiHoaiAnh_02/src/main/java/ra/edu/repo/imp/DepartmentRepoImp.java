package ra.edu.repo.imp;

import org.springframework.stereotype.Repository;
import ra.edu.model.Department;
import ra.edu.repo.DepartmentRepo;
import ra.edu.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepoImp implements DepartmentRepo {

    // Display
    @Override
    public List<Department> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> departmentList = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call display_department()}");
            ResultSet rs = callSt.executeQuery();
            departmentList = new ArrayList<>();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("status"));
                departmentList.add(department);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return departmentList;
    }

    // Add
    @Override
    public boolean add(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_department(?,?,?)}");
            callSt.setString(1, department.getName());
            callSt.setString(2, department.getDescription());
            callSt.setBoolean(3, department.isStatus());
            callSt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    // Update
    @Override
    public Department findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Department department = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_department_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return department;
    }

    @Override
    public boolean update(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_department(?,?,?,?)}");
            callSt.setInt(1, department.getId());
            callSt.setString(2, department.getName());
            callSt.setString(3, department.getDescription());
            callSt.setBoolean(4, department.isStatus());
            callSt.executeUpdate();
            System.out.println(department.getId());
            System.out.println(department.getName());
            System.out.println(department.getDescription());
            System.out.println(department.isStatus());
            return true;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    // Delete
    @Override
    public boolean deleteById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_department(?)}");
            callSt.setInt(1, id);
            return true;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }


}
