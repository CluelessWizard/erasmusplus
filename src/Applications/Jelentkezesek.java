package Applications;

import MainMenu.mainmenu;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Jelentkezesek implements Initializable {

    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb3;
    @FXML
    TableView table;
    @FXML
    TableColumn neptun;
    @FXML
    TableColumn name;
    @FXML
    TableColumn application1;
    @FXML
    TableColumn application2;
    @FXML
    TableColumn application3;
    @FXML
    private Parent root;

    static HallgatoJelentkezesei chosenOne;
    private static Connection con;
    private static ResultSet rs;
    private static ResultSet university1, university2, university3;
    private ObservableList<HallgatoJelentkezesei> oblist = FXCollections.observableArrayList();


    private static int ElfogadottSzama;

    public void Jelentkezesopen(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Jelentkezesek.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }



    public void mainmenuopen(javafx.event.ActionEvent actionEvent) throws IOException {
        new LoginController().mainmenuopen(actionEvent);
    }

    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        new mainmenu().hallgatokmegnyit(actionEvent);
    }


    public void Accept() throws SQLException, IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            chosenOne = (HallgatoJelentkezesei) table.getSelectionModel().getSelectedItem();
            if (rb1.isSelected()) {
                ElfogadottSzama = 1;
            } else if (rb2.isSelected()) {
                ElfogadottSzama = 2;
            } else {
                ElfogadottSzama = 3;
            }
            if (ElfogadottSzama == 1 && chosenOne.getEgyetem1()!="nincs") dbmanipulate();
            else if (ElfogadottSzama == 2 && chosenOne.getEgyetem2()!="nincs") dbmanipulate();
            else if (ElfogadottSzama == 3 && chosenOne.getEgyetem3()!="nincs") dbmanipulate();
            else new popupController().hibauzenetopen();
        }


    }

    public void Reject() throws SQLException, IOException {
        if (table.getSelectionModel().getSelectedItem()!=null) {
            chosenOne = (HallgatoJelentkezesei) table.getSelectionModel().getSelectedItem();
            con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID1=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem1() + "' AND s.neptun='" + chosenOne.getNeptun() + "'");
            con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID2=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem2() + "' AND s.neptun='" + chosenOne.getNeptun() + "'");
            con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID3=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem3() + "' AND s.neptun='" + chosenOne.getNeptun() + "'");
            new popupController().elutasit();
        }
    }

    public void dbmanipulate() throws SQLException, IOException {

            if (ElfogadottSzama == 1) {
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID1=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=1 WHERE i.name='" + chosenOne.getEgyetem1()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID2=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem2()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID3=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem3()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
            } else if (ElfogadottSzama == 2) {
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID1=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem1()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID2=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=1 WHERE i.name='" + chosenOne.getEgyetem2()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID3=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem3()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
            } else if (ElfogadottSzama==3) {
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID1=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem1()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID2=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=-1 WHERE i.name='" + chosenOne.getEgyetem2()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
                con.createStatement().executeUpdate("UPDATE applications a JOIN students s ON s.applicationID3=a.ID JOIN institutions i ON a.institutionID=i.ID SET a.accepted=1 WHERE i.name='" + chosenOne.getEgyetem3()+"' AND s.neptun='"+chosenOne.getNeptun()+"'");
            }
            new popupController().megnyit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            con = dbconnection.getConn();
            rs = con.createStatement().executeQuery("select * from users");
            university1 = con.createStatement().executeQuery("select * from students s JOIN Applications a ON s.applicationID1=a.ID JOIN institutions i ON i.ID=a.institutionID");
            university2 = con.createStatement().executeQuery("select * from students s JOIN Applications a ON s.applicationID2=a.ID JOIN institutions i ON i.ID=a.institutionID");
            university3 = con.createStatement().executeQuery("select * from students s JOIN Applications a ON s.applicationID3=a.ID JOIN institutions i ON i.ID=a.institutionID");

            ObservableList<HallgatoJelentkezesei> tmp = FXCollections.observableArrayList();
            
            
            while (rs.next())
            {
                tmp.add(new HallgatoJelentkezesei(rs.getString("neptun"),rs.getString("name"),"nincs",
                        "nincs","nincs"));
            }

            int i=0;

            while (university1.next()) {

                tmp.get(i).setEgyetem1(university1.getString("i.name"));
                i++;
            }

            i=0;

            while (university2.next()) {

                tmp.get(i).setEgyetem2(university2.getString("i.name"));
                i++;
            }

            i=0;

            while (university3.next()) {

                tmp.get(i).setEgyetem3(university3.getString("i.name"));
                i++;

            }

            for (HallgatoJelentkezesei s:tmp)
            {
                if (!(s.getEgyetem1().equals("nincs") && s.getEgyetem2().equals("nincs") && s.getEgyetem3().equals("nincs")))
                {
                    oblist.add(s);
                }
            }

    } catch(
    SQLException e)

    {
        e.printStackTrace();
    }



        neptun.setCellValueFactory(new PropertyValueFactory<>("neptun"));
        name.setCellValueFactory(new PropertyValueFactory<>("nev"));
        application1.setCellValueFactory(new PropertyValueFactory<>("egyetem1"));
        application2.setCellValueFactory(new PropertyValueFactory<>("egyetem2"));
        application3.setCellValueFactory(new PropertyValueFactory<>("egyetem3"));



        if (oblist.isEmpty())
        {
            table.setPlaceholder(new Label("Nincsenek jelentkezések az adatbázisban"));
        }
        else
        {
            table.setItems(oblist);
        }

    }

    public void kijelentkezes(ActionEvent actionEvent) throws Exception {
        new LoginController().megnyit(actionEvent);
    }
}
