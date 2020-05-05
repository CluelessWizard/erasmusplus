package DekanFelulet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainmenuController extends FeluletValtas implements Initializable {

    @FXML
    javafx.scene.web.WebView WebView;

    public static void megnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(MainmenuController.class.getResource("../DekanFelulet/mainmenu.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("../DekanFelulet/Listazas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebView.getEngine().load(getClass().getResource("../MainMenu/ErasmusMainMenu.html").toString());
    }

}
