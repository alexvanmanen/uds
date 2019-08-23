package nl.qien.uren.controller;

import java.util.ArrayList;

public class Admin extends User{
    final int level = 1;
    public ArrayList <User> allUsers;


    public User register(Long id,String email, String pass){
        User user = new User(id,email,pass);
        allUsers.add(user);
        return user;
    }

}
