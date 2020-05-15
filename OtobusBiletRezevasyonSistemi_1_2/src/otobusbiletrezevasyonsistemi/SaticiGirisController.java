/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Satici;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlElement;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SaticiGirisController implements Initializable {

    @FXML
    public PasswordField telefon;
    @FXML
    public PasswordField kullaniciSifre;
    SaticiController saticiController = new SaticiController();
    SaticiBiletDuzenleController saticiBiletDuzenleController = new SaticiBiletDuzenleController();
    saticiDAO saticiDAO = new saticiDAO();

    @FXML
    public void SaticigirisKontrol(ActionEvent a) throws SQLException {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        boolean girisKontrol = false;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from satici");

            while (rs.next()) {
                //  Satici satici=new Satici(rs.getLong("satici_id"), rs.getString("satici_adi"), rs.getString("satici_soyadi"), rs.getString("satici_tel"), rs.getString("satici_adres"), rs.getString("sifre"));
                //Satici satici=new Satici();
                Satici satici = new Satici(rs.getInt("satici_id"), rs.getString("satici_adi_soyadi"),
                        rs.getString("satici_tel"), rs.getString("satici_adres"), rs.getString("satici_sifre"));
                satici.setSatici_id(rs.getInt("satici_id"));
                satici.setSatici_adi_soyadi(rs.getString("satici_adi_soyadi"));
                satici.setSatici_adres(rs.getString("satici_adres"));
                satici.setSatici_tel(rs.getString("satici_tel"));
                satici.setSatici_sifre(rs.getString("satici_sifre"));
                if (telefon.getText().equals(rs.getString("satici_tel"))
                        && kullaniciSifre.getText().equals(rs.getString("satici_sifre"))) {
                    System.out.println("Sisteme giren kullanıcı  adi soyadi: " + rs.getString("satici_adi_soyadi"));
                    System.out.println("Sisteme giren kullanıcı adresi : " + rs.getString("satici_adres"));

                    //deneme.giris(satici);
                    saticiController.aktifSatici = satici;
                    saticiBiletDuzenleController.aktifSatici = satici;
                    saticiDAO.aktifSatici = satici;

                    girisKontrol = true;
                    giris(a);
                }
            }
            if (girisKontrol == false) {
                JOptionPane.showMessageDialog(null, "Hatali sifre veya kullanici adi !"
                        + " YENİDEN DENEYİNİZ!!");
            }
        } catch (SQLException ex) {
            System.err.println("Hata");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void giris(ActionEvent a) {

        GirisOnCommand command = new GirisOnCommand();
        System.err.println("SistemeGiris metodundayım");
        command.giris("Satici", a);
    }

    @FXML
    private void cikis(ActionEvent a) {
        GirisOffCommand command = new GirisOffCommand();
        System.err.println("SistemeGiris metodundayım");
        command.giris("Satici", a);

    }

}
