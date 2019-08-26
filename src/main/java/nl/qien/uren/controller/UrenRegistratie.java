package nl.qien.uren.controller;

import java.time.LocalDate;

public class UrenRegistratie {
    private Long employeeId;
    private Long projectId;
    private Long aantalUren;
    private LocalDate datum;

    public UrenRegistratie(long employeeId, long projectId, long aantalUren, String datum){
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.aantalUren = aantalUren;
        this.datum = LocalDate.parse(datum);

    }

    public Long getEmployeeId(){
        return employeeId;
    }

    public Long getProjectId(){
        return projectId;
    }

    public Long getAantalUren(){
        return aantalUren;
    }

    public LocalDate getDatum(){
        return datum;
    }

}