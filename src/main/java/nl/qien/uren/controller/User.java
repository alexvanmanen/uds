package nl.qien.uren.controller;

import java.util.*;

public class User {
    private String emailAdress;
    final int level = 2;
    private String password;
    private Double workedHours;
    List<Double> totalHours;

    public void setWorkedHours(double workedHours){
        this.workedHours = workedHours;
    }

    public double getWorkedHours(){
        return workedHours;
    }

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

    public List registerHours(Double workedHours){
        totalHours.add(workedHours);
        return totalHours;
    }

    public void printHours(List totalHours){
        // terug naar frontend??
    }


    public User(String emailAdress, String password) {
        this.emailAdress = emailAdress;
        this.password = password;
    }

    User() {
    }
}
