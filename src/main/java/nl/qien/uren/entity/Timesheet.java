package nl.qien.uren.entity;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TS")
public class Timesheet {

    @ManyToOne
    @JoinColumn(name = "USER")
    private Employee user;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timesheet")
    private List<TimesheetEntry> entries = new ArrayList<>();
    private TimesheetState state;
    private YearMonth yearMonth;

    public Timesheet(){ }

    public Timesheet(Project project, Employee employee, YearMonth yearMonth){
        this.project = project;
        this.user = employee;
        this.yearMonth = yearMonth;
    }

    public YearMonth getYearMonth(){
        return yearMonth;
    }

    public User getEmployee(){
        return user;
    }

}
