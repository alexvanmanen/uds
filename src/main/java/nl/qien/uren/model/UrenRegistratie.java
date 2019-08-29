package nl.qien.uren.model;

import nl.qien.uren.controller.WerkType;

import java.time.LocalDate;

public class UrenRegistratie {
    private String employeeId;
    private String projectId;
    private Long aantalUren;
    private LocalDate datum;
    private WerkType werkType;

    public UrenRegistratie(){
    }

    public UrenRegistratie(String employeeId, String projectId, long aantalUren, String datum, WerkType werkType){
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.aantalUren = aantalUren;
        this.datum = LocalDate.parse(datum);
        this.werkType = werkType;

    }
    public static int daysInMonth(int year, int month ) {
        LocalDate date = LocalDate.of(year, month, 01);
        int a = date.lengthOfMonth();
        return a;
    }

    public String getEmployeeId(){
        return employeeId;
    }

    public String getProjectId(){
        return projectId;
    }

    public Long getAantalUren(){
        return aantalUren;
    }

    public LocalDate getDatum(){
        return datum;
    }

    public WerkType getWerkType(){return werkType;}

    public void setWerkType(WerkType werkType){this.werkType = werkType;}

}
