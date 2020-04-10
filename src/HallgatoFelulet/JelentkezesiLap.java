package HallgatoFelulet;

import app.LoginController;
import app.dbconnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class JelentkezesiLap implements Initializable {

    Connection con;
    @FXML
    TextField tanulmany;

    @FXML
    ChoiceBox egyetem,targy1,targy2,targy3,targy4,targy5,targy6,targy7,targy8,targy9,targy10,targy11,targy12,targy13,targy14,targy15;

    @FXML
    CheckBox csekk1,csekk2;

    @FXML
    Label hibauzenet;

    private List<String> osszestargy=new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            //Tanulmányok beállítása
            con = dbconnection.getConn();
            ResultSet degree=con.createStatement().executeQuery("SELECT * FROM students s JOIN degrees d ON s.degree=d.ID WHERE neptun='"+ LoginController.getUsername()+"'");

            while (degree.next())
            {
                String tmp="";
                switch (degree.getString("levelOfStudy"))
                {
                    case "1":
                        tmp="BSc";
                        break;
                    case "2":
                        tmp="MSc";
                        break;
                    case "3":
                        tmp ="PhD";
                        break;

                }
                tanulmany.setText(degree.getString("d.name")+" "+tmp);

                //egyetemek beállítása
                ResultSet university=con.createStatement().executeQuery("SELECT * FROM institutions");

                ObservableList feltolt = FXCollections.observableArrayList();;
                while (university.next())
                {
                    feltolt.add(university.getString("name")+", "+university.getString("city"));
                }
                feltolt.sort(Comparator.comparing( String::toString)) ;
                egyetem.setItems(feltolt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        egyetem.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                try {
                    osszestargy.clear();
                    String tmp=(String)egyetem.getItems().get((Integer) number2);
                    choiceboxFeltolt(tmp.split(",")[0]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });





    }

    public void choiceboxFeltolt(String university) throws SQLException {
        con=dbconnection.getConn();
        ResultSet targyak=con.createStatement().executeQuery("SELECT * FROM Courses c JOIN Institutions i ON c.institutionID=i.ID WHERE i.name='"+university+"'");

        while (targyak.next())
        {
            osszestargy.add(targyak.getString("c.name"));
        }

        ObservableList<String> tmp = FXCollections.observableArrayList();
        for(String a:osszestargy)
        {
            tmp.add(a);
        }
        ConnectedComboBox<String> connectedComboBox = new ConnectedComboBox<>(tmp);
        connectedComboBox.addComboBox(targy1);
        connectedComboBox.addComboBox(targy2);
        connectedComboBox.addComboBox(targy3);
        connectedComboBox.addComboBox(targy4);
        connectedComboBox.addComboBox(targy5);
        connectedComboBox.addComboBox(targy6);
        connectedComboBox.addComboBox(targy7);
        connectedComboBox.addComboBox(targy8);
        connectedComboBox.addComboBox(targy9);
        connectedComboBox.addComboBox(targy10);
        connectedComboBox.addComboBox(targy11);
        connectedComboBox.addComboBox(targy12);
        connectedComboBox.addComboBox(targy13);
        connectedComboBox.addComboBox(targy14);
        connectedComboBox.addComboBox(targy15);
    }



    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(Jelentkezes.class.getResource("JelentkezesiLap.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();

        window.setScene(s);
        window.show();
    }

    public void save(ActionEvent actionEvent)
    {
        if (csekk1.isSelected() && csekk2.isSelected()) {
            hibauzenet.setText("");
            try {

            } catch (Exception ex) {
                hibauzenet.setText("Hiányzó adatok");
            }
        }else {
            hibauzenet.setText("Kérjük fogadd el a feltételeket.");
        }
    }
}
