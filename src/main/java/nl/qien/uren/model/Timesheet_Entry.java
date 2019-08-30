package nl.qien.uren.model;

import java.time.LocalDate;

public class Timesheet_Entry {
    private int numberOfHours;
    private LocalDate entryDate;
    private EntryKind entryKind;

    public Timesheet_Entry(int numberOfHours, String entryDate, EntryKind entryKind){
        this(numberOfHours, LocalDate.parse(entryDate), entryKind);
    }
    public Timesheet_Entry(int numberOfHours, LocalDate entryDate, EntryKind entryKind){
        this.numberOfHours = numberOfHours;
        this.entryDate = entryDate;
        this.entryKind = entryKind;
    }

    public int getNumberOfHours(){
        return numberOfHours;
    }

    public LocalDate getEntryDate(){
        return entryDate;
    }

    public EntryKind getEntryKind(){return entryKind;}

}
