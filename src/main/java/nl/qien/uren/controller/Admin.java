package nl.qien.uren.controller;

public class Admin extends User{
    final int level = 1;

    public User register(String email, String pass){
        User user = new User(email,pass);
        return user;
    }

}
