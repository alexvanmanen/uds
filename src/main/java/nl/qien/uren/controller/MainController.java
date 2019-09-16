package nl.qien.uren.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.qien.uren.entity.*;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.repository.*;
import nl.qien.uren.service.TimesheetService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
//    @Qualifier("JDBCTimesheetRepository")
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    @Autowired
    TimesheetService timesheetService;

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
    public int registerHours(@PathVariable Integer employeeId, @PathVariable Integer projectId, @PathVariable Integer numberOfHours, @PathVariable String datum) {
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
        System.out.println("newEmail = " + newEmail);
        boolean verstuurd = newEmail.sendMailText(email.getReceiver(), email.getSubject(), email.getMessage());
        return verstuurd;
    }


    @ApiOperation(value = "Get a timesheet based on an id", response = Timesheet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("getTimeSheet/{id}")
    public Optional<Timesheet> getTimesheet(@ApiParam(value = "Timesheet id from which timesheet object will retrieve", required = true) @PathVariable Integer id) {
        return timesheetRepository.findById(id);
    }

    @GetMapping("getAllTimeSheetsByProject/{projectId}")
    public List<Timesheet> getAllTimeSheetsByProject(@PathVariable Integer projectId) {
        return timesheetRepository.findAllByProjectId(projectId);
    }

    @GetMapping("getAllTimeSheetsByEmployee/{employeeId}")
    public List<Timesheet> getAllTimeSheetsByEmployee(@PathVariable Integer employeeId) {
        return timesheetRepository.findAllByUserId(employeeId);
    }

    @GetMapping("getAllTimeSheets")
    public List<Timesheet> getAllTimeSheets() {
        return timesheetRepository.findAll();
    }

    @PostMapping("/createCustomer")
    public boolean createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return false;
    }

    @PostMapping("/createTimesheet")
    public void createTimesheet(@RequestBody Timesheet timesheet) {
        timesheetRepository.save(timesheet);
        for (TimesheetEntry timesheetEntry : timesheet.getEntries()) {
            timesheetEntry.setTimesheet(timesheet);
            timesheetEntryRepository.save(timesheetEntry);
        }

    }

    @PostMapping("/updateTimesheet")
    public void updateTimesheet(@RequestBody Timesheet ts) {
        Timesheet timesheet = timesheetRepository.findById(ts.getId()).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + ts.getId()));;

        for (TimesheetEntry timesheetEntry : ts.getEntries()) {
            timesheetEntry.setTimesheet(timesheet);
            timesheetEntryRepository.save(timesheetEntry);
        }
        timesheet.setEntries(ts.getEntries());
        timesheetRepository.save(timesheet);

    }

    @PostMapping("/createTimesheetEntry")
    public TimesheetEntry createTimesheetEntry(@RequestBody TimesheetEntry timesheetEntry) {
        return timesheetEntryRepository.save(timesheetEntry);
    }


    @GetMapping("/getcustomers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User userDetails) {
        String password = RandomStringUtils.randomNumeric(8);
        String passencrypt = bCryptPasswordEncoder.encode(password);
        SendMail newEmail = new SendMail(userDetails.getUsername(), "Password", "Your password for the account is \\r\\n Login : " + userDetails.getUsername() +" \\r\\n password is: " + password);
        newEmail.sendMailText(userDetails.getUsername(), "Password", "Your password for the account is Login : " + userDetails.getUsername() +" and the password is: " + password);
        userDetails.setPassword(passencrypt);
        User newUser = userRepository.save(userDetails);
        ZoneId z = ZoneId.of("Europe/Paris");
        LocalDate today = LocalDate.now(z);
        System.out.println(today);
        String yearToday = today.toString().substring(0,4);
        String monthToday = today.toString().substring(5,7);
        System.out.println("yearToday =" +yearToday);
        System.out.println("monthToday =" + monthToday);
        return userRepository.save(userDetails);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);

    }

    /*
        PUT request with url
        http://localhost:8080/uren/api/v1/updateUser/1
        and json
        {
	"firstname" : "Johnny!",
	"lastname" : "Bravo!!!",
	"emailadress" : "john@oneManArmy.com"
}
     */
    @PutMapping("/updateUser/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));
        if (userDetails.getFirstname() != null) {
            user.setFirstname(userDetails.getFirstname());
        }
        if (userDetails.getLastname() != null) {
            user.setLastname(userDetails.getLastname());
        }
        if (userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        }
        if (userDetails.getStreet() != null) {
            user.setStreet(userDetails.getStreet());
        }
        if (userDetails.getHousenumber() != null) {
            user.setHousenumber(userDetails.getHousenumber());
        }
        if (userDetails.getCity() != null) {
            user.setCity(userDetails.getCity());
        }
        if (userDetails.getPhonenumber() != 0) {
            user.setPhonenumber(userDetails.getPhonenumber());
        }
        if (userDetails.getAccountnumber() != null) {
            user.setAccountnumber(userDetails.getAccountnumber());
        }
        if (userDetails.getZipcode() != null) {
         user.setZipcode(userDetails.getZipcode());
        }
        if (userDetails.getAvatar() != null) {
            user.setAvatar(userDetails.getAvatar());
        }
        if (userDetails.getAvatarcolor() != null) {
            user.setAvatarcolor(userDetails.getAvatarcolor());
        }
        return userRepository.save(user);
    }

    @PutMapping("/deactivateUser/{userId}")
    public User deactivateUser(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));
        user.setActive(false);
        return userRepository.save(user);
    }
    @PutMapping("/activateUser/{userId}")
    public User activateUser(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));
        user.setActive(true);
        return userRepository.save(user);
    }
}



