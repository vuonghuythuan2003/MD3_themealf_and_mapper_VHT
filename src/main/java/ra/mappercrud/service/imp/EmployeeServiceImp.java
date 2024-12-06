package ra.mappercrud.service.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.mappercrud.dto.reponse.EmployeeReponse;
import ra.mappercrud.dto.request.EmployeeCreateRequest;
import ra.mappercrud.mapper.EmployeeMapper;
import ra.mappercrud.model.Department;
import ra.mappercrud.model.Employee;
import ra.mappercrud.repository.DepartmentRepository;
import ra.mappercrud.repository.EmployeeRepository;
import ra.mappercrud.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImp(EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository,
                              EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeReponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.mapperListEntityToListResponse(employees);
    }

    @Override
    public EmployeeReponse findById(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new RuntimeException("Không tìm thấy nhân viên với ID: " + id);
        }
        return employeeMapper.mapperEntityToResponse(employee);
    }

    @Override
    public EmployeeReponse save(EmployeeCreateRequest employeeCreateRequest) {
        Department department = departmentRepository.findById(employeeCreateRequest.getDepartmentId());
        if (department == null) {
            throw new RuntimeException("Không tìm thấy phòng ban với ID: " + employeeCreateRequest.getDepartmentId());
        }
        if (employeeRepository.findByEmail(employeeCreateRequest.getEmail()) != null) {
            throw new RuntimeException("Email đã tồn tại: " + employeeCreateRequest.getEmail());
        }
        if (employeeRepository.findByPhone(employeeCreateRequest.getPhone()) != null) {
            throw new RuntimeException("Số điện thoại đã được sử dụng: " + employeeCreateRequest.getPhone());
        }
        Employee employee = employeeMapper.mapperRequestToEntity(employeeCreateRequest);
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return employeeMapper.mapperEntityToResponse(employee);
    }

    @Override
    public EmployeeReponse update(String id, EmployeeCreateRequest request) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new RuntimeException("Không tìm thấy nhân viên với ID: " + id);
        }

        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setGender(request.getGender());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setStatus(request.getStatus());

        Department department = departmentRepository.findById(request.getDepartmentId());
        if (department != null) {
            employee.setDepartment(department);
        }

        employee.setAvatarUrl(request.getAvatarUrl());
        employeeRepository.update(employee);
        return employeeMapper.mapperEntityToResponse(employee);
    }

    @Override
    public void deleteById(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new RuntimeException("Không tìm thấy nhân viên với ID: " + id);
        }
        if (!employeeRepository.delete(id)) {
            throw new RuntimeException("Xóa thất bại");
        }
    }
}
