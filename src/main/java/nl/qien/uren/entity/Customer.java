package nl.qien.uren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name="customer")
public class Customer implements Serializable {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonManagedReference
    private Set<Project> projects = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naam;
    private String adres;

    public void setNaam(String naam){
        this.naam = naam;

    }

    public String getNaam(){
        return naam;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

}





