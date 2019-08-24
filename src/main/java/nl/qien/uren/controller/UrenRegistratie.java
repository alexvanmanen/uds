package nl.qien.uren.controller;

public class UrenRegistratie {
    private Long employeeId;
    private Long projectId;
    private Long aantalUren;

    public UrenRegistratie(long employeeId, long projectId, long aantalUren){
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.aantalUren = aantalUren;
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

}
