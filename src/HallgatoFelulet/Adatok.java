package HallgatoFelulet;

import app.LoginController;
import app.dbconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Adatok extends OpenFunctions implements Initializable {

    @FXML
    TextField neptunField,nameField,nationalityField,birthdateField,birthplaceField,telefonField,emailField;
    @FXML
    Label successornot;

    Connection con;

    private String username;

    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(Adatok.class.getResource("Adatok.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.username= LoginController.getUsername();
        neptunField.setText(username);

        try {
            con= dbconnection.getConn();

            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM users WHERE neptun='"+username+"'");

            while(rs.next()) {
                nameField.setText(rs.getString("name"));
                emailField.setText(rs.getString("email"));
            }

            rs=con.createStatement().executeQuery("SELECT * FROM students WHERE neptun='"+username+"'");

            while(rs.next()) {
                    nationalityField.setText(rs.getString("nationality"));
                    birthdateField.setText(rs.getString("birthday"));
                    birthplaceField.setText(rs.getString("birthplace"));
                    telefonField.setText(rs.getString("mobile"));

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() throws SQLException, IOException {
            try {
                dbconnection.getConn().createStatement().executeUpdate("INSERT INTO students (neptun) VALUES ('" + neptunField.getText() + "')");
            } catch (Exception ex) {}

            try {
                    dbconnection.getConn().createStatement().executeUpdate("UPDATE students SET nationality='" + nationalityField.getText() + "',birthday='" + birthdateField.getText() + "',birthplace='" +
                            birthplaceField.getText() + "',mobile='" + telefonField.getText() + "' WHERE neptun='" + LoginController.getUsername() + "'");
                    dbconnection.getConn().createStatement().executeUpdate("UPDATE users SET email='" + emailField.getText() + "' WHERE neptun='" + LoginController.getUsername() + "'");
                     successornot.setText("Sikeres mentés");
                }
                catch (Exception ex2)
                {
                    successornot.setText("Hibás/hiányzó adat");
                }



    }



}
