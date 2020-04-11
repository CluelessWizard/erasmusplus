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
import javafx.scene.control.*;
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

    @FXML Button mentes,megse;

    ChoiceBox[] chosenTargyak=new ChoiceBox[15];

    String chosenEgyetem="";
    String chosenVaros="";
    String chosenEgyetemID="0";

    private List<String> osszestargy=new ArrayList<>();

    public void targySetinit()
    {
        chosenTargyak[0]=targy1;
        chosenTargyak[1]=targy2;
        chosenTargyak[2]=targy3;
        chosenTargyak[3]=targy4;
        chosenTargyak[4]=targy5;
        chosenTargyak[5]=targy6;
        chosenTargyak[6]=targy7;
        chosenTargyak[7]=targy8;
        chosenTargyak[8]=targy9;
        chosenTargyak[9]=targy10;
        chosenTargyak[10]=targy11;
        chosenTargyak[11]=targy12;
        chosenTargyak[12]=targy13;
        chosenTargyak[13]=targy14;
        chosenTargyak[14]=targy15;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        targySetinit();

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
                    for (int i=0;i<chosenTargyak.length;i++) chosenTargyak[i].getSelectionModel().clearSelection();
                    String tmp=(String)egyetem.getItems().get((Integer) number2);
                    choiceboxFeltolt(tmp.split(",")[0]);
                    chosenEgyetem=tmp.split(",")[0];
                    chosenVaros=tmp.split(", ")[1];
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
                con=dbconnection.getConn();
                //Kiválasztott egyetem ID lekérdezése
                ResultSet rs=con.createStatement().executeQuery("SELECT * FROM institutions WHERE name='"+chosenEgyetem+"' AND city='"+chosenVaros+"'");

                while(rs.next())
                {
                    chosenEgyetemID=rs.getString("ID");
                }

                //Kiválasztott tárgy ID-K lekérdezése
                rs=con.createStatement().executeQuery("SELECT * FROM courses WHERE InstitutionID='"+chosenEgyetemID+"'");
                String[] targyak=new String[15];
                Boolean alltrue=true;

                for (int i=0;i<chosenTargyak.length;i++)
                {
                    if (chosenTargyak[i].getSelectionModel().isEmpty())
                    {
                        throw new Exception();
                    }
                }

                //Kiválasztott tárgy ID-k beállítása
                while (rs.next())
                {
                    for (int i=0;i<chosenTargyak.length;i++)
                    {
                        if (rs.getString("name").equals(chosenTargyak[i].getSelectionModel().getSelectedItem()))
                        {
                            targyak[i]=rs.getString("ID");
                        }
                    }
                }

                //Legnagyobb ID lekérdezése (ha ezt nem tesszük meg, akkor valamiért onnan folytatja a hozzáadást, ahol abbahagyta, akkor is ha már töröltük..
                ResultSet utolsoID=con.createStatement().executeQuery("SELECT ID FROM applications ORDER BY ID desc LIMIT 1");
                int lastID=0;
                while(utolsoID.next()) lastID=Integer.parseInt(utolsoID.getString("ID"));
                con.createStatement().executeUpdate("INSERT INTO applications (ID,institutionID,selectedCourseID1,selectedCourseID2,selectedCourseID3" +
                        ",selectedCourseID4,selectedCourseID5,selectedCourseID6,selectedCourseID7,selectedCourseID8,selectedCourseID9,selectedCourseID10,selectedCourseID11" +
                        ",selectedCourseID12,selectedCourseID13,selectedCourseID14,selectedCourseID15) VALUES("+(lastID+1)+","+chosenEgyetemID+","+targyak[0]+","+targyak[1]+","+targyak[2]+
                        ","+targyak[3]+","+targyak[4]+","+targyak[5]+","+targyak[6]+","+targyak[7]+","+targyak[8]+","+targyak[9]+","+targyak[10]+","+targyak[11]+
                        ","+targyak[12]+","+targyak[13]+","+targyak[14]+")");

                //ablak bezárása jelentkezés után
                Stage stage = (Stage) mentes.getScene().getWindow();
                stage.close();

                //adatok hozzáadása students táblához
                String a="";
                switch (Jelentkezes.getJelentkezesekSzama())
                {
                    case 0:
                        a="applicationID1";
                        break;
                    case 1:
                        a="applicationID2";
                        break;
                    case 2:
                        a="applicationID3";
                        break;
                }
                con.createStatement().executeUpdate("UPDATE students SET "+a+"="+(lastID+1)+" WHERE neptun='"+LoginController.getUsername()+"'");

                //Jelentkezes frissítése
                new Jelentkezes().refresh();


            } catch (Exception ex) {
                hibauzenet.setText("Hiányzó adatok");
            }
        }else {
            hibauzenet.setText("Kérjük fogadd el a feltételeket.");
        }
    }

    public void cancel()
    {
        Stage stage = (Stage) megse.getScene().getWindow();
        stage.close();
    }
}
