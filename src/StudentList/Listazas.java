package StudentList;

import Applications.Jelentkezesek;
import StudentInformation.BovebbInfoController;
import app.LoginController;
import app.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Listazas  implements Initializable  {
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student,String> neptun;
    @FXML private TableColumn<Student,String> nev;
    @FXML private TableColumn<Student,String> telefon;
    @FXML private TableColumn<Student,String> email;
    @FXML private TableColumn<Student,String> szak;
    @FXML ContextMenu cm;
    @FXML MenuItem mi2;
    @FXML Parent rt;


    static Student selectedforedit;

    public static Student getSelectedforedit() {
        return selectedforedit;
    }

    public void dropdowncontext() {

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });



    }


    public void BovebbInformacio(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        selectedforedit = table.getSelectionModel().getSelectedItem();
        new BovebbInfoController().megjelenites();
    }

    ObservableList<Student> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropdowncontext();

        try {
            Connection con= dbconnection.getConn();
            ResultSet rs=con.createStatement().executeQuery("select * from students");

            while (rs.next())
            {
                oblist.add(new Student(rs.getString("name"),rs.getString("neptun"),rs.getString("mobile"),rs.getString("email"),rs.getString("degree")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        neptun.setCellValueFactory(new PropertyValueFactory<>("neptun"));
        nev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        szak.setCellValueFactory(new PropertyValueFactory<>("szak"));

        if (oblist.isEmpty())
        {
            table.setPlaceholder(new Label("Nincsenek hallgatók az adatbázisban"));
        }
        else table.setItems(oblist);
    }

    @FXML TextField searchNameField;
    @FXML TextField searchNeptunField;

    public void searchName()
    {
        ObservableList<Student> tmp= FXCollections.observableArrayList();

        String sname=searchNameField.getText();

        oblist.forEach((Student) -> {
            if(Student.getNev().equals(sname)) tmp.add(Student);
        });

        table.setItems(tmp);

        if (tmp.isEmpty())
        {
            table.setPlaceholder(new Label("Nincs találat"));
        }

    }

    public void searchNeptun()
    {
        ObservableList<Student> tmp= FXCollections.observableArrayList();

        String sname=searchNeptunField.getText();

        oblist.forEach((Student) -> {
            if(Student.getNeptun().equals(sname)) tmp.add(Student);
        });

        table.setItems(tmp);

        if (tmp.isEmpty())
        {
            table.setPlaceholder(new Label("Nincs találat"));
        }

    }


    public void mainmenumegnyit(ActionEvent actionEvent) throws IOException {
        new LoginController().mainmenuopen(actionEvent);
    }

    public void Jelentkezesekopen(ActionEvent actionEvent) throws IOException {
        new Jelentkezesek().Jelentkezesopen(actionEvent);
    }


    public void kijelentkezes(ActionEvent actionEvent) throws Exception {
         new LoginController().megnyit(actionEvent);

    }

}
