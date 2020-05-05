package HallgatoFelulet;

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
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Egyetemek extends OpenFunctions implements Initializable {

    private static ObservableList<University_feedback> oblist= FXCollections.observableArrayList();

    @FXML
    TableColumn egyetem,varos,homerseklet,koltseg,latvanyossag,szinvonal,ertekeles;

    @FXML
    TableView table;

    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(Egyetemek.class.getResource("Egyetemek.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            oblist.clear();
            Connection con=dbconnection.getConn();

            ResultSet rs=con.createStatement().executeQuery("SELECT i.ID,i.name,i.city,count(i.ID) as db,sum(question1) as q1,sum(question3) as q3,sum(question4) as q4,sum(question5) as q5 FROM institutions i LEFT JOIN feedback f ON i.ID=f.universityID GROUP BY i.ID");


            while (rs.next())
            {
                try {
                    double homerseklet=api(rs.getString("city"));
                    if (rs.getInt("q1")==0) {
                        oblist.add(new University_feedback(rs.getString("name"), rs.getString("city"), homerseklet+" °C", rs.getDouble("q1") / rs.getDouble("db"), rs.getDouble("q3") / rs.getDouble("db"), rs.getDouble("q4") / rs.getDouble("db"), 0));
                    }
                    else oblist.add(new University_feedback(rs.getString("name"), rs.getString("city"), homerseklet+" °C", rs.getDouble("q1")/ rs.getDouble("db"), rs.getDouble("q3") / rs.getDouble("db"), rs.getDouble("q4") / rs.getDouble("db"), rs.getInt("db")));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            egyetem.setCellValueFactory(new PropertyValueFactory<>("egyetem"));
            varos.setCellValueFactory(new PropertyValueFactory<>("varos"));
            homerseklet.setCellValueFactory(new PropertyValueFactory<>("homerseklet"));
            koltseg.setCellValueFactory(new PropertyValueFactory<>("koltseg"));
            latvanyossag.setCellValueFactory(new PropertyValueFactory<>("latvanyossag"));
            szinvonal.setCellValueFactory(new PropertyValueFactory<>("szinvonal"));
            ertekeles.setCellValueFactory(new PropertyValueFactory<>("ertekeles"));

            if (oblist.size()>0)
            {
                table.setItems(oblist);
            }
            else table.setPlaceholder(new Label("Nincs értékelés az adatbázisban"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public double api(String varos) throws	IOException, MalformedURLException, JSONException {
     			OpenWeatherMap owm	=	new	OpenWeatherMap("be2be6bfe1297eafe7fbe2ebf344997a");
      			CurrentWeather cwd	=	owm.currentWeatherByCityName(varos);
   						return new Double(df.format((cwd.getMainInstance().getTemperature()-32)/1.8));
    }
}
