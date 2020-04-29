package HallgatoFelulet;

import app.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JelentkezesReszletek implements Initializable {

    public static class ttt
    {
        int id;
        String name;

        int credit;

        public ttt(int id, String name, int credit) {
            this.id = id;
            this.name = name;
            this.credit = credit;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCredit() {
            return credit;
        }
    };

    private static int jelentkezesID=0;
    private static String Egyetem=null;
    private static String Varos=null;

    @FXML
    TableColumn<ttt,String> name;
    @FXML
    TableColumn<ttt, Integer> credit;
    @FXML
    TableColumn<ttt,Integer> id;
    @FXML
    TableView<ttt> table;

    @FXML
    TextField egyetem,varos;

    public static void megnyit(javafx.event.ActionEvent actionEvent) throws IOException {

        Parent p = FXMLLoader.load(Jelentkezes.class.getResource("JelentkezesReszletek.fxml"));
        Scene s = new Scene(p);

        Stage window = new Stage();

        window.setScene(s);
        window.show();
    }

    ObservableList<ttt> oblist= FXCollections.observableArrayList();

    Connection con;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jelentkezesID=Jelentkezes.getSelected_ID();
        Egyetem=Jelentkezes.getSelected_Egyetem();
        Varos=Jelentkezes.getSelected_Varos();

        egyetem.setText(Egyetem);
        varos.setText(Varos);

        try {
            con=dbconnection.getConn();

            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM applications WHERE ID='"+jelentkezesID+"'");
            List<Integer> tmp=new ArrayList<>();

            while (rs.next())
            {
                tmp.add(rs.getInt("selectedCourseID1"));
                tmp.add(rs.getInt("selectedCourseID2"));
                tmp.add(rs.getInt("selectedCourseID3"));
                tmp.add(rs.getInt("selectedCourseID4"));
                tmp.add(rs.getInt("selectedCourseID5"));
                tmp.add(rs.getInt("selectedCourseID6"));
                tmp.add(rs.getInt("selectedCourseID7"));
                tmp.add(rs.getInt("selectedCourseID8"));
                tmp.add(rs.getInt("selectedCourseID9"));
                tmp.add(rs.getInt("selectedCourseID10"));
                tmp.add(rs.getInt("selectedCourseID11"));
                tmp.add(rs.getInt("selectedCourseID12"));
                tmp.add(rs.getInt("selectedCourseID13"));
                tmp.add(rs.getInt("selectedCourseID14"));
                tmp.add(rs.getInt("selectedCourseID15"));
            }

            rs = con.createStatement().executeQuery("SELECT * FROM Courses c JOIN institutions i ON i.id=c.institutionID WHERE i.name='"+Egyetem+"'");

            ObservableList<ttt> targyak= FXCollections.observableArrayList();

            while (rs.next())
            {
                targyak.add(new ttt(rs.getInt("ID"), rs.getString("name"), rs.getInt("credit")));
            }

            for (int i=0;i<targyak.size();i++)
            {
                if (tmp.contains(targyak.get(i).getId()))
                {
                    oblist.add(new ttt(targyak.get(i).getId(), targyak.get(i).getName(), targyak.get(i).getCredit()));
                }
            }

            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            table.setItems(oblist);



        } catch (SQLException e) {

        }
    }
}
