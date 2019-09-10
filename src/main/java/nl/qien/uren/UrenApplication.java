package nl.qien.uren;

import nl.qien.uren.entity.*;
import nl.qien.uren.model.EntryKind;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class UrenApplication  implements CommandLineRunner {

    @Autowired
    TimesheetEntryRepository timesheetEntryRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(UrenApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {



        Customer customerA = new Customer();
        Customer customerB = new Customer();
        Employee employeeA = new Employee();
        Employee employeeB = new Employee();
        customerA.setUsername("bartzwaagstra@live.nl");
        customerA.setFirstname("Bart");
        customerA.setPassword(bCryptPasswordEncoder.encode("Bart01"));
        customerA.setActive(true);

        Set<Project> projectsA = new HashSet<>();
        Project projectA1 = new Project("Project A1", customerA);
        projectsA.add(projectA1);
        customerA.setProjects(projectsA);


        Set<Project> projectsB = new HashSet<>();
        projectsB.add(new Project("Project B3", customerB));
        projectsB.add(new Project("Project B4", customerB));
        customerB.setProjects(projectsB);


        customerRepository.saveAll(Arrays.asList(customerA, customerB));
        projectRepository.saveAll(projectsA);
        projectRepository.saveAll(projectsB);
        employeeRepository.save(employeeA);
        employeeRepository.save(employeeB);


        //show the info
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }

        Admin cora = new Admin();
        cora.setPassword(bCryptPasswordEncoder.encode("Admin01"));
        cora.setUsername("admin@qien.nl");
        cora.setActive(true);
        adminRepository.save(cora);


        Employee alex = new Employee();
        alex.setFirstname("Alex");
        Employee bart = new Employee();
        bart.setFirstname("bart");

        employeeRepository.saveAll(Arrays.asList(alex,bart));


        Timesheet timesheetAlexAug = new Timesheet(projectA1, alex, YearMonth.of(2019,8), TimesheetState.OPEN);
        Timesheet timesheetAlexJul = new Timesheet(projectA1, alex, YearMonth.of(2019,7), TimesheetState.DECLINED);
        Timesheet timesheetBart = new Timesheet(projectA1, bart, YearMonth.of(2019,1), TimesheetState.APPROVED);
        Timesheet timesheetBart2018 = new Timesheet(projectA1, bart, YearMonth.of(2018,2), TimesheetState.APPROVED);

        timesheetRepository.save(timesheetAlexAug);
        timesheetRepository.save(timesheetAlexJul);

        timesheetRepository.save(timesheetBart);
        timesheetRepository.save(timesheetBart2018);


        TimesheetEntry timesheetEntry1 = new TimesheetEntry(1, 8, EntryKind.WORK, timesheetAlexAug);
        TimesheetEntry timesheetEntry2 = new TimesheetEntry(2, 4, EntryKind.LEAVE_OF_ABSENCE, timesheetAlexAug);

        TimesheetEntry timesheetEntry3 = new TimesheetEntry(5, 4, EntryKind.LEAVE_OF_ABSENCE, timesheetBart);


        SendMail sendMail = new SendMail();

        timesheetAlexAug.setCustomerKey("8928308ALEX87283279");
        sendMail.sendMail(timesheetAlexAug);

//        SELECT UREN, ENTRY_KIND FROM USER
//        INNER JOIN TS ON(USER.ID=TS.USER)
//        INNER JOIN TSENTRY ON (TS.ID=TSENTRY.TIMESHEET_ID)
//        WHERE FIRSTNAME='Alex'


        timesheetEntryRepository.saveAll(Arrays.asList(timesheetEntry1,timesheetEntry2, timesheetEntry3));
    }
}
