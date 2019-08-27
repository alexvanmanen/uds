package nl.qien.uren.model.user;

public class Employee extends User {

    public Employee(Long id, String firstName, String lastName){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


}
