package nl.qien.uren.controller;

import nl.qien.uren.entity.Customer;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.Employee;
import nl.qien.uren.entity.User;
import nl.qien.uren.repository.CustomerRepository;
import nl.qien.uren.repository.EmployeeRepository;
import nl.qien.uren.repository.TimesheetRepository;
import nl.qien.uren.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
//    @Qualifier("JDBCTimesheetRepository")
    private TimesheetRepository timesheetRepository;

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> findAlluser() {
        return userRepository.findAll();
    }

    @GetMapping("/checkPassword/{email}/{password}")
    @ResponseBody
    public boolean checkPassword(@PathVariable String email, @PathVariable String password) {
        //return userRepository.validateUser(email, password);
        return true;
    }

    @GetMapping("/count")
    public long getNumberOfEmployees() {
        return employeeRepository.count() + 1;
    }


    @GetMapping("/urenRegistratie/{employeeId}/{projectId}/{numberOfHours}/{datum}")
    public int registerHours(@PathVariable Integer employeeId, @PathVariable Integer projectId, @PathVariable Integer numberOfHours, @PathVariable String datum){
        return 0;
    }

    @GetMapping("/urenRegistratie/count")
    public int registerHours() {
        return 0;
    }


    @PostMapping("/sendmail")
    @ResponseBody
    public boolean sendMail(@RequestBody SendMail email) {
        SendMail newEmail = new SendMail(email.getReceiver(), email.getSubject(), email.getMessage());
        boolean verstuurd = newEmail.sendMail(email.getReceiver(), email.getSubject(), email.getMessage());
        return verstuurd;
    }


    @GetMapping("getTimeSheet/{id}")
    public Optional<Timesheet> getTimesheet(@PathVariable Integer id){
        return timesheetRepository.findById(id);
    }

    @GetMapping("getAllTimeSheetsByProject/{projectId}")
    public List<Timesheet> getAllTimeSheetsByProject(@PathVariable Integer projectId){
        return timesheetRepository.findAllByProjectId(projectId);
    }

    @GetMapping("getAllTimeSheetsByEmployee/{employeeId}")
    public List<Timesheet> getAllTimeSheetsByEmployee(@PathVariable Integer employeeId){
        return timesheetRepository.findAllByUserId(employeeId);
    }

    @GetMapping("getAllTimeSheets")
    public List<Timesheet> getAllTimeSheets(){
        return timesheetRepository.findAll();
    }

    @PostMapping("/createCustomer")
    public boolean createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return false;
    }

    @PostMapping("/createTimeSheet")
    public void createTimesheet(@RequestBody Timesheet timesheet) {
        timesheetRepository.save(timesheet);
    }

    @GetMapping("/getcustomers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        user.setPassword(RandomStringUtils.randomNumeric(8));
        return userRepository.save(user);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);

    }
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
       return userRepository.save(user);

    }
}


