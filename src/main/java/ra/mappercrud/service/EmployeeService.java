package ra.mappercrud.service;

import org.springframework.web.multipart.MultipartFile;
import ra.mappercrud.dto.reponse.EmployeeReponse;
import ra.mappercrud.dto.request.EmployeeCreateRequest;
import ra.mappercrud.dto.request.EmployeeUpdateRequest;
import ra.mappercrud.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<EmployeeReponse> findAll();
    EmployeeReponse findById(String id);
    EmployeeReponse save(EmployeeCreateRequest request, MultipartFile avatarFile) throws IOException;
    EmployeeReponse update(String id, EmployeeCreateRequest request, MultipartFile avatarFile) throws IOException;
    void deleteById(String id);

}
