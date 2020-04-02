package Applications;

import MainMenu.mainmenu;
import app.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Jelentkezesek {
    public void Jelentkezesopen(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Jelentkezesek.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    @FXML private Parent root;
    public void mainmenuopen(javafx.event.ActionEvent actionEvent) throws IOException {
        new LoginController().mainmenuopen(actionEvent);
    }

    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        new mainmenu().hallgatokmegnyit(actionEvent);
    }
}
