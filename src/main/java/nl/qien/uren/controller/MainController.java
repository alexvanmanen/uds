package nl.qien.uren.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class MainController {


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        Employee employee = new Employee("Alex", "van Manen", "alexvanmanen@hotmail.com");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }


}
