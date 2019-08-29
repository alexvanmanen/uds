package nl.qien.uren.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Customer")
@Getter
@Setter
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Project> projects = new HashSet<>();




    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append(name + "\r\n");
        for (Project project: projects){
            result.append("  "+ project.getId() + " - " + project.getName() + "\r\n") ;
        }
        return result.toString();
    }
}