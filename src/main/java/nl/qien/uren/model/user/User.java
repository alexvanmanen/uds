package nl.qien.uren.model.user;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private boolean active;
    private String emailadress;
    private String password;
    private String adress;

    public User(int id, String firstname, String lastname, boolean active, String emailadress, String password, String adress) {
        this.emailadress = emailadress;
        this.password = password;
        this.id = id;
        this.active = active;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
    }

    User() { }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){return id;}

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
