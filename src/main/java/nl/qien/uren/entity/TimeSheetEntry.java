package nl.qien.uren.entity;

import nl.qien.uren.model.EntryKind;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TSEntry")
public class TimeSheetEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int uren;
    private Date dag;
    @Enumerated(EnumType.STRING)
    private EntryKind entryKind;

    public TimeSheetEntry(Date dag, int uren, EntryKind entryKind) {
        this.dag = dag;
        this.uren = uren;
        this.entryKind = entryKind;
    }

    public int getUren() {
        return uren;
    }

    public Date getDag() {
        return dag;
    }


}
