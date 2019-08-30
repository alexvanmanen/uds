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
    private Employee user;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timesheet")
    private List<TimesheetEntry> entries = new ArrayList<>();
    private TimesheetState state;
    private YearMonth yearMonth;

    public Timesheet(){ }

    public Timesheet(nl.qien.uren.model.Project project, Employee employee, YearMonth yearMonth){
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
