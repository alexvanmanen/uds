package nl.qien.uren.controller;

import nl.qien.uren.entity.Customer;
import nl.qien.uren.model.EntryKind;
import nl.qien.uren.model.Project;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.model.Timeshit;
import nl.qien.uren.model.user.Employee;
import nl.qien.uren.model.user.User;
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
    @Qualifier("JDBCEmployeeRepository")
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("JDBCTimesheetRepository")
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
        Timeshit timeshit = new Timeshit(new Project(), new Employee(), YearMonth.of(year,month));
        timeshit.addHourEntry(8,8, EntryKind.WORK);
        timeshit.addHourEntry(8,9, EntryKind.WORK);
        return timeshit.getTotalHours();
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
    public int addEmployee(@PathVariable Integer id, @PathVariable String firstName, @PathVariable String lastName){
        return employeeRepository.save(new Employee(id, firstName,lastName));

    }

    @GetMapping("/urenRegistratie/{employeeId}/{projectId}/{numberOfHours}/{datum}")
    public int registerHours(@PathVariable Integer employeeId, @PathVariable Integer projectId, @PathVariable Integer numberOfHours, @PathVariable String datum){
        LocalDate date = LocalDate.parse(datum);
        YearMonth yearMonth = YearMonth.of(date.getYear(),date.getMonth());
        Project project = new Project();
        project.setId(projectId);
        Employee employee = new Employee();
        employee.setId(employeeId);
        Timeshit timeshit = new Timeshit(project, employee, yearMonth);
        timeshit.addHourEntry(numberOfHours,date.getDayOfMonth(), EntryKind.WORK);

        return timesheetRepository.save(timeshit);

    }

    @GetMapping("/urenRegistratie/count")
    public int registerHours() {
        return timesheetRepository.count();
    }

    @PostMapping("/createuser")
    @ResponseBody
    public User register(@RequestBody User user){
        int id = userRepository.getMaxId();
        User newUser = new User(id, user.getFirstname(), user.getLastname(), true, user.getEmailadress(), RandomStringUtils.randomNumeric(8), null, null,null,null,1,null,true);
        userRepository.save(newUser);
        return newUser;
    }
    @PostMapping("/sendmail")
    @ResponseBody
    public boolean sendMail(@RequestBody SendMail email){
        SendMail newEmail = new SendMail(email.getReceiver(), email.getSubject(), email.getMessage());
        boolean verstuurd = newEmail.sendMail(email.getReceiver(), email.getSubject(), email.getMessage());
        return verstuurd;
    }


    @GetMapping("getTimeSheet/{employeeId}")
    public Timeshit getTimesheet(@PathVariable Integer employeeId){
        Employee employee = new Employee(1,"alex", "van Manen");
        Timeshit timeshit = new Timeshit(new Project(), employee, YearMonth.of(2019,8));
        timeshit.addHourEntry(8,14, EntryKind.LEAVE_OF_ABSENCE);
        timeshit.addHourEntry(8,15, EntryKind.WORK);
        return timeshit;
    }

    @PostMapping("/createCustomer")
    public boolean createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return false;
    }

    @PostMapping("/createTimeSheet")
    public void createTimesheet(@RequestBody Timeshit timeshit) {
        timesheetRepository.save(timeshit);
    }

    @GetMapping("/getcustomers")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

}
