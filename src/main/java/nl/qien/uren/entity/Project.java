package nl.qien.uren.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name="project")
public class Project implements Serializable {

    @ManyToOne
    @JoinColumn(name="Customer_ID")
    @JsonBackReference
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String projectName;
    //private LocalDate startDate;

    public Project(String name, Customer customer){
    }
    public Project(){}
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public String getProjectName(){
        return projectName;
    }
}
