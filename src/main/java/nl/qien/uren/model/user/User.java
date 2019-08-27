package nl.qien.uren.model.user;

import java.util.*;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String active;
    private String emailAdress;
    private String password;
    private String adres;

    public User(Long id,String emailAdress, String password) {
        this.emailAdress = emailAdress;
        this.password = password;
        this.id = id;
    }

    User() { }

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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){return id;}

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
