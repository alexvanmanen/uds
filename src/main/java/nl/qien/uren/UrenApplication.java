package nl.qien.uren;

import nl.qien.uren.entity.*;
import nl.qien.uren.model.EntryKind;
import nl.qien.uren.repository.*;
import nl.qien.uren.service.TimesheetService;
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
    TimesheetService timesheetService;

    @Autowired
    TimesheetEntryRepository timesheetEntryRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

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

        Set<Project> projectsQien = new HashSet<>();
        Project projectVanCustomerQien = new Project("Qien", "admin@qien.nl", "38739874534");
        projectsQien.add(projectVanCustomerQien);
        projectRepository.saveAll(projectsQien);

        Set<Project> projectsA = new HashSet<>();
        Project projectVanCustomerRobin = new Project("Robin's Dojo", "sigma_fi19@yahoo.com", "43509834873");
        projectsA.add(projectVanCustomerRobin);
        projectRepository.saveAll(projectsA);

        Set<Project> projectsVanCustomerBart = new HashSet<>();
        projectsVanCustomerBart.add(new Project("Bart's Bingo Paleis", "bartzwaagstra@live.nl", "34598348753"));
        projectRepository.saveAll(projectsVanCustomerBart);


        Employee employeeAlex = new Employee("Alex", "van Manen", "alex@vanmanenit.nl", bCryptPasswordEncoder.encode("Alex01"));
        employeeAlex.setProject(projectRepository.findById(1));
        Employee employeeBen = new Employee("Ben", "Vosse", "benvosse@hotmail.com", bCryptPasswordEncoder.encode("Ben01"));
        employeeBen.setProject(projectRepository.findById(2));
        employeeRepository.saveAll(Arrays.asList(employeeAlex,employeeBen));

        Admin cora = new Admin("Cora", "de Lima-Roos", "admin@qien.nl", bCryptPasswordEncoder.encode("Admin01"));
        adminRepository.save(cora);

        Timesheet timesheetAlexAug = new Timesheet(projectVanCustomerRobin, employeeAlex, YearMonth.of(2019,8), TimesheetState.AFWACHTEND);
        Timesheet timesheetAlexJul = new Timesheet(projectVanCustomerRobin, employeeAlex, YearMonth.of(2019,7), TimesheetState.AFGEKEURD);
        Timesheet timesheetBenAug = new Timesheet(projectVanCustomerRobin, employeeBen, YearMonth.of(2019,8), TimesheetState.GOEDGEKEURD);
        Timesheet timesheetBenJul = new Timesheet(projectVanCustomerRobin, employeeBen, YearMonth.of(2019,7), TimesheetState.GOEDGEKEURD);
        Timesheet timesheetBenSep = new Timesheet(projectVanCustomerRobin, employeeBen, YearMonth.of(2019,9), TimesheetState.OPEN);

        timesheetRepository.saveAll(Arrays.asList(timesheetAlexAug, timesheetAlexJul, timesheetBenAug, timesheetBenJul, timesheetBenSep));

        TimesheetEntry timesheetEntry1 = new TimesheetEntry(1, 8, EntryKind.WORK, timesheetAlexAug);
        TimesheetEntry timesheetEntry2 = new TimesheetEntry(1, 4, EntryKind.LEAVE_OF_ABSENCE, timesheetAlexAug);

        TimesheetEntry timesheetEntry3 = new TimesheetEntry(5, 4, EntryKind.LEAVE_OF_ABSENCE, timesheetBenAug);
        timesheetAlexAug.setCustomerKey("8928308ALEX87283279");
        timesheetEntryRepository.saveAll(Arrays.asList(timesheetEntry1,timesheetEntry2, timesheetEntry3));
        timesheetService.createTimesheets();
        //System.out.println(timesheetService.getActiveEmployees());
    }


}
