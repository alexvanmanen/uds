package nl.qien.uren.entity;

import lombok.*;
import nl.qien.uren.model.EntryKind;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "TSEntry")

public class TimesheetEntry implements Serializable {

    public TimesheetEntry(){
    }

//    @ManyToOne
//    @JoinColumn(name = "TIMESHEET_ID")
//    private Timesheet timesheet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int uren;
    private Date dag;
//    @Enumerated(EnumType.STRING)
//    private EntryKind entryKind;

    public TimesheetEntry(int hours) {
        this.uren = hours;
    }



    private int getUren() {
        return uren;
    }

    public Date getDag() {
        return dag;
    }

    private void setUren(int uren) {
        this.uren = uren;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
