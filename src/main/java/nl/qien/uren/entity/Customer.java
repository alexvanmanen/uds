package nl.qien.uren.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public void setProjects(Set<Project> projects){
        this.projects = projects;
    }

    public Set<Project> getProjects(){
        return projects;
    }
}