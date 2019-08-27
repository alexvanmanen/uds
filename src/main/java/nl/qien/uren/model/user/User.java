package nl.qien.uren.model.user;

public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private boolean active;
    private String emailadress;
    private String password;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String woonplaats;
    private int telefoonnummer;
    private String bankrekeningnr;
    private boolean firstlogin;


    public User(Long id, String firstname, String lastname, boolean active, String emailadress, String password,
                String straat, String huisnummer, String postcode, String woonplaats, int telefoonnummer, String bankrekeningnr, boolean firstlogin) {
        this.emailadress = emailadress;
        this.password = password;
        this.id = id;
        this.active = active;
        this.firstname = firstname;
        this.lastname = lastname;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.telefoonnummer = telefoonnummer;
        this.bankrekeningnr = bankrekeningnr;
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
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){return id;}

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

    public String getStraat() { return straat; }

    public void setStraat(String straat) {
        this.straat = straat;
    }
    public String getHuisnummer() { return huisnummer; }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getBankrekeningnr() {
        return bankrekeningnr;
    }

    public void setBankrekeningnr(String bankrekeningnr) {
        this.bankrekeningnr = bankrekeningnr;
    }

    public boolean getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(boolean firstlogin) {
        this.firstlogin = firstlogin;
    }
}
