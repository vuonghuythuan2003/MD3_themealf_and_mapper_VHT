package ra.mappercrud.dto.reponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeReponse {
    private String id;
    private String name;
    private Integer age;
    private Boolean geader;
    private String email;
    private String phone;
    private String address;
    private String departmentName;
    private Integer status;
    private String avatarUrl;
}
