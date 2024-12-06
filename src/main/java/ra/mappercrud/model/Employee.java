package ra.mappercrud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @Column(name = "emp_id", length = 5)
    private String id;

    @Column(name = "emp_name", length = 50, nullable = false)
    private String name;

    @Column(name = "emp_age", nullable = false)
    private Integer age;

    @Column(name = "emp_sex", nullable = false)
    private Boolean gender;

    @Column(name = "emp_email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "emp_phone", length = 10, nullable = false, unique = true)
    private String phone;

    @Column(name = "emp_address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;
    @Column(name = "emp_status", nullable = false)
    private Integer status;

    @Column(name = "avatar_url")
    private String avatarUrl;
}
