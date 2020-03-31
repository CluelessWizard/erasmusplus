package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Listazas implements Initializable {
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student,String> neptun;
    @FXML private TableColumn<Student,String> nev;
    @FXML private TableColumn<Student,String> telefon;
    @FXML private TableColumn<Student,String> email;
    @FXML private TableColumn<Student,String> szak;

    ObservableList<Student> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            Connection con=dbconnection.getConn();
            ResultSet rs=con.createStatement().executeQuery("select * from students");

            while (rs.next())
            {
                oblist.add(new Student(rs.getString("neptun"),rs.getString("name"),rs.getString("mobile"),rs.getString("email"),rs.getString("degree")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        neptun.setCellValueFactory(new PropertyValueFactory<>("neptun"));
        nev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        szak.setCellValueFactory(new PropertyValueFactory<>("szak"));

        table.setItems(oblist);
    }
}
