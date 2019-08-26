package nl.qien.uren.entity;

import javax.persistence.*;

@Entity
public class EmployeeEEE {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
}