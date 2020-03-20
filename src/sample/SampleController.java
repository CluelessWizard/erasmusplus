package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.HashMap;


public class SampleController {


    public Label info;
    public PasswordField pw;
    public TextField user;

    static HashMap<String, String> userpass = new HashMap<>();

    public void login(ActionEvent actionEvent) {

        String username=user.getText();
        String password=pw.getText();

        if(userpass.containsKey(username))
        {

           if(userpass.get(username).equals(password)){
                info.setText("Sikeres bejelentkezés!");
           }
           else {
                info.setText("Hibás jelszó!");
            }
        }
        else {
            info.setText("Hibás felhasználó!");
        }
    }
}
