package ra.mappercrud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentCreateRequest {
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Tên không được vượt quá 100 ký tự")
    private String name;

    @NotBlank(message = "Số phòng trong tòa nhà không được để trống")
    @Size(max = 4, message = "Số phòng không được quá 4 ký tự")
    @Pattern(regexp = "^[A-Za-z0-9]{4}$", message = "Số phòng chỉ bao gồm 4 ký tự chữ hoặc số")
    private String deptNo;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;
}
