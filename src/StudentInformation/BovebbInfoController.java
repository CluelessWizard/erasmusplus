package StudentInformation;


import StudentList.Listazas;
import StudentList.Student;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BovebbInfoController implements Initializable {
    @FXML Label szulnev;
    @FXML Label neptun;
    @FXML Label szak;
    @FXML Label fokozat;
    @FXML Label szuldate;
    @FXML Label szulhely;
    @FXML Label telefon;
    @FXML Label email;
    @FXML Label nemzetiseg;

    private Student hallgato;

    public void megjelenites() throws SQLException, IOException {

        Parent p = FXMLLoader.load(getClass().getResource("BovebbInfo.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Hallgató adatai");

        window.setScene(s);
        window.show();
    }


    private static List<StudentDetails> lista=new ArrayList<>();

    private ResultSet rs;
    private Connection con;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.hallgato= Listazas.getSelectedforedit();

        try {
            con=app.dbconnection.getConn();
            rs=con.createStatement().executeQuery("select * from students");

            adatok();


            for(StudentDetails s:lista)
            {

                if (s.getNeptun().equals(hallgato.getNeptun()))
                {
                szulnev.setText(s.getSzulnev());
                neptun.setText(s.getNeptun());
                szak.setText(s.getSzak());
                if (s.getFokozat() == "1") {
                    fokozat.setText("Bsc");
                } else if (s.getFokozat() == "2") {
                    fokozat.setText("Msc");
                } else fokozat.setText("Phd");
                szuldate.setText(s.getSzuldate());
                szulhely.setText(s.getSzulhely());
                telefon.setText(s.getTelefon());
                email.setText(s.getEmail());
                nemzetiseg.setText(s.getNemzetiseg());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void adatok() throws SQLException {
        while (rs.next()) {
            lista.add(new StudentDetails(rs.getString("name"),
                    rs.getString("neptun"),
                    rs.getString("degree"),
                    rs.getString("LevelOfStudy"),
                    rs.getString("birthday"),
                    rs.getString("birthplace"),
                    rs.getString("mobile"),
                    rs.getString("email"),
                    rs.getString("nationality")));
        }
    }
}
