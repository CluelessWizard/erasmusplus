package AdminFelulet;

import app.dbconnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModositasController implements Initializable {


    @FXML
    TextField name,neptun;
    @FXML
    ChoiceBox jog;
    @FXML
    Button mentes,megse;

    private static Connection con;
    private static ResultSet rs;


    private user chosenOne;

    public void modositasmegnyit() throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource("Modositas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = new Stage();
        window.setTitle("Felhasználó adatainak módosítása");

        window.setScene(s);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.chosenOne=adminmenuController.getChosenOne();

        name.setText(chosenOne.getNev());
        neptun.setText(chosenOne.getNeptun());
        jog.setValue(chosenOne.getRole());

        try {
            con = dbconnection.getConn();
            rs = con.createStatement().executeQuery("Select * from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() throws SQLException {
        String EditedName=name.getText();
        String EditedNeptun=neptun.getText();
        String EditedRole=(String)jog.getValue();
        System.out.println(EditedRole);
        int roleNumber=1;
        if (EditedRole.equals("Ügyintéző")) roleNumber=2;
        else if(EditedRole.equals("Dékán")) roleNumber=3;
        else if(EditedRole.equals("Admin")) roleNumber=4;

        con.createStatement().executeUpdate("UPDATE users SET name='"+EditedName+"',neptun='"+EditedNeptun+"',role="+roleNumber+" WHERE neptun='"+chosenOne.getNeptun()+"'");

        ObservableList<user> oblist;
        oblist=adminmenuController.getOblist();

        for (user s:oblist)
        {
            if (s.getNeptun()==chosenOne.getNeptun())
            {
                s.setNeptun(EditedNeptun);
                s.setNev(EditedName);
                s.setRole(EditedRole);
            }
        }

        try {
            new adminmenuController().update();
        }catch(Exception ex) { }

        Stage stage = (Stage) mentes.getScene().getWindow();
        stage.close();
    }

    public void kilep()
    {
        Stage stage = (Stage) megse.getScene().getWindow();
        stage.close();
    }

}
