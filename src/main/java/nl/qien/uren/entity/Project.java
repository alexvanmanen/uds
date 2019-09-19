package nl.qien.uren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Employee> employees = new HashSet<>();

    private String name;
    private String email;

    public Project(){}

    public Project(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
         this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getCustomerName(){
        return name;
    }

    public void setEmployees(Set<Employee> employees){
        this.employees = employees;
    }


}
