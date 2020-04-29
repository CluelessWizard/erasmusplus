package HallgatoFelulet;

import app.LoginController;
import javafx.event.ActionEvent;

import java.io.IOException;

public class OpenFunctions {


    public void mainmenuOpen(ActionEvent actionEvent) throws IOException {
        StudentMain.megnyit(actionEvent);
    }

    public void ertekelesOpen(ActionEvent actionEvent) throws IOException {
        Ertekeles.megnyit(actionEvent);
    }

    public void egyetemOpen(ActionEvent actionEvent) throws IOException {
        Egyetemek.megnyit(actionEvent);
    }

    public void adatokOpen(ActionEvent actionEvent) throws IOException {
        Adatok.megnyit(actionEvent);
    }

    public void jelentkezesOpen(ActionEvent actionEvent) throws IOException {
        Jelentkezes.megnyit(actionEvent);
    }

    public void kijelentkezes(ActionEvent actionEvent) throws Exception {
        new LoginController().megnyit(actionEvent);
    }

    public void reszletekOpen(ActionEvent actionEvent) throws Exception {
        JelentkezesReszletek.megnyit(actionEvent);
    }



}
