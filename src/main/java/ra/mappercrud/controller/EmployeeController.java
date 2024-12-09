package ra.mappercrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.mappercrud.dto.reponse.DepartmentResponse;
import ra.mappercrud.dto.reponse.EmployeeReponse;
import ra.mappercrud.dto.request.EmployeeCreateRequest;
import ra.mappercrud.service.DepartmentService;
import ra.mappercrud.service.EmployeeService;

import java.util.List;
@Controller
@RequestMapping("/employeeController")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;


    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<EmployeeReponse> listEmployee = employeeService.findAll();
        model.addAttribute("listEmployee", listEmployee);
        return "employeeList";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest();
        model.addAttribute("employeeCreateRequest", employeeCreateRequest);
        List<DepartmentResponse> listDepartments = departmentService.findAll();
        model.addAttribute("listDepartments", listDepartments);
        return "employeeCreate";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute EmployeeCreateRequest employeeCreateRequest,
                         @RequestParam("avatarUrl") MultipartFile avatarFile, Model model) {
        try {
            employeeService.save(employeeCreateRequest, avatarFile);
            return "redirect:/employeeController/findAll";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Thêm thất bại: " + e.getMessage());
            return "employeeCreate";
        }
    }

    @GetMapping("/initUpdate")
    public String initUpdate(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            EmployeeReponse employeeResponse = employeeService.findById(employeeId);
            model.addAttribute("employeeReponse", employeeResponse);
            List<DepartmentResponse> listDepartments = departmentService.findAll();
            model.addAttribute("listDepartments", listDepartments);
            return "employeeUpdate";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không tìm thấy nhân viên với ID: " + employeeId);
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("employeeId") String employeeId,
                         @ModelAttribute EmployeeCreateRequest create,
                         @RequestParam("avatarUrl") MultipartFile avatarFile, Model model) {
        try {


            employeeService.update(employeeId, create, avatarFile);
            return "redirect:/employeeController/findAll";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Cập nhật thất bại: " + e.getMessage());
            return "employeeUpdate";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            employeeService.deleteById(employeeId);
            return "redirect:/employeeController/findAll";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Xóa thất bại: " + e.getMessage());
            return "error";
        }
    }
}
