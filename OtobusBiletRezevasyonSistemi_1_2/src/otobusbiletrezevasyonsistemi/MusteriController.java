/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import entity.Musteri;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static otobusbiletrezevasyonsistemi.SaticiBiletDuzenleController.aktifSatici;
import static otobusbiletrezevasyonsistemi.SaticiBiletDuzenleController.arananBilet;
import static otobusbiletrezevasyonsistemi.SaticiController.biletListele;
import sun.security.rsa.RSACore;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MusteriController extends musteriAbstractController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static Musteri aktifMusteri = new Musteri();
    musteriDAO musteriDAO = new musteriDAO();
    MusteriBiletDuzenleController musteriBiletDuzenleController = new MusteriBiletDuzenleController();

    @FXML
    private Label isim;
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
    public TextField aranacakBilet_id;
    @FXML
    private TableView<Bilet> arananBiletTableView;
    @FXML
    private TableColumn<Bilet, Long> arananBilet_idColumn;
    @FXML
    private TableColumn<Bilet, String> arananKalkisYeriColumn;
    @FXML
    private TableColumn<Bilet, String> arananVarisYeriColumn;
    @FXML
    private TableColumn<Bilet, String> arananBiletFiyatColumn;
    @FXML
    private TableColumn<Bilet, String> arananVarisTarihiColumn;
    @FXML
    private TableColumn<Bilet, String> arananKalkisTarihiColumn;
    @FXML
    private TableColumn<Bilet, String> arananKalkis_saatiColumn;

    public static List<Bilet> arananBilet = new ArrayList();
    public static List<Bilet> biletListele = new ArrayList();
    public static List<Bilet> alinanBiletler = new ArrayList();
    @FXML
    private TextField biletNo;
    @FXML
    private TextField secilenKoltuk;

    @FXML
    public void biletAl() {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();
        String biletNoText = biletNo.getText();
        String secilenKoltukText = secilenKoltuk.getText();
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from bilet where bilet_id=" + Integer.valueOf(biletNoText));
            while (rs.next()) {
                Bilet bilet = new Bilet(Integer.valueOf(biletNoText), rs.getString("kalkis_tarihi"),
                        rs.getString("varis_tarihi"), rs.getString("kalkis_yeri"), rs.getString("varis_yeri"),
                        rs.getString("kalkis_saati"), rs.getString("bilet_fiyat"), rs.getInt("satici_id"));
                st.executeUpdate("insert into musteri_bilet(musteri_id,"
                        + "bilet_id,koltuk_num) values('" + aktifMusteri.getMusteri_id()
                        + "','" + Integer.valueOf(biletNoText) + "','" + Integer.valueOf(secilenKoltukText) + "')");
                //musteriDAO.biletleriGoster();
                alinanBiletler.add(bilet);
                musteriBiletDuzenleController.alinanBiletler.add(bilet);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @FXML
    public void biletAra() {

        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        String aranacakBilet_idText;
        aranacakBilet_idText = aranacakBilet_id.getText();
        int bilet_id = Integer.valueOf(aranacakBilet_idText);
        boolean kontrol = false;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from bilet where bilet_id=" + bilet_id);
            System.out.println("BURAYA GELDİN Mİ?");
            while (rs.next()) {
                Bilet bilet = new Bilet(bilet_id,
                        rs.getString("kalkis_tarihi"), rs.getString("varis_tarihi"),
                        rs.getString("kalkis_yeri"), rs.getString("varis_yeri"),
                        rs.getString("bilet_fiyat"), rs.getString("kalkis_saati"),
                        rs.getInt("satici_id"));
                arananBilet.add(bilet);
                arananBiletTableView.getItems().setAll(arananBilet);
                kontrol = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (kontrol == false) {
            JOptionPane.showMessageDialog(null, " BİLET BULUNAMADI ! ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        isim.setText(aktifMusteri.getMusteri_adi_soyadi());
        biletListele.removeAll(biletListele);
        musteriDAO.tumBiletleriGoster();
        //musteriDAO.musteriListelemeTest();
        // akademisyenTableView.getItems().setAll(akademisyenDuyuru);
        biletTableView.getItems().setAll(biletListele);
        arananBiletTableView.getItems().setAll(arananBilet);

        bilet_idColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Long>("bilet_id"));
        kalkisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_yeri"));
        varisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_yeri"));
        varisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_tarihi"));
        kalkisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_tarihi"));
        fiyatColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Integer>("bilet_fiyat"));
        kalkis_saatiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_saati"));
        saticiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Integer>("satici_id"));

        arananBilet_idColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Long>("bilet_id"));
        arananKalkisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_yeri"));
        arananVarisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_yeri"));
        arananVarisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_tarihi"));
        arananKalkisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_tarihi"));
        arananBiletFiyatColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("bilet_fiyat"));
        arananKalkis_saatiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_saati"));

    }

    @FXML
    private void cikis(ActionEvent a) {
        GirisOffCommand command = new GirisOffCommand();
        command.giris("Musteri", a);
    }

    @FXML
    private void biletlerim(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MusteriBiletDuzenle.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
