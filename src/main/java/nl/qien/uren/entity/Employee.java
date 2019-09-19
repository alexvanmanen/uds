package nl.qien.uren.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "employee")
public class Employee extends User{

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;


    public Employee(String firstname, String lastname, String username, String password){
        super(firstname, lastname, username, password);
    }
}
