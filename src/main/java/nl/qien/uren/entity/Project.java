package nl.qien.uren.entity;

import javax.persistence.*;
import java.io.Serializable;
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

    private String name;

    public Project(String name, Customer customer){
        this.name = name;
        this.customer = customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer(){
        return customer;
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