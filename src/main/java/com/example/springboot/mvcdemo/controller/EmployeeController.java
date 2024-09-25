package com.example.springboot.mvcdemo.controller;

import com.example.springboot.mvcdemo.entity.Employee;
import com.example.springboot.mvcdemo.service.EmployeeService;
import com.example.springboot.mvcdemo.service.PdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private PdfService pdfService;

    public EmployeeController(EmployeeService employeeService,PdfService pdfService) {
        this.employeeService = employeeService;
        this.pdfService = pdfService;
    }


    //add mapping for /list
    @GetMapping("/list")
    public String listEmployees(Model model) {
        //get employees from the database
        List<Employee> theEmployees = employeeService.findAllByOrderByLastNameAsc();

        //add to the model
        model.addAttribute("employees" , theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFromForAdd")
    public String showFormForAdd (Model model) {
        Employee employee =  new Employee();
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFromForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model) {
        //get the employee from the service
        Employee theEmployee = employeeService.findById(theId);
        //employee in the model to populate the form
        model.addAttribute("employee", theEmployee);
        //send over to our from

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {
        System.out.println("In delete .....");
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        //save the employee
        employeeService.save(employee);

        //Use a redirect to prevent duplicate submission
        return "redirect:/employees/list";
    }
}
