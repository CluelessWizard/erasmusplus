package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class mainmenu {
    @FXML private Parent root;
    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Listazas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) root.getScene().getWindow();

        window.setScene(s);
        window.show();
    }
    
}
