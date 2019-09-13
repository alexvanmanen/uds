package nl.qien.uren.service;

import nl.qien.uren.entity.Employee;
import nl.qien.uren.entity.TimesheetState;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class TimesheetService {


    /*
    2. Aanroepen van CreateTimesheetForEmployee. Aanroepen van bovenstaande methode wanneer
    er een nieuwe gebruiker wordt aangemaakt. De maand = huidige jaar + maand.
    Employee = aangemaakte gebruiker, status = OPEN.
     */

    public void createTimesheetForEmployee(Employee employee, YearMonth yearMonth, TimesheetState state){

    }

    /*
    3. employeeHasNoTimesheet. Controle methode die controleert of een employee al een timesheet heeft voor deze jaar + maand.
     */
    private boolean employeeHasNoTimesheet(Employee employee, YearMonth yearMonth){
        return false;
    }

    /*
    4. getActiveEmployees. Methode aanmaken die lijst van actieve employees ophaalt.
     */
    private List<Employee> getActiveEmployees(){
        return new ArrayList<Employee>();
    }

    /*
    5. CreateTimesheets. Voor elke actieve employee moet gecontroleert worden of er geen timesheet is aangemaakt (zie punt 3),
    dan moet de methode uit stap 1 aangeroepen worden voor de actieve employee.
    Maand + jaar = huidige jaar en maand, employee = aangemaakt employee en status = open.
     */
    public void createTimesheets(){
        for(Employee employee: getActiveEmployees()){
            if(employeeHasNoTimesheet(null, null)){
                createTimesheetForEmployee(null, null, null);
            }
        }
    }

    /*
    7. StartCreationOfTimesheetsEveryMonth. Thread maken die elke 1e dag van de maand de methode uit punt 4 aanroept.
     */
    public void startCreationOfTimesheetsEveryMonth(){
        createTimesheets();//Vette thread:)
    }
}
