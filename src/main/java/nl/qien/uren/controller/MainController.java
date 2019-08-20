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
        Employee employee = new Employee("Alex", "van Manen");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }

    public boolean checkPassword (String emailAdress, String password){
        boolean b = true;
        List <User> users = getUsers();
        for (User a : users) {
            b = emailAdress.equals(a.getEmailAdress()) && password.equals(a.getPassword());

        }
        return b;
    }

    private List<User> getUsers() {
        List <User> users = new ArrayList<>();
        users.add( new User ("hallo@hallo.com", "hallo"));
        users.add( new User( "kalim@hallo.com", "hoi"));
        return users;



    }


}
