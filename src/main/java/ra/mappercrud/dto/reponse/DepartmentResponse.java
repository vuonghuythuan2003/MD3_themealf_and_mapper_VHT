package ra.mappercrud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.mappercrud.model.Department;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {
    private Integer id;
    private String name;
    private String deptNo;
    private Boolean status;
//    private int employeeCount;

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.deptNo = department.getDeptNo();
        this.status = department.getStatus();
//        this.employeeCount = department.getEmployees().size();
    }
}
