package ra.mappercrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.mappercrud.dto.reponse.DepartmentResponse;
import ra.mappercrud.dto.request.DepartmentCreateRequest;
import ra.mappercrud.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/departmentController")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<DepartmentResponse> listDepartment = departmentService.findAll();
        model.addAttribute("listDepartment", listDepartment);
        return "departmentList";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        DepartmentCreateRequest departmentRequest = new DepartmentCreateRequest();
        model.addAttribute("departmentRequest", departmentRequest);
        return "newDepartment";
    }

    @PostMapping("/create")
    public String create(DepartmentCreateRequest departmentRequest, Model model) {
        try {
            departmentService.create(departmentRequest);
            model.addAttribute("successMessage", "Department created successfully.");
            return "redirect:/departmentController/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error occurred while creating department.");
            return "error";
        }
    }


    @GetMapping("/initUpdate")
    public String initUpdate(@RequestParam("departmentId") int departmentId, Model model) {
        DepartmentResponse departmentResponse = departmentService.findById(departmentId);
        if (departmentResponse == null) {
            model.addAttribute("errorMessage", "Phòng ban không tồn tại!");
            return "error";
        }
        model.addAttribute("departmentResponse", departmentResponse);
        return "updateDepartment";
    }

    @PostMapping("/update")
    public String update(@RequestParam("departmentId") int departmentId, DepartmentCreateRequest departmentRequest, Model model) {
        try {
            departmentService.update(departmentId, departmentRequest);
            return "redirect:/departmentController/findAll";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật phòng ban!");
            return "error";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("departmentId") int departmentId, Model model) {
        try {
            departmentService.delete(departmentId);
            return "redirect:/departmentController/findAll";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi xóa phòng ban!");
            return "error";
        }
    }

}
