package nl.qien.uren.model;

import nl.qien.uren.controller.EntryKind;

import java.time.LocalDate;

public class TimesheetEntry {
    private int numberOfHours;
    private LocalDate entryDate;
    private EntryKind entryKind;

    public TimesheetEntry(int numberOfHours, String entryDate, EntryKind entryKind){
        this(numberOfHours, LocalDate.parse(entryDate), entryKind);
    }
    public TimesheetEntry(int numberOfHours, LocalDate entryDate, EntryKind entryKind){
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
