package MainMenu;

import Applications.Jelentkezesek;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class mainmenu {

    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("../StudentList/Listazas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    public void Jelentkezesekopen(ActionEvent actionEvent) throws IOException {
        new Jelentkezesek().Jelentkezesopen(actionEvent);
    }

}
