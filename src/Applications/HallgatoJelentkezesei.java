package Applications;

public class HallgatoJelentkezesei {
    private String neptun,nev, egyetem1, egyetem2, egyetem3;

    public HallgatoJelentkezesei(String neptun, String nev, String egyetem1, String egyetem2, String egyetem3) {
        this.neptun = neptun;
        this.nev = nev;
        this.egyetem1 = egyetem1;
        this.egyetem2 = egyetem2;
        this.egyetem3 = egyetem3;
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

    public String getEgyetem1() {
        return egyetem1;
    }

    public void setEgyetem1(String egyetem1) {
        this.egyetem1 = egyetem1;
    }

    public String getEgyetem2() {
        return egyetem2;
    }

    public void setEgyetem2(String egyetem2) {
        this.egyetem2 = egyetem2;
    }

    public String getEgyetem3() {
        return egyetem3;
    }

    public void setEgyetem3(String egyetem3) {
        this.egyetem3 = egyetem3;
    }
}
