package Applications;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class popupController {

    @FXML
    Button kilepesgomb;
    @FXML
    Label cimke;

    public void megnyit() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Popupwindows/Elfogadva.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Elfogadva");

        window.setScene(s);
        window.show();
    }

    public void kilep()
    {
        Stage stage = (Stage) kilepesgomb.getScene().getWindow();
        stage.close();
    }

    public void elutasit() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Popupwindows/Elutasitva.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Elutasitva");

        window.setScene(s);
        window.show();
    }

    public void hibauzenetopen() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Popupwindows/HibaUzenet.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Hiba!");

        window.setScene(s);
        window.show();
    }
}
