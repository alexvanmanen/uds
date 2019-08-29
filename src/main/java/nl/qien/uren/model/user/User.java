package nl.qien.uren.model.user;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private boolean active;
    private String emailadress;
    private String password;
    private String street;
    private String housenumber;
    private String zipcode;
    private String city;
    private int phonenumber;
    private String accountnumber;
    private boolean firstlogin;

    public User(int id, String firstname, String lastname, boolean active, String emailadress, String password, String street,
                String housenumber, String zipcode, String city, int phonenumber, String accountnumber, boolean firstlogin) {
        this.emailadress = emailadress;
        this.password = password;
        this.id = id;
        this.active = active;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.housenumber = housenumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phonenumber = phonenumber;
        this.accountnumber = accountnumber;
        this.firstlogin = firstlogin;

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

    public String getStreet() { return street; }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getHousenumber() { return housenumber; }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public boolean getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(boolean firstlogin) {
        this.firstlogin = firstlogin;
    }
}