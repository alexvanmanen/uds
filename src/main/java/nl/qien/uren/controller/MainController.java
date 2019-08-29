package nl.qien.uren.controller;

import nl.qien.uren.model.UrenRegistratie;
import nl.qien.uren.model.user.Employee;
import nl.qien.uren.model.user.User;
import nl.qien.uren.repository.EmployeeRepository;
import nl.qien.uren.repository.UserRepository;
import nl.qien.uren.repository.UrenRegistratieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/urencount")
    public int getUrenCount() {
        return urenRegistratieRepository.count();
    }

    @PostMapping("/urenRegistratie")
    @ResponseBody
    public UrenRegistratie registerHours( @RequestBody UrenRegistratie urenregistratie) {
        urenRegistratieRepository.save(urenregistratie);
        return urenregistratie;
    }

    @GetMapping("/createuser/{email}/{password}")
    @ResponseBody
    public User register(@PathVariable String email, @PathVariable String password){
        long id = userRepository.getMaxId();
        User user = new User(id, email,password);
        userRepository.save(user);
        return user;
    }

    @PostMapping("/createuser")
    @ResponseBody
    public User register(@RequestBody User user){
        long id = userRepository.getMaxId();
        User newUser = new User(id, user.getEmailAdress(),user.getPassword());
        userRepository.save(newUser);
        return newUser;
    }


//    @GetMapping("getTimeSheet/{employeeId}")
//    public UrenRegistratie getTimesheet(@PathVariable Integer employeeId){
//       UrenRegistratie urenRegistratie = new UrenRegistratie(1,3,0, "1928-01-01", new WerkType("verlof"));
//       return urenRegistratie;
//
//    }
}
