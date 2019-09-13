package nl.qien.uren.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "customer")
public class Customer extends User {


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Project> projects = new HashSet<>();

    public Customer(String firstname, String lastname, String username, String password){
        super(firstname, lastname, username, password);
    }

    public void setProjects(Set<Project> projects){
        this.projects = projects;
    }

    public Set<Project> getProjects(){
        return projects;
    }
}
