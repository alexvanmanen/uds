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
    private String straat;
    private String huisnummer;
    private String postcode;
    private String woonplaats;
    private int telefoonnummer;
    private String bankrekeningnr;
    private Boolean firstlogin;
}
