package ra.mappercrud.service;

import ra.mappercrud.dto.reponse.DepartmentResponse;
import ra.mappercrud.dto.request.DepartmentCreateRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> findAll();

    DepartmentResponse findById(int id);

    DepartmentResponse create(DepartmentCreateRequest request);

    DepartmentResponse update(int id, DepartmentCreateRequest request);

    void delete(int id);
}
