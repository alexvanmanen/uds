package nl.qien.uren.controller;

public class Employee {

    private String firstName;
    private String lastName;
    private String emailAdress;

    public Employee(String firstName, String lastName, String emailAdress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress(){return emailAdress;}

    public void setEmailAdress(String emailAdress){ this.emailAdress = emailAdress;}


}
