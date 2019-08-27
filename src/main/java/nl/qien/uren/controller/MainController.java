package nl.qien.uren.controller;

import nl.qien.uren.repository.EmployeeRepository;
import nl.qien.uren.repository.JDBCUserRepository;
import nl.qien.uren.repository.UserRepository;
import nl.qien.uren.repository.UrenRegistratieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    @Qualifier("JDBCUrenRegistratieRepository")
    private UrenRegistratieRepository urenRegistratieRepository;

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    @GetMapping("/users")
    public List<User> findAlluser() {
        return userRepository.findAll();
    }
    @GetMapping("/getmonthdays/{year}/{month}")
    public int getDaysInMonth(@PathVariable int year, @PathVariable int month) {
        int uren = UrenRegistratie.daysInMonth(year, month);
        return uren;
    }
    @GetMapping("/checkPassword/{email}/{password}")
    @ResponseBody
    public boolean checkPassword(@PathVariable String email, @PathVariable String password) {
        return userRepository.validateUser(email, password);
    }

    @GetMapping("/count")
    public int getNumberOfEmployees(){
        return employeeRepository.count()+1;
    }

    @GetMapping("/addEmployee/{id}/{firstName}/{lastName}")
    public int addEmployee(@PathVariable Long id, @PathVariable String firstName, @PathVariable String lastName){
        return employeeRepository.save(new Employee(id, firstName,lastName));

    }

    @GetMapping("/urenRegistratie/{employeeId}/{projectId}/{aantalUren}/{datum}")
    public int registerHours(@PathVariable Long employeeId, @PathVariable Long projectId, @PathVariable Long aantalUren, @PathVariable String datum){
        return urenRegistratieRepository.save(new UrenRegistratie(employeeId, projectId,aantalUren,datum));

    }

    @GetMapping("/urenRegistratie/count")
    public int registerHours() {
        return urenRegistratieRepository.count();
    }


    @GetMapping("/createuser/{email}/{password}")
    @ResponseBody
    public User register(@PathVariable String email, @PathVariable String password){
        long id = userRepository.getMaxId();
        User user = new User(id, email,password);
        userRepository.save(user);
        return user;
    }
}
