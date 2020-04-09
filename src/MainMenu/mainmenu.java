package MainMenu;

import Applications.Jelentkezesek;
import app.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class mainmenu implements Initializable {

    @FXML
    WebView WebView;
    @FXML
    Pane root;

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebView.getEngine().load(getClass().getResource("ErasmusMainMenu.html").toString());
        
    }

    public void kijelentkezes(ActionEvent actionEvent) throws Exception {
        new LoginController().megnyit(actionEvent);
    }


}
