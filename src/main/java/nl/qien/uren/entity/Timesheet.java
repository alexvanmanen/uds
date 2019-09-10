package nl.qien.uren.entity;

import nl.qien.uren.util.YearMonthDateAttributeConverter;

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

    @Enumerated(EnumType.STRING)
    private TimesheetState state;

    @Convert(
            converter = YearMonthDateAttributeConverter.class
    )
    private YearMonth yearMonth;
    private String customerKey;
    public Timesheet(){ }

    public Timesheet(Project project, Employee employee, YearMonth yearMonth, TimesheetState state){
        this.state = state;
        this.project = project;
        this.user = employee;
        this.yearMonth = yearMonth;
    }

    public YearMonth getYearMonth(){
        return yearMonth;
    }

    public List<TimesheetEntry> getEntries(){
        return entries;
    }

    public String getState(){
        return state.toString();
    }

    public int getId(){
        return id;
    }

    public void setEntries(List<TimesheetEntry> entries){
        this.entries = entries;
    }

    public String getCustomerName() {
        return "Bart Zwaagstra";
    }

    public String getCustomerEmailAddress() {
        return project.getCustomerEmailAddress();
    }

    public String getEmployeeName() {
        return user.getFirstname() + " " + user.getLastname();
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }
}
