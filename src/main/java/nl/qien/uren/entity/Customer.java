package nl.qien.uren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String companyName;
    private String address;

   @OneToMany
            (fetch = FetchType.LAZY, mappedBy = "customer")
   @JsonManagedReference
    private Set<Project> projects = new HashSet<>();

}
