package nl.qien.uren.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class MainController {


    @GetMapping("/rulezz/{name}")
    public String kalim(@PathVariable String name){
        return "<H1>DIT IS ECHT TOP</H1> <font color='red'>"+name +" rulezzz</font>";
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        Employee employee = new Employee("Alex", "van Manen");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }

    @GetMapping("/checkPassword/{email}/{password}")
    @ResponseBody
    public boolean checkPassword(@PathVariable String email, @PathVariable String password) {
        List<User> users = getUsers();
        for (User a : users) {
            if(email.equals(a.getEmailAdress()) && password.equals(a.getPassword())){
                return true;
            }
        }
        return false;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("hallo@hallo.com", "hallo"));
        users.add(new User("kalim@hallo.com", "hoi"));
        return users;


    }
//    @GetMapping("/register/{email}/{password}")
//    @ResponseBody
//    public User register(String email, String pass){
//        User user = new User(email,pass);
//        return user;
//    }

}
