/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.Action;

/**
 *
 * @author hp
 */
public class Giris {

    public Giris() {
    }

    @FXML
    public void girisOn(String kullanici, ActionEvent a) {
        System.out.println("BURAYA GELİYOR MUSUN");
        if (kullanici.equals("Satici")) {
            try {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("satici.fxml"));
                Scene tableview = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
                window.setScene(tableview);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (kullanici.equals("Musteri")) {
            try {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("musteri.fxml"));
                Scene tableview = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
                window.setScene(tableview);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void girisOff(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
