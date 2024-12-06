package ra.mappercrud.service;

import ra.mappercrud.dto.reponse.EmployeeReponse;
import ra.mappercrud.dto.request.EmployeeCreateRequest;
import ra.mappercrud.dto.request.EmployeeUpdateRequest;
import ra.mappercrud.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeReponse> findAll();
    EmployeeReponse findById(String id);
    EmployeeReponse save(EmployeeCreateRequest request);
    EmployeeReponse update(String id, EmployeeCreateRequest request);
    void deleteById(String id);

}
