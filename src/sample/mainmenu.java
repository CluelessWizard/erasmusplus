package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;



public class mainmenu {
    @FXML private Parent root;
    public void hallgatokmegnyit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Listazas.fxml"));
        Scene s = new Scene(p);

        //stage információ
        Stage window = (Stage) root.getScene().getWindow();

        window.setScene(s);
        window.show();
    }
    
}
