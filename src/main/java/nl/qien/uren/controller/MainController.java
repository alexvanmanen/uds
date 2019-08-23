package nl.qien.uren.controller;

import nl.qien.uren.repository.EmployeeRepository;
import nl.qien.uren.repository.JDBCUserRepository;
import nl.qien.uren.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("JDBCEmployeeRepository")
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/rulezz/{name}")
    public String kalim(@PathVariable String name){
        return "<H1>DIT IS ECHT TOP</H1> <font color='red'>"+name +" rulezzz</font>";
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
//        Employee employee = new Employee(new Long(1),"Alex", "van Manen");
//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        return employees;
    }

    @GetMapping("/checkPassword/{email}/{password}")
    @ResponseBody
    public boolean checkPassword(@PathVariable String email, @PathVariable String password) {
        return userRepository.validateUser(email, password);
//        List<User> users = getUsers();
//        for (User a : users) {
//            if(email.equals(a.getEmailAdress()) && password.equals(a.getPassword())){
//                return true;
//            }
//        }
//        return false;
    }
//
//    private List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//        users.add(new User(1,"hallo@hallo.com", "hallo"));
//        users.add(new User(2, "kalim@hallo.com", "hoi"));
//        return users;
//
//
//    }
    @GetMapping("/count")
    public int getNumberOfEmployees(){
        return employeeRepository.count()+1;
    }

    @GetMapping("/addEmployee/{id}/{firstName}/{lastName}")
    public int addEmployee(@PathVariable Long id, @PathVariable String firstName, @PathVariable String lastName){
        return employeeRepository.save(new Employee(id, firstName,lastName));

    }
    @GetMapping("/urenRegistratie/{employeeId}/{projectId}/{aantalUren}")
    public int urenRegistratie(@PathVariable Long employeeId, @PathVariable Long projectId, @PathVariable Long aantalUren){
        UrenRegistratie uren = new UrenRegistratie(); //argumenten meegeven
        return employeeRepository.saveUren(uren);
    }

//    @GetMapping("/employee/{id}")
//    public Optional<Employee> getEmployee(@PathVariable Long id){
//        return employeeRepository.findById(id);
//    }

//    @GetMapping("/createuser/{email}/{password}")
//    @ResponseBody
//    public User register(String email, String password){
//        User user = new User(email,password);
//
//        return user;
//    }

}
