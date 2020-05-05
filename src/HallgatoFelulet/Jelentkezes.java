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

    private boolean elf1=true,elf2=true,elf3=true;

    public class tablaLista
    {
        String id;
        String egyetem;
        String varos;
        String statusz;

        public String getStatusz() {
            return statusz;
        }

        public void setStatusz(String statusz) {
            this.statusz = statusz;
        }

        public tablaLista(String id, String egyetem, String varos, String statusz) {
            this.id = id;
            this.egyetem = egyetem;
            this.varos = varos;
            this.statusz = statusz;
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

    @FXML Label hibauzenet;

    @FXML
    TableView<tablaLista> table;

    @FXML TableColumn<tablaLista,String> jelentkezesID;
    @FXML TableColumn<tablaLista,String> jelEgyetem;
    @FXML TableColumn<tablaLista,String> jelVaros;
    @FXML TableColumn<tablaLista,String> jelStatusz;

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

            String st="";
            while (app1.next())
            {
                if (app1.getInt("accepted")==1){ elf1=false; st="Elfogadva";}
                else if (app1.getInt("accepted")==-1){st="Elutasítva";}
                else st="Függőben";
                oblist.add(new tablaLista(app1.getString("a.ID"),app1.getString("i.name"),app1.getString("i.city"),st));
            }
            while (app2.next())
            {
                if (app2.getInt("accepted")==1){ elf2=false; st="Elfogadva";}
                else if (app2.getInt("accepted")==-1){st="Elutasítva";}
                else st="Függőben";
                oblist.add(new tablaLista(app2.getString("a.ID"),app2.getString("i.name"),app2.getString("i.city"),st));
            }
            while (app3.next())
            {
                if (app3.getInt("accepted")==1){ elf3=false; st="Elfogadva";}
                else if (app3.getInt("accepted")==-1){st="Elutasítva";}
                else st="Függőben";
                oblist.add(new tablaLista(app3.getString("a.ID"),app3.getString("i.name"),app3.getString("i.city"),st));
            }
            jelentkezesekSzama=oblist.size();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        jelentkezesID.setCellValueFactory(new PropertyValueFactory<>("id"));
        jelEgyetem.setCellValueFactory(new PropertyValueFactory<>("egyetem"));
        jelVaros.setCellValueFactory(new PropertyValueFactory<>("varos"));
        jelStatusz.setCellValueFactory(new PropertyValueFactory<>("statusz"));

        if (oblist.size()>0)
        {
            table.setItems(oblist);
        }
        else table.setPlaceholder(new Label("Nincsenek Jelentkezéseid"));
    }


    public void newApp(ActionEvent actionEvent) throws IOException {

        boolean szemelyesadatok=true;

        try {
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM students WHERE neptun='"+LoginController.getUsername()+"'");
            if (!rs.next()) szemelyesadatok=false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (szemelyesadatok) {
            if (jelentkezesekSzama < 3) {
                if (elf1 && elf2 && elf3) {
                    JelentkezesiLap.megnyit(actionEvent);
                } else {
                    hibauzenet.setText("Már van elfogadott jelentkezésed.");
                }
            } else {
                hibauzenet.setText("A jelentkezéseid száma elérte a maximumot.");
            }
        }else hibauzenet.setText("Add meg a személyes adataid!");
    }

    public void delete(ActionEvent actionEvent) throws SQLException {
        if (elf1 && elf2 && elf3) {
            try {
                tablaLista a = table.getSelectionModel().getSelectedItem();
                con.createStatement().executeUpdate("DELETE FROM applications WHERE ID=" + a.getId());
                refresh();
            } catch (Exception ex) {
            }
        }else hibauzenet.setText("Már van elfogadott jelentkezésed.");
    }

    private static int selected_ID=0;
    private static String selected_Egyetem=null;

    public static String getSelected_Egyetem() {
        return selected_Egyetem;
    }

    public static String getSelected_Varos() {
        return selected_Varos;
    }

    private static String selected_Varos=null;

    public static int getSelected_ID() {
        return selected_ID;
    }



    public void reszletek(ActionEvent actionEvent) {
        try {
            selected_ID = Integer.parseInt(table.getSelectionModel().getSelectedItem().id);
            selected_Varos = table.getSelectionModel().getSelectedItem().varos;
            selected_Egyetem = table.getSelectionModel().getSelectedItem().egyetem;

            if (selected_ID != 0)
                reszletekOpen(actionEvent);
        }catch (Exception ex)
        {

        }
    }


}
