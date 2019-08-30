package nl.qien.uren.entity;

import nl.qien.uren.model.EntryKind;
import nl.qien.uren.model.Project;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TS")
public class Timesheet {

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @Id
    @GeneratedValue
    private int id;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timesheet")
//    private List<TimesheetEntry> entries = new ArrayList<>();
//    Project project;
//    Employee employee;
    YearMonth yearMonth;

    public Timesheet(nl.qien.uren.model.Project project, Employee employee, YearMonth yearMonth){
//        this.project = project;
//        this.employee = employee;
       // this.yearMonth = yearMonth;
    }

    public Timesheet(){}

    public void addHourEntry(int numberOfHours, int dayOfTheMonth, EntryKind entryKind){
       LocalDate entryDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), dayOfTheMonth);
       // Timesheet_Entry TSENTRY = new Timesheet_Entry(numberOfHours, entryDate, entryKind);
        //entries.add(TSENTRY);
    }
//
//    public List<TS_ENTRY> getEntries(){
//        return entries;
//    }

    public YearMonth getYearMonth(){
        return yearMonth;
    }

    public int getTotalHours(){
        int total = 0;
//        for(TS_ENTRY TSENTRY : entries){
//            total = TSENTRY.getNumberOfHours();
//        }
        return total;
    }

    public Project getProject(){
        return null;
    }

    public Employee getEmployee(){
        return null;
    }

}
