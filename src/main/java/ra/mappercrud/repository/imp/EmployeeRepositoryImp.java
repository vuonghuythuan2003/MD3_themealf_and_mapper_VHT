package ra.mappercrud.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.mappercrud.model.Employee;
import ra.mappercrud.repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
@Transactional
public class EmployeeRepositoryImp implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery(
                "SELECT e FROM Employee e JOIN FETCH e.department", Employee.class
        ).getResultList();
    }


    @Override
    public Employee findById(String id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public boolean save(Employee employee) {
        try{
            entityManager.persist(employee);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        try{
            entityManager.merge(employee);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            Employee employee = findById(id);
            if (employee != null) {
                entityManager.remove(employee);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee findByEmail(String email) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.email = :email", Employee.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public Employee findByPhone(String phone) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.phone = :phone", Employee.class);
        query.setParameter("phone", phone);
        return query.getResultStream().findFirst().orElse(null);
    }
}
