package nl.qien.uren.model;

import nl.qien.uren.model.user.Employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Timeshit {

    @Id
    @GeneratedValue
    List<TimesheetEntry> entries = new ArrayList<>();
//    Project project;
//    Employee employee;
    YearMonth yearMonth;

    public Timeshit(Project project, Employee employee, YearMonth yearMonth){
//        this.project = project;
//        this.employee = employee;
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
        return null;
    }

    public Employee getEmployee(){
        return null;
    }

}
