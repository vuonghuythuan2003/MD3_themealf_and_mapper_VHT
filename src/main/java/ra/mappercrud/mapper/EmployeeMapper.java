package ra.mappercrud.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.mappercrud.dto.reponse.DepartmentResponse;
import ra.mappercrud.dto.reponse.EmployeeReponse;
import ra.mappercrud.dto.request.EmployeeCreateRequest;
import ra.mappercrud.model.Department;
import ra.mappercrud.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class EmployeeMapper implements GenericMapper<EmployeeCreateRequest, Employee, EmployeeReponse>{
    @Override
    public Employee mapperRequestToEntity(EmployeeCreateRequest request) {
        if (request == null) {
            return null;
        }
        return Employee.builder()
                .id(request.getId())
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .department(request.getDepartmentId() != null
                        ? Employee.builder().department(
                        Department.builder().id(request.getDepartmentId()).build()
                ).build().getDepartment()
                        : null)
                .status(request.getStatus())
                .avatarUrl(request.getAvatarUrl())
                .build();
    }

    @Override
    public EmployeeReponse mapperEntityToResponse(Employee entity) {
        if (entity == null) {
            return null;
        }
        return EmployeeReponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .geader(entity.getGender())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : "Chưa có phòng ban")
                .status(entity.getStatus())
                .avatarUrl(entity.getAvatarUrl())
                .build();
    }

    public List<EmployeeReponse> mapperListEntityToListResponse(List<Employee> Employees) {
        if (Employees == null) return Collections.emptyList();
        return Employees.stream().map(this::mapperEntityToResponse).collect(Collectors.toList());
    }
}
