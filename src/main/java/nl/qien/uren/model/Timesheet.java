package nl.qien.uren.model;

import nl.qien.uren.model.user.Employee;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Timesheet {

    List<TimesheetEntry> entries = new ArrayList<>();
    Project project;
    Employee employee;
    YearMonth yearMonth;

    public Timesheet(Project project, Employee employee, YearMonth yearMonth){
        this.project = project;
        this.employee = employee;
        this.yearMonth = yearMonth;
    }

    public void addHourEntry(int numberOfHours, int dayOfTheMonth, EntryKind entryKind){
        LocalDate entryDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), dayOfTheMonth);
        TimesheetEntry timesheetEntry = new TimesheetEntry(numberOfHours, entryDate, entryKind);
        entries.add(timesheetEntry);
    }

    public List<TimesheetEntry> getEntries(){
        return entries;
    }

    public YearMonth getYearMonth(){
        return yearMonth;
    }

    public int getTotalHours(){
        int total = 0;
        for(TimesheetEntry timesheetEntry: entries){
            total = timesheetEntry.getNumberOfHours();
        }
        return total;
    }

    public Project getProject(){
        return project;
    }

    public Employee getEmployee(){
        return employee;
    }

}
