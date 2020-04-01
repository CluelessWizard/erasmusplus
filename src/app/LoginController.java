package app;

import javafx.event.ActionEvent;
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


public class LoginController {


    public Label info;
    public PasswordField pw;
    public TextField user;

    Scene scene1;

    static HashMap<String, String> userpass = new HashMap<>();
    static HashMap<String, String> userRole = new HashMap<>();

    public void login(ActionEvent actionEvent) throws IOException {

        String username=user.getText();
        String password=pw.getText();

        if(userpass.containsKey(username))
        {

           if(userpass.get(username).equals(password)){
                if (userRole.get(username).equals("2") || userRole.get(username).equals("3")) {
                    Parent p = FXMLLoader.load(getClass().getResource("../MainMenu/mainmenu.fxml"));
                    Scene s = new Scene(p);

                    //stage információ
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    window.setScene(s);
                    window.show();
                }
        }
           else {
                info.setText("Hibás jelszó!");
            }
        }
        else {
            info.setText("Hibás felhasználó!");
        }
    }
    public void registration(ActionEvent actionEvent) throws IOException {

        RegisterForm r=new RegisterForm();
       /* Parent registration = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene reg = new Scene(registration);
        Stage reg_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        reg_stage.setScene(reg);
        reg_stage.show();*/
    }
}
