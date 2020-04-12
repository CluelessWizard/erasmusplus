package app;

import AdminFelulet.adminmenuController;
import HallgatoFelulet.OpenFunctions;
import HallgatoFelulet.StudentMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class LoginController extends OpenFunctions {

    @FXML
    Label info;
    @FXML
    PasswordField pw;
    @FXML
    TextField user;

    private static String username;
    static HashMap<String, String> userpass = new HashMap<>();
    static HashMap<String, String> userRole = new HashMap<>();

    public LoginController(){
        try {
            dbconnection.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUsername() { return username; }

    public void login(ActionEvent actionEvent) throws IOException {

        dbconnection.refresh();
        username=user.getText().toUpperCase();
        String password = pw.getText();

        if(userpass.containsKey(username)){
            if(userpass.get(username).equals(password)){
                switch (userRole.get(username)){
                    case "1":
                        StudentMain.megnyit(actionEvent);
                        break;
                    case "2":
                    case "3":
                        mainmenuopen(actionEvent);
                        break;
                    case "4":
                        adminmenuController.megnyit(actionEvent);
                        break;
                }
            }
            else info.setText("Hibás jelszó!");
        }
        else info.setText("Hibás felhasználó!");
    }

    public void registration(ActionEvent actionEvent) throws IOException {
        RegController.megnyit(actionEvent);
    }

    public void mainmenuopen(ActionEvent actionEvent) throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource("../MainMenu/mainmenu.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }

    public static void megnyit(ActionEvent actionEvent) throws IOException {

        Parent p = FXMLLoader.load(LoginController.class.getResource("login.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(s);
        window.show();
    }
}
