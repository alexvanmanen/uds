package nl.qien.uren.entity;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
}
