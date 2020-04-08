package AdminFelulet;

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

public class adminmenuController implements Initializable {

    private static Connection con;
    private static ResultSet rs;
    private static ObservableList<user> oblist = FXCollections.observableArrayList();
    private static user chosenOne;



    public static user getChosenOne() {
        return chosenOne;
    }

    @FXML TableColumn neptun,nev,tipus,email;
    @FXML TableView table;

    public static ObservableList<user> getOblist() {
        return oblist;
    }

    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(adminmenuController.class.getResource("adminmenu.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adatok();


    }

    public void adatok()
    {
        oblist.clear();
        try {
            con= dbconnection.getConn();
            rs=con.createStatement().executeQuery("Select * from users");

            while (rs.next())
            {
                String tmp="";
                if (rs.getString("role").equals("1")) tmp="Hallgató";
                else if (rs.getString("role").equals("2")) tmp="Ügyintéző";
                else if (rs.getString("role").equals("3")) tmp="Dékán";
                else tmp="Admin";
                oblist.add(new user(rs.getString("neptun"),rs.getString("name"),tmp,rs.getString("email")));
            }

            neptun.setCellValueFactory(new PropertyValueFactory<>("neptun"));
            nev.setCellValueFactory(new PropertyValueFactory<>("nev"));
            tipus.setCellValueFactory(new PropertyValueFactory<>("role"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (oblist.isEmpty())
        {
            table.setPlaceholder(new Label("Nincsenek felhasználók az adatbázisban"));
        }
        else table.setItems(oblist);
    }


    public void edit() throws IOException {
        chosenOne= (user) table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItem()!=null)
        {
            new ModositasController().modositasmegnyit();
        }
    }



    public void update()
    {
        adatok();
    }

    public void delete() throws SQLException, IOException {
        if (table.getSelectionModel().getSelectedItem()!=null) {
            chosenOne= (user) table.getSelectionModel().getSelectedItem();
            con.createStatement().executeUpdate("DELETE FROM users WHERE neptun='" + chosenOne.getNeptun() + "'");
            oblist.remove(table.getSelectionModel().getSelectedItem());
        }
        new AdminPopups().torolveMegnyit();
    }

    public void passwordReset() throws IOException, SQLException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            chosenOne = (user) table.getSelectionModel().getSelectedItem();
            con.createStatement().executeUpdate("UPDATE users SET password='NEPTUN' WHERE neptun='" + chosenOne.getNeptun() + "'");
        }
        new AdminPopups().visszaalitvaMegnyit();
    }

    public void kijelentkezes(ActionEvent actionEvent) throws Exception {
        new LoginController().megnyit(actionEvent);
    }


}
