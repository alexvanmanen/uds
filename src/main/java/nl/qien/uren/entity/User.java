package nl.qien.uren.entity;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private boolean active;
    private String emailadress;
    private String password;
    private String adress;
}
