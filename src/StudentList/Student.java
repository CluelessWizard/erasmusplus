package StudentList;

public class Student {
    private String nev;
    private String neptun;
    private String telefon;
    private String email;
    private String szak;

    public Student(String nev, String neptun, String telefon, String email, String szak) {
        this.nev = nev;
        this.neptun = neptun;
        this.telefon = telefon;
        this.email = email;
        this.szak = szak;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getNeptun() {
        return neptun;
    }

    public void setNeptun(String neptun) {
        this.neptun = neptun;
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

    public String getSzak() {
        return szak;
    }

    public void setSzak(String szak) {
        this.szak = szak;
    }
}
