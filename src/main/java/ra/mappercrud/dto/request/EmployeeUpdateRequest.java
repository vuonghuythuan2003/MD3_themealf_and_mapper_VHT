package ra.mappercrud.dto.request;

import javax.validation.constraints.*;

public class EmployeeUpdateRequest {
    @Size(max = 5, message = "Mã nhân viên không được vượt quá 5 ký tự")
    @Pattern(regexp = "^[A-Za-z0-9]{5}$", message = "Mã nhân viên phải bao gồm chữ hoặc số (tối đa 5 ký tự)")
    private String id;

    @NotBlank(message = "Tên nhân viên không được để trống")
    @Size(max = 50, message = "Tên nhân viên không được vượt quá 50 ký tự")
    private String name;

    @NotNull(message = "Tuổi nhân viên không được để trống")
    @Min(value = 18, message = "Tuổi nhân viên phải lớn hơn hoặc bằng 18")
    @Max(value = 65, message = "Tuổi nhân viên phải nhỏ hơn hoặc bằng 65")
    private Integer age;

    @NotNull(message = "Giới tính nhân viên không được để trống")
    private Boolean gender;

    @NotBlank(message = "Email nhân viên không được để trống")
    @Size(max = 50, message = "Email nhân viên không được vượt quá 50 ký tự")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 10, message = "Số điện thoại không được vượt quá 10 ký tự")
    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải gồm 10 chữ số")
    private String phone;

    @NotBlank(message = "Địa chỉ nhân viên không được để trống")
    private String address;

    @NotNull(message = "Phòng ban không được để trống")
    private Integer departmentId;

    @NotNull(message = "Trạng thái nhân viên không được để trống")
    private Integer status;

    private String avatarUrl;
}
