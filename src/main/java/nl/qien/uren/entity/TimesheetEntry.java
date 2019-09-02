package nl.qien.uren.entity;

import nl.qien.uren.model.EntryKind;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "TSEntry")
public class TimesheetEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "TIMESHEET_ID")
    private Timesheet timesheet;

    private int hoursSpent;
    private int dayOfTheMonth;
    @Enumerated(EnumType.STRING)
    private EntryKind entryKind;

    public TimesheetEntry(){}

    public TimesheetEntry(int dayOfTheMonth, int hoursSpent, EntryKind entryKind, Timesheet timesheet) {
        this.timesheet = timesheet;
        this.dayOfTheMonth = dayOfTheMonth;
        this.hoursSpent = hoursSpent;
        this.entryKind = entryKind;
    }

    public int getHoursSpent() {
        return hoursSpent;
    }
    
    public EntryKind getEntryKind(){
        return entryKind;
    }

    public int getDayOftheMonth() {
        return dayOfTheMonth;
    }

    public LocalDate getDate(){
        return LocalDate.of(timesheet.getYearMonth().getYear(), timesheet.getYearMonth().getMonth(), dayOfTheMonth);
    }


}
