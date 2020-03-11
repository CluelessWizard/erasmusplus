package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SampleController {


    public Label info;
    public PasswordField pw;
    public TextField user;

    static String adminUser = "admin", adminPassword = "admin";

    public void login(ActionEvent actionEvent) {
        if(user.getText().equals(adminUser))
        {
            if(pw.getText().equals(adminPassword)){
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
