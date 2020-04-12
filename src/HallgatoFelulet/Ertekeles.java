package HallgatoFelulet;

import app.LoginController;
import app.dbconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Ertekeles extends OpenFunctions implements Initializable {

    private Connection con;
    private String neptun;
    private int uniID;

    final ToggleGroup ElsoTG=new ToggleGroup();
    final ToggleGroup MasodikTG=new ToggleGroup();
    final ToggleGroup HarmadikTG=new ToggleGroup();
    final ToggleGroup NegyedikTG=new ToggleGroup();
    final ToggleGroup OtodikTG=new ToggleGroup();

    @FXML
    ToggleButton FirstNS,FirstNM,FirstNB,FirstPS,FirstPM,FirstPB;

    @FXML
    ToggleButton SecondNS,SecondNM,SecondNB,SecondPS,SecondPM,SecondPB;

    @FXML
    ToggleButton ThirdNS,ThirdNM,ThirdNB,ThirdPS,ThirdPM,ThirdPB;

    @FXML
    ToggleButton FourthNS,FourthNM,FourthNB,FourthPS,FourthPM,FourthPB;

    @FXML
    ToggleButton FifthNS,FifthNM,FifthNB,FifthPS,FifthPM,FifthPB;

    @FXML
    ToggleButton FirstNeut,SecondNeut,ThirdNeut,FourthNeut,FifthNeut;

    @FXML
    Label hibauzenet;

    @FXML
    TextField ertekeltVaros,ertekeltEgyetem;

    int FirstValue=0,SecondValue=0,ThirdValue=0,FourthValue=0,FifthValue=0;


    public static void megnyit(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(Ertekeles.class.getResource("Ertekeles.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            con= dbconnection.getConn();
            neptun=LoginController.getUsername();

            //A bejelentkezett felhasználóhoz tartozó jelentkezések lekérése
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM students WHERE neptun='"+neptun+"'");

            int jel1=0,jel2=0,jel3=0;

            while (rs.next())
            {
                    jel1=rs.getInt("applicationID1");
                    jel2=rs.getInt("applicationID2");
                    jel3=rs.getInt("applicationID3");
            }


            //Ha van elfogadott jelentkezés, akkor az ahhoz tartozó egyetemID lekérése
            rs=con.createStatement().executeQuery("SELECT * FROM applications");

            while(rs.next())
            {
                if ((rs.getInt("ID")==jel1 || rs.getInt("ID")==jel2 || rs.getInt("ID")==jel3) && rs.getInt("accepted")==1)
                {
                    uniID=rs.getInt("institutionID");
                }
            }

            //Egyetem és város lekérése

            rs=con.createStatement().executeQuery("SELECT * FROM institutions WHERE ID="+uniID);

            while (rs.next()) {
                ertekeltEgyetem.setText(rs.getString("name"));
                ertekeltVaros.setText(rs.getString("city"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        FirstNS.setToggleGroup(ElsoTG); SecondNS.setToggleGroup(MasodikTG); ThirdNS.setToggleGroup(HarmadikTG); FourthNS.setToggleGroup(NegyedikTG); FifthNS.setToggleGroup(OtodikTG);
        FirstNM.setToggleGroup(ElsoTG); SecondNM.setToggleGroup(MasodikTG); ThirdNM.setToggleGroup(HarmadikTG); FourthNM.setToggleGroup(NegyedikTG); FifthNM.setToggleGroup(OtodikTG);
        FirstNB.setToggleGroup(ElsoTG); SecondNB.setToggleGroup(MasodikTG); ThirdNB.setToggleGroup(HarmadikTG); FourthNB.setToggleGroup(NegyedikTG); FifthNB.setToggleGroup(OtodikTG);
        FirstPS.setToggleGroup(ElsoTG); SecondPS.setToggleGroup(MasodikTG); ThirdPS.setToggleGroup(HarmadikTG); FourthPS.setToggleGroup(NegyedikTG); FifthPS.setToggleGroup(OtodikTG);
        FirstPM.setToggleGroup(ElsoTG); SecondPM.setToggleGroup(MasodikTG); ThirdPM.setToggleGroup(HarmadikTG); FourthPM.setToggleGroup(NegyedikTG); FifthPM.setToggleGroup(OtodikTG);
        FirstPB.setToggleGroup(ElsoTG); SecondPB.setToggleGroup(MasodikTG); ThirdPB.setToggleGroup(HarmadikTG); FourthPB.setToggleGroup(NegyedikTG); FifthPB.setToggleGroup(OtodikTG);

        FirstNeut.setToggleGroup(ElsoTG);
        SecondNeut.setToggleGroup(MasodikTG);
        ThirdNeut.setToggleGroup(HarmadikTG);
        FourthNeut.setToggleGroup(NegyedikTG);
        FifthNeut.setToggleGroup(OtodikTG);
    }

    public void mentes()
    {
        hibauzenet.setTextFill(Color.web("#ff0000"));
        if (uniID!=0) {
            try {
                ertekbeallitas();
                try {
                    con.createStatement().executeUpdate("INSERT INTO feedback VALUES('" + neptun + "'," + uniID + "," + FirstValue + "," + SecondValue + "," + ThirdValue + "," + FourthValue + "," + FifthValue + ")");
                    hibauzenet.setTextFill(Color.web("#00ff00"));
                    hibauzenet.setText("Sikeresen kitöltötted a kérdőívet.");
                } catch (SQLException e) {
                    hibauzenet.setText("Már egyszer kitöltötted a kérdőívet.");
                }
            } catch (Exception ex) {
                hibauzenet.setText("Kérjük válaszolj minden kérdésre.");
            }
        }else hibauzenet.setText("Még nem vettél részt a programban");


    }

    public void ertekbeallitas()
    {
        switch (ElsoTG.getSelectedToggle().toString())
        {
            case "ToggleButton[id=FirstPB, styleClass=toggle-button]''": FirstValue=10;break;
            case "ToggleButton[id=FirstPM, styleClass=toggle-button]''": FirstValue=8;break;
            case "ToggleButton[id=FirstPS, styleClass=toggle-button]''": FirstValue=6;break;
            case "ToggleButton[id=FirstNS, styleClass=toggle-button]''": FirstValue=4;break;
            case "ToggleButton[id=FirstNM, styleClass=toggle-button]''": FirstValue=2;break;
            case "ToggleButton[id=FirstNB, styleClass=toggle-button]''": FirstValue=1;break;
            case "ToggleButton[id=FirstNeut, styleClass=toggle-button]''": FirstValue=5;break;
        }

        switch (MasodikTG.getSelectedToggle().toString())
        {
            case "ToggleButton[id=SecondPB, styleClass=toggle-button]''": SecondValue=10;break;
            case "ToggleButton[id=SecondPM, styleClass=toggle-button]''": SecondValue=8;break;
            case "ToggleButton[id=SecondPS, styleClass=toggle-button]''": SecondValue=6;break;
            case "ToggleButton[id=SecondNS, styleClass=toggle-button]''": SecondValue=4;break;
            case "ToggleButton[id=SecondNM, styleClass=toggle-button]''": SecondValue=2;break;
            case "ToggleButton[id=SecondNB, styleClass=toggle-button]''": SecondValue=1;break;
            case "ToggleButton[id=SecondNeut, styleClass=toggle-button]''": SecondValue=5;break;
        }

        switch (HarmadikTG.getSelectedToggle().toString())
        {
            case "ToggleButton[id=ThirdPB, styleClass=toggle-button]''": ThirdValue=10;break;
            case "ToggleButton[id=ThirdPM, styleClass=toggle-button]''": ThirdValue=8;break;
            case "ToggleButton[id=ThirdPS, styleClass=toggle-button]''": ThirdValue=6;break;
            case "ToggleButton[id=ThirdNS, styleClass=toggle-button]''": ThirdValue=4;break;
            case "ToggleButton[id=ThirdNM, styleClass=toggle-button]''": ThirdValue=2;break;
            case "ToggleButton[id=ThirdNB, styleClass=toggle-button]''": ThirdValue=1;break;
            case "ToggleButton[id=ThirdNeut, styleClass=toggle-button]''": ThirdValue=5;break;
        }

        switch (NegyedikTG.getSelectedToggle().toString())
        {
            case "ToggleButton[id=FourthPB, styleClass=toggle-button]''": FourthValue=10;break;
            case "ToggleButton[id=FourthPM, styleClass=toggle-button]''": FourthValue=8;break;
            case "ToggleButton[id=FourthPS, styleClass=toggle-button]''": FourthValue=6;break;
            case "ToggleButton[id=FourthNS, styleClass=toggle-button]''": FourthValue=4;break;
            case "ToggleButton[id=FourthNM, styleClass=toggle-button]''": FourthValue=2;break;
            case "ToggleButton[id=FourthNB, styleClass=toggle-button]''": FourthValue=1;break;
            case "ToggleButton[id=FourthNeut, styleClass=toggle-button]''": FourthValue=5;break;
        }

        switch (OtodikTG.getSelectedToggle().toString())
        {
            case "ToggleButton[id=FifthPB, styleClass=toggle-button]''": FifthValue=10;break;
            case "ToggleButton[id=FifthPM, styleClass=toggle-button]''": FifthValue=8;break;
            case "ToggleButton[id=FifthPS, styleClass=toggle-button]''": FifthValue=6;break;
            case "ToggleButton[id=FifthNS, styleClass=toggle-button]''": FifthValue=4;break;
            case "ToggleButton[id=FifthNM, styleClass=toggle-button]''": FifthValue=2;break;
            case "ToggleButton[id=FifthNB, styleClass=toggle-button]''": FifthValue=1;break;
            case "ToggleButton[id=FifthNeut, styleClass=toggle-button]''": FifthValue=5;break;
        }
    }
}
