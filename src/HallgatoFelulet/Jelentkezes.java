package HallgatoFelulet;

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

public class Jelentkezes extends OpenFunctions implements Initializable {

    public class tablaLista
    {
        String id;
        String egyetem;
        String varos;

        public tablaLista(String id, String egyetem, String varos) {
            this.id = id;
            this.egyetem = egyetem;
            this.varos = varos;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEgyetem() {
            return egyetem;
        }

        public void setEgyetem(String egyetem) {
            this.egyetem = egyetem;
        }

        public String getVaros() {
            return varos;
        }

        public void setVaros(String varos) {
            this.varos = varos;
        }

    }
    

    @FXML
    TableView<tablaLista> table;

    @FXML TableColumn<tablaLista,String> jelentkezesID;
    @FXML TableColumn<tablaLista,String> jelEgyetem;
    @FXML TableColumn<tablaLista,String> jelVaros;

    private static int jelentkezesekSzama=0;

    public static int getJelentkezesekSzama() {
        return jelentkezesekSzama;
    }

    private static ObservableList<tablaLista>oblist = FXCollections.observableArrayList();

    private static Connection con;

    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(Jelentkezes.class.getResource("Jelentkezes.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

    public void refresh()
    {
        oblist.clear();
        try {
            con= dbconnection.getConn();
            ResultSet app1=con.createStatement().executeQuery("SELECT * FROM students s JOIN applications a ON s.ApplicationID1=a.ID JOIN institutions i ON i.ID=a.institutionID WHERE s.neptun='"+ LoginController.getUsername()+"'");
            ResultSet app2=con.createStatement().executeQuery("SELECT * FROM students s JOIN applications a ON s.ApplicationID2=a.ID JOIN institutions i ON i.ID=a.institutionID WHERE s.neptun='"+ LoginController.getUsername()+"'");
            ResultSet app3=con.createStatement().executeQuery("SELECT * FROM students s JOIN applications a ON s.ApplicationID3=a.ID JOIN institutions i ON i.ID=a.institutionID WHERE s.neptun='"+ LoginController.getUsername()+"'");

            while (app1.next())
            {
                oblist.add(new tablaLista(app1.getString("a.ID"),app1.getString("i.name"),app1.getString("i.city")));
            }
            while (app2.next())
            {
                oblist.add(new tablaLista(app2.getString("a.ID"),app2.getString("i.name"),app2.getString("i.city")));
            }
            while (app3.next())
            {
                oblist.add(new tablaLista(app3.getString("a.ID"),app3.getString("i.name"),app3.getString("i.city")));
            }
            jelentkezesekSzama=oblist.size();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        jelentkezesID.setCellValueFactory(new PropertyValueFactory<>("id"));
        jelEgyetem.setCellValueFactory(new PropertyValueFactory<>("egyetem"));
        jelVaros.setCellValueFactory(new PropertyValueFactory<>("varos"));

        if (oblist.size()>0)
        {
            table.setItems(oblist);
        }
        else table.setPlaceholder(new Label("Nincsenek Jelentkezéseid"));
    }


    public void newApp(ActionEvent actionEvent) throws IOException {
        if (jelentkezesekSzama<3) JelentkezesiLap.megnyit(actionEvent);
    }

    public void delete(ActionEvent actionEvent) throws SQLException {
        try {
            tablaLista a = table.getSelectionModel().getSelectedItem();
            con.createStatement().executeUpdate("DELETE FROM applications WHERE ID=" + a.getId());
            refresh();
        }catch(Exception ex){ }
    }

    public void update()
    {
        refresh();
    }
}
