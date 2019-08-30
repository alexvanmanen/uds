package nl.qien.uren;

import nl.qien.uren.entity.*;
import nl.qien.uren.model.EntryKind;
import nl.qien.uren.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class UrenApplication  implements CommandLineRunner {

    @Autowired
    TimeSheetEntryRepository timeSheetEntryRepository;

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

        Set<Project> projectsA = new HashSet<>();
        projectsA.add(new Project("Project A1", customerA));
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
        adminRepository.save(cora);

        Timesheet timesheet = new Timesheet(new nl.qien.uren.model.Project(), new Employee(), YearMonth.of(2019,8));
        timesheetRepository.save(timesheet);

        TimesheetEntry timesheetEntry = new TimesheetEntry(new Date(), 8, EntryKind.WORK, timesheet);
        timeSheetEntryRepository.save(timesheetEntry);


    }
}
