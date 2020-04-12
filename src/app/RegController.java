package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegController {

    @FXML
    TextField name,neptun,email;
    @FXML
    PasswordField jelszo1,jelszo2;
    @FXML
    Label uzenet;

    @FXML
    Button kilepesgomb;

    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(RegController.class.getResource("Registration.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    public void save(ActionEvent actionEvent) throws SQLException, IOException {
        ResultSet rs=dbconnection.getConn().createStatement().executeQuery("SELECT * FROM users");

        Boolean notexists=true;

        while (rs.next())
        {
            if (rs.getString("neptun").equals(neptun.getText().toUpperCase()))
            {
                notexists=false;
            }
        }

        if (!name.getText().equals("") && !neptun.getText().equals("") && !email.getText().equals("") && !jelszo1.getText().equals("") && !jelszo2.getText().equals("")) {
            if (notexists) {
                if (jelszo1.getText().equals(jelszo2.getText())) {
                    dbconnection.getConn().createStatement().executeUpdate("INSERT INTO users (name,neptun,email,password,role) VALUES ('" +
                            name.getText() + "','" + neptun.getText() + "','" + email.getText() + "','" + jelszo1.getText() + "',1)");
                    kilep(actionEvent);
                } else uzenet.setText("A két jelszó nem egyezik!");
            } else uzenet.setText("Már van ilyen regisztrált neptunkód!");
        }else uzenet.setText("Hiányzó adatok!");

    }

    public void kilep(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();;
        stage.close();
        LoginController.megnyit(actionEvent);
        popup(actionEvent);
    }

    public void popupkilep(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) kilepesgomb.getScene().getWindow();
        stage.close();
    }

    public void popup(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("successreg.fxml"));
        Scene s = new Scene(p);
        //stage információ
        Stage window = new Stage();
        window.setTitle("Sikeres regisztráció");


        window.setScene(s);
        window.show();
    }
}
