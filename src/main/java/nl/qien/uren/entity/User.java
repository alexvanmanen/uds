package nl.qien.uren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private boolean active;
    private String emailadress;
    private String password;
    private String street;
    private String housenumber;
    private String zipcode;
    private String city;
    private int phonenumber;
    private String accountnumber;
    private Boolean firstlogin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<Timesheet> timesheets = new HashSet<>();

}
