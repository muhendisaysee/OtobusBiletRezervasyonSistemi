/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import entity.Musteri;
import entity.Satici;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MusteriGirisController implements Initializable {

    @FXML
    public PasswordField telefon;
    @FXML
    public PasswordField kullaniciSifre;
    musteriDAO musteriDAO = new musteriDAO();
    MusteriBiletDuzenleController musteriBiletDuzenleController = new MusteriBiletDuzenleController();
    MusteriController musteriController = new MusteriController();

    @FXML
    public void MusterigirisKontrol(ActionEvent a) throws SQLException {
        DBConnection db = DBConnection.getDBConnection();

        Connection c = db.connect();
        boolean girisKontrol = false;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from musteri");

            while (rs.next()) {
                Musteri musteri = new Musteri(rs.getInt("musteri_id"), rs.getString("musteri_adi_soyadi"), rs.getString("musteri_tel"), rs.getString("musteri_adres"), rs.getString("musteri_sifre"));
                musteri.setMusteri_id(rs.getInt("musteri_id"));
                musteri.setMusteri_adi_soyadi(rs.getString("musteri_adi_soyadi"));
                musteri.setMusteri_adres(rs.getString("musteri_adres"));
                musteri.setMusteri_tel(rs.getString("musteri_tel"));
                musteri.setMusteri_sifre(rs.getString("musteri_sifre"));
                if (telefon.getText().equals(rs.getString("musteri_tel"))
                        && kullaniciSifre.getText().equals(rs.getString("musteri_sifre"))) {
                    System.out.println("Sisteme giren müşteri ad, soyad : " + rs.getString("musteri_adi_soyadi"));
                    System.out.println("Sisteme giren müşteri adres : " + rs.getString("musteri_adres"));
                    musteriDAO.aktifMusteri = musteri;
                    musteriBiletDuzenleController.aktifMusteri = musteri;
                    musteriController.aktifMusteri = musteri;

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

    @FXML
    private void giris(ActionEvent a) {

        GirisOnCommand command = new GirisOnCommand();
        command.giris("Musteri", a);

    }

    @FXML
    private void cikis(ActionEvent a) {
        GirisOffCommand command = new GirisOffCommand();
        command.giris("Musteri", a);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
