package nl.qien.uren.model.user;

import java.util.*;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean active;
    private String emailAdress;
    private String password;
    private String adress;

    public User(Long id, String firstName, String lastName, boolean active, String emailAdress, String password, String adress) {
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
