package StudentInformation;

public class StudentDetails {

     private String szulnev,neptun,szak,fokozat,szuldate,szulhely,telefon,email,nemzetiseg;

    public StudentDetails(String szulnev, String neptun, String szak, String fokozat, String szuldate, String szulhely, String telefon, String email, String nemzetiseg) {
        this.szulnev = szulnev;
        this.neptun = neptun;
        this.szak = szak;
        this.fokozat = fokozat;
        this.szuldate = szuldate;
        this.szulhely = szulhely;
        this.telefon = telefon;
        this.email = email;
        this.nemzetiseg = nemzetiseg;
    }

    public String getSzulnev() {
        return szulnev;
    }

    public void setSzulnev(String szulnev) {
        this.szulnev = szulnev;
    }

    public String getNeptun() {
        return neptun;
    }

    public void setNeptun(String neptun) {
        this.neptun = neptun;
    }

    public String getSzak() {
        return szak;
    }

    public void setSzak(String szak) {
        this.szak = szak;
    }

    public String getFokozat() {
        return fokozat;
    }

    public void setFokozat(String fokozat) {
        this.fokozat = fokozat;
    }

    public String getSzuldate() {
        return szuldate;
    }

    public void setSzuldate(String szuldate) {
        this.szuldate = szuldate;
    }

    public String getSzulhely() {
        return szulhely;
    }

    public void setSzulhely(String szulhely) {
        this.szulhely = szulhely;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNemzetiseg() {
        return nemzetiseg;
    }

    public void setNemzetiseg(String nemzetiseg) {
        this.nemzetiseg = nemzetiseg;
    }
}
