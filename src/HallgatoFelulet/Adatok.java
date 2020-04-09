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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Adatok extends OpenFunctions implements Initializable {

    //Képzés segéd-osztály az adatbázisba való felvitelhez
    public class Fokozat {

        String degree;
        int id;

        Fokozat(String degree)
        {
            this.degree=degree;
        }

        Fokozat(int id)
        {
            this.id=id;
        }

        public int getId() {
            switch (degree) {
                case "Mesterképzés":
                    return 2;
                case "Doktori képzés":
                    return 3;
                default:
                    return 1;
            }
        }

        public String getDegree()
        {
            switch(id)
            {
                case 2:
                    return "Mesterképzés";
                case 3:
                    return "Doktori képzés";
                default:
                    return "Alapképzés";
            }
        }
    }

    @FXML
    TextField neptunField,nameField,nationalityField,birthdateField,birthplaceField,telefonField,emailField;
    @FXML
    Label successornot;

    @FXML
    ChoiceBox degreeChoice;

    @FXML
    ChoiceBox losChoice;

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


            //ChoiceBox feltöltése

            ResultSet cb=con.createStatement().executeQuery("SELECT * FROM degrees");

            while (cb.next())
            {
                degreeChoice.getItems().add(cb.getString("name"));
            }

            //ChoiceBox személyre szabása

            ResultSet Scb=con.createStatement().executeQuery("SELECT * FROM degrees d JOIN students s ON d.ID=s.degree WHERE neptun='"+neptunField.getText()+"'");

            while(Scb.next())
            {
                Fokozat tmp=new Fokozat(parseInt(Scb.getString("levelOFStudy")));
                degreeChoice.setValue(Scb.getString("d.name"));
                losChoice.setValue(tmp.getDegree());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() throws SQLException, IOException {
            try {
                //Hallgató hozzáadása users-ből ha még nincs benne a students táblában
                con.createStatement().executeUpdate("INSERT INTO students (neptun) VALUES ('" + neptunField.getText() + "')");
            } catch (Exception ex) {}

            //Ha már benne van a students táblában akkor update
            try {
                    con.createStatement().executeUpdate("UPDATE students SET nationality='" + nationalityField.getText() + "',birthday='" + birthdateField.getText() + "',birthplace='" +
                            birthplaceField.getText() + "',mobile='+36" + telefonField.getText() + "' WHERE neptun='" + LoginController.getUsername() + "'");
                    con.createStatement().executeUpdate("UPDATE users SET email='" + emailField.getText() + "' WHERE neptun='" + LoginController.getUsername() + "'");
                     successornot.setText("Sikeres mentés");
                }
                catch (Exception ex2)
                {
                    successornot.setText("Hibás/hiányzó adat");
                }



             //Szak és képzés felvitele adatbázisba


            String chosendegree = (String) degreeChoice.getSelectionModel().getSelectedItem();
            String chosenlos = (String) losChoice.getSelectionModel().getSelectedItem();
        try {
            Fokozat fok = new Fokozat(chosenlos);
            fok.getId();

            //students.degree-hez ID keresés a degrees táblából

            ResultSet deg = con.createStatement().executeQuery("SELECT * FROM degrees");
            String degreeID = "0";

            while (deg.next()) {
                if (chosendegree.equals(deg.getString("name"))) {
                    degreeID = deg.getString("ID");
                }
            }

            if (chosenlos != null && chosendegree != null) {
                con.createStatement().executeUpdate("UPDATE students SET degree='" + degreeID + "',levelOfStudy='" + fok.getId() + "' WHERE neptun='" + neptunField.getText() + "'");
            }

        }catch(Exception ex)
        {
        }
    }



}
