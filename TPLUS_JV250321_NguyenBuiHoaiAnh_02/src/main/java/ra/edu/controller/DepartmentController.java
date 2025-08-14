package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.Department;
import ra.edu.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/department"})
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/list")
    public String department(Model model) {
        List<Department> departmentList = departmentService.findAll();

        model.addAttribute("departmentList", departmentList);
        return "department/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "department/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("department") Department department,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "department/add";
        }

        boolean add = departmentService.create(department);
        if (add) {
            return "redirect:/department";
        } else {
            model.addAttribute("department", department);
            return "department/add";
        }
    }

    @GetMapping("/update/{id}")
    public String initUpdate(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department/update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("department") Department department,
                         BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            return "department/update";
        }
        boolean update = departmentService.update(department);
        System.out.println(update);
        if (update) {
            return "redirect:/department/list";
        } else {
            model.addAttribute("department", department);
            return "department/update";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {

        boolean delete = departmentService.delete(id);
        if (delete) {
            return "redirect:/department/list";
        } else {
            return "redirect:/department/list";
        }
    }
}