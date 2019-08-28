package nl.qien.uren.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    //@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL)
    //private Set<Project> projects;

    public Customer(String name){
        this.name = name;
    }

    public void addProject(Project project){
        //projects.add(project);
    }

    public String toString(){
        return name;
    }
}