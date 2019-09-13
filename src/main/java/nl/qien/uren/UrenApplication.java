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
        Customer customerRobin = new Customer("Robin", "Coes", "sigma_fi19@yahoo.com", bCryptPasswordEncoder.encode("Robin01"));
        Customer customerBart = new Customer("Bart", "Zwaagstra", "bartzwaagstra@live.nl", bCryptPasswordEncoder.encode("Bart01"));
        customerRepository.saveAll(Arrays.asList(customerRobin, customerBart));

        Employee employeeAlex = new Employee("Alex", "van Manen", "alex@vanmanenit.nl", bCryptPasswordEncoder.encode("Alex01"));
        Employee employeeBen = new Employee("Ben", "Vosse", "benvosse@hotmail.com", bCryptPasswordEncoder.encode("Ben01"));
        employeeRepository.saveAll(Arrays.asList(employeeAlex,employeeBen));

        Admin cora = new Admin("Cora", "de Lima-Roos", "admin@qien.nl", bCryptPasswordEncoder.encode("Admin01"));
        adminRepository.save(cora);

        Set<Project> projectsA = new HashSet<>();
        Project projectVanCustomerRobin = new Project("Project van customer Robin", customerRobin);
        projectsA.add(projectVanCustomerRobin);
        customerRobin.setProjects(projectsA);
        projectRepository.saveAll(projectsA);

        Set<Project> projectsVanCustomerBart = new HashSet<>();
        projectsVanCustomerBart.add(new Project("Project van customer Bart", customerBart));
        customerBart.setProjects(projectsVanCustomerBart);
        projectRepository.saveAll(projectsVanCustomerBart);


        Timesheet timesheetAlexAug = new Timesheet(projectVanCustomerRobin, employeeAlex, YearMonth.of(2019,8), TimesheetState.PENDING);
        Timesheet timesheetAlexJul = new Timesheet(projectVanCustomerRobin, employeeAlex, YearMonth.of(2019,7), TimesheetState.DECLINED);
        Timesheet timesheetBenAug = new Timesheet(projectVanCustomerRobin, employeeBen, YearMonth.of(2019,8), TimesheetState.APPROVED);
        Timesheet timesheetBenJul = new Timesheet(projectVanCustomerRobin, employeeBen, YearMonth.of(2019,7), TimesheetState.APPROVED);

        timesheetRepository.saveAll(Arrays.asList(timesheetAlexAug, timesheetAlexJul, timesheetBenAug, timesheetBenJul));

        TimesheetEntry timesheetEntry1 = new TimesheetEntry(1, 8, EntryKind.WORK, timesheetAlexAug);
        TimesheetEntry timesheetEntry2 = new TimesheetEntry(1, 4, EntryKind.LEAVE_OF_ABSENCE, timesheetAlexAug);

        TimesheetEntry timesheetEntry3 = new TimesheetEntry(5, 4, EntryKind.LEAVE_OF_ABSENCE, timesheetBenAug);
        timesheetAlexAug.setCustomerKey("8928308ALEX87283279");
        timesheetEntryRepository.saveAll(Arrays.asList(timesheetEntry1,timesheetEntry2, timesheetEntry3));
    }


}
