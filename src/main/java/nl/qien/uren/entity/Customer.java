package nl.qien.uren.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Project> projects = new HashSet<>();

    public Customer(){ }

    public Customer(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProjects(Set<Project> projects){
        this.projects = projects;
    }

    public Set<Project> getProjects(){
        return projects;
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append(name + "\r\n");
        for (Project project: projects){
            result.append("  "+ project.getId() + " - " + project.getName() + "\r\n") ;
        }
        return result.toString();
    }
}