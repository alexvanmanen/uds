package nl.qien.uren.entity;

import nl.qien.uren.model.EntryKind;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TSEntry")
public class TimesheetEntry implements Serializable {

    @ManyToOne
    @JoinColumn(name = "TIMESHEET_ID")
    private Timesheet timesheet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int uren;
    private Date dag;
    @Enumerated(EnumType.STRING)
    private EntryKind entryKind;

    public TimesheetEntry(){}

    public TimesheetEntry(Date dag, int uren, EntryKind entryKind, Timesheet timesheet) {
        this.timesheet = timesheet;
        this.dag = dag;
        this.uren = uren;
        this.entryKind = entryKind;
    }

    public int getUren() {
        return uren;
    }

    public Timesheet getTimesheet(){
        return timesheet;
    }

    public Date getDag() {
        return dag;
    }


}
