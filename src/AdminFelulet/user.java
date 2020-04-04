package AdminFelulet;

public class user {
    private String neptun,nev,role;

    public user(String neptun, String nev, String role) {
        this.neptun = neptun;
        this.nev = nev;
        this.role = role;
    }

    public String getNeptun() {
        return neptun;
    }

    public void setNeptun(String neptun) {
        this.neptun = neptun;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
