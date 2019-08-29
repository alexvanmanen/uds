package nl.qien.uren.model.user;

public class Employee extends User {

    public Employee(Integer id, String firstName, String lastName){
        this.setId(id);
        this.setFirstname(firstName);
        this.setLastname(lastName);
    }

    public Employee(){
    }


}
