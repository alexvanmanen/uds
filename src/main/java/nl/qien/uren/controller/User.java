package nl.qien.uren.controller;

public class User {
    private String emailAdress;
    final int level = 2;

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

    private String password;

    User(String emailAdress, String password){
        this.emailAdress = emailAdress;
        this.password = password;
    }
    User(){}
}
