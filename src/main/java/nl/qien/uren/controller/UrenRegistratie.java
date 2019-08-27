package nl.qien.uren.controller;

import java.time.LocalDate;

public class UrenRegistratie {
    private Long employeeId;
    private Long projectId;
    private Long aantalUren;
    private LocalDate datum;
    private WerkType werkType;

    public UrenRegistratie(long employeeId, long projectId, long aantalUren, String datum, WerkType werkType){
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

    public WerkType getWerkType(){return werkType;}

    public void setWerkType(WerkType werkType){this.werkType = werkType;}

}
