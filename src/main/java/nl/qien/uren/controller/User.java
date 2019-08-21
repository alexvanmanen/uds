package nl.qien.uren.controller;

import java.util.ArrayList;

public class User {
    private String emailAdress;
    final int level = 2;
    private String password;
    private double workedHours;

    ArrayList<Double> totalHours = new ArrayList();

    User(String emailAdress, String password) {
        this.emailAdress = emailAdress;
        this.password = password;
    }

    User() {
    }

    public void setWorkedHours(double workedHours){
        this.workedHours= workedHours;
    }
    public double getWorkedHours(){
        return workedHours;

    }

    String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void registerHours(double workedHours) {



    }
}
