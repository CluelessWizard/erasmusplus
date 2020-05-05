package HallgatoFelulet;

public class University_feedback {
    String Egyetem;
    String Varos;
    String Homerseklet;
    double Koltseg;
    double Latvanyossag;
    double Szinvonal;
    int Ertekeles;

    public University_feedback(String egyetem, String varos, String homerseklet, double koltseg, double latvanyossag, double szinvonal, int ertekeles) {
        Egyetem = egyetem;
        Varos = varos;
        Homerseklet = homerseklet;
        Koltseg = koltseg;
        Latvanyossag = latvanyossag;
        Szinvonal = szinvonal;
        Ertekeles = ertekeles;
    }

    public String getEgyetem() {
        return Egyetem;
    }

    public void setEgyetem(String egyetem) {
        Egyetem = egyetem;
    }

    public String getVaros() {
        return Varos;
    }

    public void setVaros(String varos) {
        Varos = varos;
    }

    public String getHomerseklet() {
        return Homerseklet;
    }

    public void setHomerseklet(String homerseklet) {
        Homerseklet = homerseklet;
    }

    public double getKoltseg() {
        return Koltseg;
    }

    public void setKoltseg(double koltseg) {
        Koltseg = koltseg;
    }

    public double getLatvanyossag() {
        return Latvanyossag;
    }

    public void setLatvanyossag(double latvanyossag) {
        Latvanyossag = latvanyossag;
    }

    public double getSzinvonal() {
        return Szinvonal;
    }

    public void setSzinvonal(double szinvonal) {
        Szinvonal = szinvonal;
    }

    public int getErtekeles() {
        return Ertekeles;
    }

    public void setErtekeles(int ertekeles) {
        Ertekeles = ertekeles;
    }
}
