package ra.mappercrud.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer id;

    @Column(name = "dept_name", nullable = false, unique = true)
    private String name;

    @Column(name = "dept_no", nullable = false)
    private String deptNo;

    @Column(name = "dept_status", nullable = false)
    private Boolean status;

//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Employee> employees;
}
