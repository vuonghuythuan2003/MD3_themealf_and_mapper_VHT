package ra.mappercrud.repository;

import ra.mappercrud.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee findById(String id);
    boolean save(Employee employee);
    boolean update(Employee employee);
    boolean delete(String id);
    Employee findByEmail(String email);
    Employee findByPhone(String phone);
}
