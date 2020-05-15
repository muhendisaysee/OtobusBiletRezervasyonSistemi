/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import entity.Satici;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SaticiController extends AbstractController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static Satici aktifSatici = new Satici();
    @FXML
    private TableView<Bilet> biletTableView;
    @FXML
    private TableColumn<Bilet, Long> bilet_idColumn;
    @FXML
    private TableColumn<Bilet, String> kalkisYeriColumn;
    @FXML
    private TableColumn<Bilet, String> varisYeriColumn;
    @FXML
    private TableColumn<Bilet, Integer> fiyatColumn;
    @FXML
    private TableColumn<Bilet, Integer> saticiColumn;
    @FXML
    private TableColumn<Bilet, String> varisTarihiColumn;
    @FXML
    private TableColumn<Bilet, String> kalkisTarihiColumn;
    @FXML
    private TableColumn<Bilet, String> kalkis_saatiColumn;
    @FXML
    private Label isim;

    public static List<Bilet> biletListele = new ArrayList();     //Okunan kitap bilgilerini tutan dizi

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        isim.setText(aktifSatici.getSatici_adi_soyadi());
        biletListele.removeAll(biletListele);
        saticiDAO.biletleriGoster();
        // akademisyenTableView.getItems().setAll(akademisyenDuyuru);
        biletTableView.getItems().setAll(biletListele);
        System.err.println("Satici isim : " + aktifSatici.getSatici_adi_soyadi());
        // System.out.println("isim :  "+biletListele.get(7).getSatici().getSatici_adi_soyadi());
        //tableView.getItems().setAll(duyuruListele); //Tabloda göster
        bilet_idColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Long>("bilet_id"));
        kalkisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_yeri"));
        varisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_yeri"));
        varisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_tarihi"));
        kalkisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_tarihi"));
        fiyatColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Integer>("bilet_fiyat"));
        kalkis_saatiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_saati"));
        saticiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Integer>("satici_id"));
    }

    @FXML
    private void cikis(ActionEvent a) {
        GirisOffCommand command = new GirisOffCommand();
        command.giris("Satici", a);
    }

    @FXML
    private void BiletDuzenle(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("SaticiBiletDuzenle.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SaticiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
