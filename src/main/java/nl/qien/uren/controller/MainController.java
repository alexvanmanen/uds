package nl.qien.uren.controller;

import nl.qien.uren.entity.Customer;
import nl.qien.uren.model.EntryKind;
import nl.qien.uren.model.Project;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


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
    @Qualifier("JDBCTSRepository")
    private TimesheetRepository timesheetRepository;

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
        Timesheet timesheet = new Timesheet(new Project(), new Employee(), YearMonth.of(year,month));
        timesheet.addHourEntry(8,8, EntryKind.WORK);
        timesheet.addHourEntry(8,9, EntryKind.WORK);
        return timesheet.getTotalHours();
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
        LocalDate date = LocalDate.parse(datum);
        YearMonth yearMonth = YearMonth.of(date.getYear(),date.getMonth());
        Project project = new Project();
        project.setId(projectId);
        Employee employee = new Employee();
        employee.setId(employeeId);
        Timesheet timesheet = new Timesheet(project, employee, yearMonth);
        timesheet.addHourEntry(numberOfHours,date.getDayOfMonth(), EntryKind.WORK);

        return timesheetRepository.save(timesheet);

    }

    @GetMapping("/urenRegistratie/count")
    public int registerHours() {
        return timesheetRepository.count();
    }


    @PostMapping("/sendmail")
    @ResponseBody
    public boolean sendMail(@RequestBody SendMail email) {
        SendMail newEmail = new SendMail(email.getReceiver(), email.getSubject(), email.getMessage());
        boolean verstuurd = newEmail.sendMail(email.getReceiver(), email.getSubject(), email.getMessage());
        return verstuurd;
    }


//    @GetMapping("getTimeSheet/{employeeId}")
//    public Timesheet getTimesheet(@PathVariable Integer employeeId){
//        Employee employee = new Employee(1,"alex", "van Manen");
//        Timesheet timesheet = new Timesheet(new Project(), employee, YearMonth.of(2019,8));
//        timesheet.addHourEntry(8,14, EntryKind.LEAVE_OF_ABSENCE);
//        timesheet.addHourEntry(8,15, EntryKind.WORK);
//        return timesheet;
//    }

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


