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

import java.time.YearMonth;
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
    private UserRepository userRepository;

    @Autowired
//    @Qualifier("JDBCTimesheetRepository")
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    @Autowired
    private ProjectRepository projectRepository;

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

    @PostMapping("/createProject")
    public boolean createProject(@RequestBody Project project) {
        projectRepository.save(project);
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
        Timesheet timesheet = timesheetRepository.findById(ts.getId()).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + ts.getId()));
        ;

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


    @GetMapping("/getprojects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody Employee userDetails) {
        String password = RandomStringUtils.randomNumeric(8);
        String passencrypt = bCryptPasswordEncoder.encode(password);
        SendMail newEmail = new SendMail(userDetails.getUsername(), "Password", "Your password for the account is \\r\\n Login : " + userDetails.getUsername() + " \\r\\n password is: " + password);
        newEmail.sendMailText(userDetails.getUsername(), "Password", "Your password for the account is Login : " + userDetails.getUsername() + " and the password is: " + password);
        userDetails.setPassword(passencrypt);
        int projectid = userDetails.getProject().getId();
        //SUPER SMELLY CODE :)
        userDetails.setProject(projectRepository.findById(projectid));
        Employee newUser = userRepository.save(userDetails);
        timesheetService.createTimesheetForEmployee(projectRepository.findById(projectid), newUser, YearMonth.now(), TimesheetState.OPEN);
        return newUser;
    }
    @PutMapping("/editProject")
    public User editProject(@RequestBody Employee userDetails) {
        Employee emp = employeeRepository.findById(userDetails.id);
        int projectid = userDetails.getProject().getId();
        //SUPER SMELLY CODE :)
        emp.setProject(projectRepository.findById(projectid));
         userRepository.save(emp);
       return emp;
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

    @GetMapping("/getProject/{id}")
    public Project getProject(@PathVariable int id) {
        return projectRepository.findById(id);
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
        if (userDetails.getPhonenumber() != null) {
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
        if (userDetails.getPhonenumber() != null) {
            user.setPhonenumber(userDetails.getPhonenumber());
        }
        if (userDetails.getAccountnumber() != null) {
            user.setAccountnumber(userDetails.getAccountnumber());
        }
        if (userDetails.getDateofbirth() != null) {
            user.setDateofbirth(userDetails.getDateofbirth());
        }
        return userRepository.save(user);
    }

    @PutMapping("/deactivateUser/{userId}")
    public User deactivateUser(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));
        user.setActive(false);
        return userRepository.save(user);


        }

    @PutMapping("/deactivateProject/{projectId}")
    public Project deactivateProject(@PathVariable int projectId){
        Project project= projectRepository.findById(projectId);
        project.setActive(false);
        return projectRepository.save(project);
    }


    @PutMapping("/activateProject/{projectId}")
    public Project activateProject(@PathVariable int projectId) {
        Project project = projectRepository.findById(projectId);
        project.setActive(true);
        return projectRepository.save(project);
    }

    @PutMapping("/activateUser/{userId}")
    public User activateUser(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found for this id :: " + userId));
        user.setActive(true);
        return userRepository.save(user);
    }

    @PostMapping("/forgotPassword")
    public void forgotPassword(@RequestBody User userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        user.setPasswordKey(KeyGenerator.generateKey());
        userRepository.save(user);
        new SendMail().sendMail(user);
    }

    @PostMapping("/setNewPassword/{id}/{passwordKey}")
    public void setNewPassword(@RequestBody User userDetails, @PathVariable String passwordKey, @PathVariable int id) {
        User user = userRepository.findByPasswordKey(passwordKey);
        String password = userDetails.getPassword();
        String passencrypt = bCryptPasswordEncoder.encode(password);
        SendMail newEmail = new SendMail(user.getUsername(), "Password", "Your password for the account is \\r\\n Login : " + user.getUsername() + " \\r\\n password is: " + password);
        newEmail.sendMailText(user.getUsername(), "Password", "Your password for the account is Login : " + user.getUsername() + " and the password is: " + password);
        user.setPassword(passencrypt);
        user.setPasswordKey(null);
        userRepository.save(user);
    }
}



