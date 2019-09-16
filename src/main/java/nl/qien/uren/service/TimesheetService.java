package nl.qien.uren.service;

import nl.qien.uren.entity.Employee;
import nl.qien.uren.entity.Project;
import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.TimesheetState;
import nl.qien.uren.repository.CustomerRepository;
import nl.qien.uren.repository.EmployeeRepository;
import nl.qien.uren.repository.ProjectRepository;
import nl.qien.uren.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /*
    2. Aanroepen van CreateTimesheetForEmployee. Aanroepen van bovenstaande methode wanneer
    er een nieuwe gebruiker wordt aangemaakt. De maand = huidige jaar + maand.
    Employee = aangemaakte gebruiker, status = OPEN.
     */

    public void createTimesheetForEmployee(Employee employee, YearMonth yearMonth, TimesheetState state) {
        Project project = new Project("Ellende", customerRepository.findById(1));
        projectRepository.save(project);
        System.out.println("employeeHasNoTimesheet(employee, yearMonth) = " + employeeHasNoTimesheet(employeeRepository.findById(4), yearMonth));
        Timesheet newTimesheet = new Timesheet(project, employee, yearMonth, state);
        timesheetRepository.save(newTimesheet);
    }

    /*
    3. employeeHasNoTimesheet. Controle methode die controleert of een employee al een timesheet heeft voor deze jaar + maand.
     */
    public boolean employeeHasNoTimesheet(Employee employee, YearMonth yearMonth) {
        boolean hasTimesheet = false;
        List<Timesheet> timesheets = timesheetRepository.findAllByUserId(employee.getId());
        for (Timesheet ts : timesheets) {
            if (ts.getYearMonth().equals(yearMonth.now())) {
                hasTimesheet = true;
                return hasTimesheet;
            } else {
                hasTimesheet = false;
            }
        }

        //hier moet wat logica komen om de methode soms true en soms false terug te laten geven.
        return hasTimesheet;
    }

    /*
    4. getActiveEmployees. Methode aanmaken die lijst van actieve employees ophaalt.
     */
    private List<Employee> getActiveEmployees() {
        return new ArrayList<Employee>();
    }

    /*
    5. CreateTimesheets. Voor elke actieve employee moet gecontroleert worden of er geen timesheet is aangemaakt (zie punt 3),
    dan moet de methode uit stap 1 aangeroepen worden voor de actieve employee.
    Maand + jaar = huidige jaar en maand, employee = aangemaakt employee en status = open.
     */
    public void createTimesheets() {
        for (Employee employee : getActiveEmployees()) {
            if (employeeHasNoTimesheet(null, null)) {
                createTimesheetForEmployee(null, null, null);
            }
        }
    }

    /*
    7. StartCreationOfTimesheetsEveryMonth. Thread maken die elke 1e dag van de maand de methode uit punt 4 aanroept.
     */
    public void startCreationOfTimesheetsEveryMonth() {
        createTimesheets();//Vette thread:)
    }
}
