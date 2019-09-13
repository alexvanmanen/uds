package nl.qien.uren.service;

import nl.qien.uren.entity.Employee;
import nl.qien.uren.entity.TimesheetState;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class TimesheetService {


    public void createTimesheetForEmployee(Employee employee, YearMonth yearMonth, TimesheetState state){

    }

    private boolean employeeHasNoTimesheet(Employee employee, YearMonth yearMonth){
        return false;
    }

    private List<Employee> getActiveEmployees(){
        return new ArrayList<Employee>();
    }

    public void createTimesheets(){
        for(Employee employee: getActiveEmployees()){
            if(employeeHasNoTimesheet(null, null)){
                createTimesheetForEmployee(null, null, null);
            }
        }
    }

    public void startCreationOfTimesheetsEveryMonth(){
        createTimesheets();//Vette thread:)
    }
}
