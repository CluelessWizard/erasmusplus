package HallgatoFelulet;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentMain extends OpenFunctions {
    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(StudentMain.class.getResource("StudentMain.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }
}
