package nl.qien.uren.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;
    public String firstname;
    public String lastname;
    private boolean active = true;
    private String emailadress;
    private String password;
    private String street;
    private String housenumber;
    private String zipcode;
    private String city;
    private int phonenumber;
    private String accountnumber;
    private Boolean firstlogin;
    private String employer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Timesheet> timesheets = new HashSet<>();

}
