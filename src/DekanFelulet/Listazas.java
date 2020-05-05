package DekanFelulet;

import StudentList.Student;
import app.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Listazas  extends FeluletValtas implements Initializable  {
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




    ObservableList<Student> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con= dbconnection.getConn();
            ResultSet rs=con.createStatement().executeQuery("select * from students s JOIN users u ON s.neptun=u.neptun");

            while (rs.next())
            {
                if (rs.getString("role").equals("1"))
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
        try{
        ObservableList<Student> tmp= FXCollections.observableArrayList();

        String sname=searchNameField.getText().toUpperCase();

        oblist.forEach((Student) -> {
            if(StudentList.KMP_algoritmus.KMP(Student.getNev().toUpperCase(),sname)) tmp.add(Student);
        });

        table.setItems(tmp);

        if (tmp.isEmpty())
        {
            table.setPlaceholder(new Label("Nincs találat"));
        }

    }catch (Exception ex)
    {
        table.setItems(oblist);
    }

    }

    public void searchNeptun()
    {
        try {
            ObservableList<Student> tmp = FXCollections.observableArrayList();

            String sname = searchNeptunField.getText().toUpperCase();

            oblist.forEach((Student) -> {
                if (StudentList.KMP_algoritmus.KMP(Student.getNeptun(), sname)) tmp.add(Student);
            });

            table.setItems(tmp);

            if (tmp.isEmpty()) {
                table.setPlaceholder(new Label("Nincs találat"));
            }
        }catch (Exception ex)
        {
            table.setItems(oblist);
        }
    }




}
