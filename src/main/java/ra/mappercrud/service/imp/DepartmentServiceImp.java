package ra.mappercrud.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.mappercrud.dto.reponse.DepartmentResponse;
import ra.mappercrud.dto.request.DepartmentCreateRequest;
import ra.mappercrud.mapper.DepartmentMapper;
import ra.mappercrud.model.Department;
import ra.mappercrud.repository.DepartmentRepository;
import ra.mappercrud.service.DepartmentService;

import java.util.List;

@Service

public class DepartmentServiceImp implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImp(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentResponse> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departmentMapper.mapperListEntityToListResponse(departments);
    }

    @Override
    public DepartmentResponse findById(int id) {
        Department department = departmentRepository.findById(id);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        return departmentMapper.mapperEntityToResponse(department);
    }

    @Override
    public DepartmentResponse create(DepartmentCreateRequest request) {
        Department department = departmentMapper.mapperRequestToEntity(request);
        System.out.println("Mapped Entity: " + department); // Debug log
        departmentRepository.save(department);
        return findById(department.getId());
    }


    @Override
    public DepartmentResponse update(int id, DepartmentCreateRequest request) {
        Department department = departmentRepository.findById(id);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        department.setName(request.getName());
        department.setDeptNo(request.getDeptNo());
        department.setStatus(request.getStatus());
        departmentRepository.update(department);
        return departmentMapper.mapperEntityToResponse(department);
    }

    @Override
    public void delete(int id) {
        if (!departmentRepository.delete(id)) {
            throw new RuntimeException("Failed to delete Department");
        }
    }
}
