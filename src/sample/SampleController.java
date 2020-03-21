package sample;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SampleController {


    public Label info;
    public PasswordField pw;
    public TextField user;

    Scene scene1;

    static HashMap<String, String> userpass = new HashMap<>();
    static List<String> oktazon=new ArrayList<String>();

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
    public void registration(ActionEvent actionEvent) throws IOException {

        RegisterForm r=new RegisterForm();
       /* Parent registration = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene reg = new Scene(registration);
        Stage reg_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        reg_stage.setScene(reg);
        reg_stage.show();*/
    }
}
