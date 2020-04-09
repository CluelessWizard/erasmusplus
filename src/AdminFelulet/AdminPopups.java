package AdminFelulet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPopups {
    @FXML
    Button kilepesgomb;
    @FXML
    Label cimke;

    public void torolveMegnyit() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("popupwindows/Torolve.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Törölve!");

        window.setScene(s);
        window.show();
    }

    public void visszaalitvaMegnyit() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("popupwindows/Visszaallitva.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Visszaállítva!");

        window.setScene(s);
        window.show();
    }

    public void kilep()
    {
        Stage stage = (Stage) kilepesgomb.getScene().getWindow();
        stage.close();
    }
}
