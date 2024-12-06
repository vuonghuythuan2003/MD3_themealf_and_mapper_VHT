package ra.mappercrud.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.mappercrud.model.Department;
import ra.mappercrud.repository.DepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class DepartmentRepositoryImp implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    @Override
    public Department findById(int id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public boolean save(Department department) {
        try {
            System.out.println("Saving entity: " + department);
            entityManager.persist(department);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean update(Department department) {
        try{
            entityManager.merge(department);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int depId) {
        try{
            entityManager.remove(entityManager.find(Department.class, depId));
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
