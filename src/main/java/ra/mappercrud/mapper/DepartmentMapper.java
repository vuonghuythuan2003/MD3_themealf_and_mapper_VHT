package ra.mappercrud.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.mappercrud.dto.reponse.DepartmentResponse;
import ra.mappercrud.dto.request.DepartmentCreateRequest;
import ra.mappercrud.model.Department;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper implements GenericMapper<DepartmentCreateRequest, Department, DepartmentResponse>{

    @Override
    public Department mapperRequestToEntity(DepartmentCreateRequest request) {
        if(request == null) return null;
        return Department.builder().name(request.getName()).deptNo(request.getDeptNo()).status(request.getStatus()).build();
    }

    public List<DepartmentResponse> mapperListEntityToListResponse(List<Department> departments) {
        if (departments == null) return Collections.emptyList();
        return departments.stream().map(this::mapperEntityToResponse).collect(Collectors.toList());
    }

    public DepartmentResponse mapperEntityToResponse(Department department) {
        if (department == null) return null;
        return new DepartmentResponse(department.getId(), department.getName(),
                department.getDeptNo(), department.getStatus(), department.getEmployees().size());
    }
}
