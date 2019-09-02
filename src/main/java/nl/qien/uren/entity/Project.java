package nl.qien.uren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Timesheet> timesheets = new HashSet<>();

    private String name;

    public Project(){}

    public Project(String name, Customer customer){
        this.name = name;
        this.customer = customer;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}