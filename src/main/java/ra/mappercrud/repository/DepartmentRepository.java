package ra.mappercrud.repository;

import ra.mappercrud.model.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();
    Department findById(int id);
    boolean save(Department department);
    boolean update(Department department);
    boolean delete(int depId);
}
