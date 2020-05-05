package DekanFelulet;

import app.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TargyElfogadas extends FeluletValtas implements Initializable {

    @FXML
    ScrollPane spane;

    @FXML
    TextField neptunfield;

    @FXML TextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12,tf13,tf14,tf15;

    @FXML
    ChoiceBox jelentkezesfield;

    @FXML
    Label uzenet,keresUzenet;

    @FXML Label lb,lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10,lb11,lb12,lb13,lb14;

    private static Connection con=null;
    private static String keresettNeptun="";
    private static List<TextField> tfs=new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        keresUzenet.setText("");
        uzenet.setText("");

        tfs.add(tf1);
        tfs.add(tf2);
        tfs.add(tf3);
        tfs.add(tf4);
        tfs.add(tf5);
        tfs.add(tf6);
        tfs.add(tf7);
        tfs.add(tf8);
        tfs.add(tf9);
        tfs.add(tf10);
        tfs.add(tf11);
        tfs.add(tf12);
        tfs.add(tf13);
        tfs.add(tf14);
        tfs.add(tf15);


        try {
            con= dbconnection.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void keres(ActionEvent actionEvent)
    {
        keresUzenet.setText("");
        uzenet.setText("");
        for (int i=0;i<15;i++)
        {
            tfs.get(i).clear();
        }

        int []hallgato_jelentkezesei=new int[3];
        for (int i=0;i<3;i++) hallgato_jelentkezesei[i]=0;

        keresettNeptun=neptunfield.getText();

        try {
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM students WHERE neptun='"+keresettNeptun+"'");

            boolean letezik=false;

                while (rs.next()) {
                    if (rs.getString("neptun").equals(keresettNeptun)) {
                        letezik=true;
                        hallgato_jelentkezesei[0] = rs.getInt("applicationID1");
                        hallgato_jelentkezesei[1] = rs.getInt("applicationID2");
                        hallgato_jelentkezesei[2] = rs.getInt("applicationID3");
                    }
                }

                if (!letezik) {
                    keresUzenet.setText("Nincs ilyen hallgató az adatbázisban");
                }else{

                rs = con.createStatement().executeQuery("SELECT * FROM applications a JOIN institutions i ON a.institutionID=i.ID WHERE a.ID in (" + hallgato_jelentkezesei[0] + "," + hallgato_jelentkezesei[1] + "," + hallgato_jelentkezesei[2] + ") AND accepted=1");

                ObservableList<String> oblist = FXCollections.observableArrayList();

                int elf_id=0;

                while (rs.next()) {
                    oblist.add(rs.getString("i.name"));
                    elf_id=rs.getInt("a.ID");
                }

                refresh_courses(elf_id);

                jelentkezesfield.setItems(oblist);
                jelentkezesfield.setValue(oblist.get(0));
                spane.setVisible(true);



            }

        } catch (SQLException e) {
            spane.setVisible(false);
        } catch (Exception ex)
        {
            ex.printStackTrace();
            keresUzenet.setText("A hallgatónak nincs elfogadott jelentkezése");
        }
    }

    public void refresh_courses(int appID)
    {
        uzenet.setText("");
        for (int i=0;i<15;i++)
        {
            tfs.get(i).clear();
        }

        List<String> targyak=new ArrayList<>();
        List<Integer> azonositok=new ArrayList<>();
        List<Integer> kreditek=new ArrayList<>();
        try {
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM applications WHERE ID="+appID);
            while (rs.next())
            {
                for (int i=1;i<16;i++)
                azonositok.add(rs.getInt("selectedCourseID"+i));

            }



            rs=con.createStatement().executeQuery("SELECT * FROM courses");

            while (rs.next())
            {
                if (azonositok.contains(rs.getInt("ID")))
                {
                    targyak.add(rs.getString("name"));
                    kreditek.add(rs.getInt("credit"));
                }
            }


            //kreditek beállítása
            try{
                lb.setText(kreditek.get(0).toString());
                lb1.setText(kreditek.get(1).toString());
                lb2.setText(kreditek.get(2).toString());
                lb3.setText(kreditek.get(3).toString());
                lb4.setText(kreditek.get(4).toString());
                lb5.setText(kreditek.get(5).toString());
                lb6.setText(kreditek.get(6).toString());
                lb7.setText(kreditek.get(7).toString());
                lb8.setText(kreditek.get(8).toString());
                lb9.setText(kreditek.get(9).toString());
                lb10.setText(kreditek.get(10).toString());
                lb11.setText(kreditek.get(11).toString());
                lb12.setText(kreditek.get(12).toString());
                lb13.setText(kreditek.get(13).toString());
                lb14.setText(kreditek.get(14).toString());


            }catch(Exception ex)
            {
            }

            for (int i=0;i<targyak.size();i++)
            {
                tfs.get(i).setText(targyak.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void elkuld()
    {
        uzenet.setText("Sikeres rögzítés!");
    }
}
