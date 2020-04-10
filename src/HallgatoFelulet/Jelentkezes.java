package HallgatoFelulet;

import app.LoginController;
import app.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Jelentkezes extends OpenFunctions implements Initializable {
    @FXML
    ListView lista;

    private ObservableList<String> oblist = FXCollections.observableArrayList();

    Connection con;

    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(Jelentkezes.class.getResource("Jelentkezes.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista.setPlaceholder(new Label("Nincsenek Jelentkezéseid"));
        try {
            con= dbconnection.getConn();
            ResultSet app1=con.createStatement().executeQuery("SELECT * FROM students s JOIN applications a ON s.ApplicationID1=a.ID JOIN institutions i ON i.ID=a.institutionID WHERE s.neptun='"+ LoginController.getUsername()+"'");
            ResultSet app2=con.createStatement().executeQuery("SELECT * FROM students s JOIN applications a ON s.ApplicationID2=a.ID JOIN institutions i ON i.ID=a.institutionID WHERE s.neptun='"+ LoginController.getUsername()+"'");
            ResultSet app3=con.createStatement().executeQuery("SELECT * FROM students s JOIN applications a ON s.ApplicationID3=a.ID JOIN institutions i ON i.ID=a.institutionID WHERE s.neptun='"+ LoginController.getUsername()+"'");



            while (app1.next())
            {
                oblist.add(app1.getString("i.name"));
            }
            while (app2.next())
            {
                oblist.add(app2.getString("i.name"));
            }
            while (app3.next())
            {
                oblist.add(app3.getString("i.name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!oblist.isEmpty()) lista.setItems(oblist);
    }

    public void newApp(ActionEvent actionEvent) throws IOException {
        JelentkezesiLap.megnyit(actionEvent);
    }
}
