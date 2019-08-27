package nl.qien.uren.entity;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private boolean active;
    private String emailAdress;
    private String password;
    private String adress;
}
