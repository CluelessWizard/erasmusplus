package DekanFelulet;

import app.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FeluletValtas {
    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("../DekanFelulet/Listazas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    public void mainmenumegnyit(ActionEvent actionEvent) throws IOException {
        MainmenuController.megnyit(actionEvent);
    }

    public void kijelentkezes(ActionEvent actionEvent) throws Exception {
        new LoginController().megnyit(actionEvent);

    }

    public void targyakmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("../DekanFelulet/TargyElfogadas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

}
