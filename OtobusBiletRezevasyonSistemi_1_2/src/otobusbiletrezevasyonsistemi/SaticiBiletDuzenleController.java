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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static otobusbiletrezevasyonsistemi.SaticiController.aktifSatici;
import sun.security.rsa.RSACore;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SaticiBiletDuzenleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static List<Bilet> biletListele = new ArrayList();
    public static List<Bilet> arananBilet = new ArrayList();

    saticiDAO saticiDAO = new saticiDAO();

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
    private TableColumn<Bilet, String> fiyatColumn;
    @FXML
    private TableColumn<Bilet, Integer> saticiColumn;
    @FXML
    private TableColumn<Bilet, String> varisTarihiColumn;
    @FXML
    private TableColumn<Bilet, String> kalkisTarihiColumn;
    @FXML
    private TableColumn<Bilet, String> kalkis_saatiColumn;
    @FXML
    public ComboBox<String> guncellenecek = new ComboBox();
    @FXML
    public TextField varis_yeri;
    @FXML
    public TextField kalkis_yeri;
    @FXML
    public DatePicker varis_tarihi;
    @FXML
    public DatePicker kalkis_tarihi;
    @FXML
    public TextField fiyati;
    @FXML
    public TextField kalkis_saati;
    @FXML
    public TextField guncelDeger;
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

    @FXML
    public void biletEkle() {

        String kalkisTarihiText = kalkis_tarihi.getValue().toString();
        String varisTarihiText = varis_tarihi.getValue().toString();
        String kalkisYeriText = kalkis_yeri.getText();
        String varisYeriText = varis_yeri.getText();
        String fiyatText = fiyati.getText();
        String kalkisSaatiText = kalkis_saati.getText();
        Integer saticiIdText = aktifSatici.getSatici_id();

        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        try {
            Statement st = c.createStatement();
            st.executeUpdate("insert into bilet(kalkis_tarihi,varis_tarihi,kalkis_yeri,"
                    + "varis_yeri,bilet_fiyat,kalkis_saati,satici_id)"
                    + "values('" + kalkisTarihiText + "','" + varisTarihiText + "','"
                    + kalkisYeriText + "','" + varisYeriText + "','" + fiyatText + "','"
                    + kalkisSaatiText + "','" + saticiIdText + "')");

        } catch (SQLException exception) {
            exception.getMessage();
        }
        biletListele.removeAll(biletListele);
        saticiDAO.biletleriGoster();
        biletTableView.getItems().setAll(biletListele);

    }

    @FXML
    public void biletSil() {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        ObservableList<Bilet> selectedRows, allbilet;
        allbilet = biletTableView.getItems();
        selectedRows = biletTableView.getSelectionModel().getSelectedItems();
        for (Bilet bilet : selectedRows) {
            allbilet.remove(bilet);
            try {
                Statement st = c.createStatement();
                st.executeUpdate("delete from bilet where bilet_id="
                        + bilet.getBilet_id());
                st.executeUpdate("delete from musteri_bilet where bilet_id=" + bilet.getBilet_id());

            } catch (SQLException exception) {
                exception.getMessage();
            }
        }
    }

    @FXML
    public void biletGuncelle() {

        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();

        ObservableList<Bilet> selectedRows, allbilet;
        allbilet = biletTableView.getItems();

        selectedRows = biletTableView.getSelectionModel().getSelectedItems();
        String guncelDegerText = guncelDeger.getText();
        if (guncellenecek.getValue().equals("Kalkış Tarihi")) {
            for (Bilet bilet : selectedRows) {
                guncellenecek.setValue("Kalkış Tarihi");
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("update bilet set kalkis_tarihi='" + guncelDegerText + "' where bilet_id=" + bilet.getBilet_id());

                } catch (SQLException e) {
                    e.getMessage();
                }
            }

        } else if (guncellenecek.getValue().equals("Varış Tarihi")) {
            for (Bilet bilet : selectedRows) {
                guncellenecek.setValue("Varış Tarihi");
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("update bilet set varis_tarihi='" + guncelDegerText + "' where bilet_id=" + bilet.getBilet_id());
                } catch (SQLException e) {
                    e.getMessage();
                }
            }

        } else if (guncellenecek.getValue().equals("Kalkış Yeri")) {
            for (Bilet bilet : selectedRows) {
                guncellenecek.setValue("Kalkış Yeri");
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("update bilet set kalkis_yeri='" + guncelDegerText + "' where bilet_id=" + bilet.getBilet_id());
                } catch (SQLException e) {
                    e.getMessage();
                }
            }

        } else if (guncellenecek.getValue().equals("Varış Yeri")) {
            for (Bilet bilet : selectedRows) {
                guncellenecek.setValue("Varış Yeri");
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("update bilet set varis_yeri='" + guncelDegerText + "' where bilet_id=" + bilet.getBilet_id());
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        } else if (guncellenecek.getValue().equals("Kalkış Saati")) {
            for (Bilet bilet : selectedRows) {
                guncellenecek.setValue("Kalkış Saati");
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("update bilet set kalkis_saati='" + guncelDegerText + "' where bilet_id=" + bilet.getBilet_id());
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        } else if (guncellenecek.getValue().equals("Bilet Fiyatı")) {
            for (Bilet bilet : selectedRows) {
                guncellenecek.setValue("Bilet Fiyatı");
                try {
                    Statement st = c.createStatement();
                    st.executeUpdate("update bilet set bilet_fiyat='" + guncelDegerText + "' where bilet_id=" + bilet.getBilet_id());
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
        biletListele.removeAll(biletListele);
        saticiDAO.biletleriGoster();
        biletTableView.getItems().setAll(biletListele);
    }

    @FXML
    public void biletAra() {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();
        String aranacakBilet_idText;
        aranacakBilet_idText = aranacakBilet_id.getText();
        boolean kontrol = false;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from bilet where bilet_id='"
                    + Integer.valueOf(aranacakBilet_idText)
                    + "' and satici_id='" + aktifSatici.getSatici_id() + "'");
            while (rs.next()) {
                Bilet bilet = new Bilet(Integer.valueOf(aranacakBilet_idText),
                        rs.getString("kalkis_tarihi"), rs.getString("varis_tarihi"), rs.getString("kalkis_yeri"), rs.getString("varis_yeri"),
                        rs.getString("bilet_fiyat"), rs.getString("kalkis_saati"),
                        aktifSatici.getSatici_id());
                System.out.println("Bilet Bulundu");
                arananBilet.add(bilet);
                arananBiletTableView.getItems().setAll(arananBilet);
                arananBilet.removeAll(arananBilet);
                kontrol = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (kontrol == false) {
            JOptionPane.showMessageDialog(null, " BİLET BULUNAMADI ! ");
            System.out.println("Bilet Bulunamadı");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guncellenecek.getItems().add("Kalkış Tarihi");
        guncellenecek.getItems().add("Varış Tarihi");
        guncellenecek.getItems().add("Kalkış Yeri");
        guncellenecek.getItems().add("Varış Yeri");
        guncellenecek.getItems().add("Kalkış Saati");
        guncellenecek.getItems().add("Bilet Fiyatı");
        // aramaTest();
        // saticiDAO.listeleTest();
        biletListele.removeAll(biletListele);
        saticiDAO.biletleriGoster();
        biletTableView.getItems().setAll(biletListele);

        arananBiletTableView.getItems().setAll(arananBilet);

        bilet_idColumn.setCellValueFactory(new PropertyValueFactory<Bilet, Long>("bilet_id"));
        kalkisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_yeri"));
        varisYeriColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_yeri"));
        varisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("varis_tarihi"));
        kalkisTarihiColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("kalkis_tarihi"));
        fiyatColumn.setCellValueFactory(new PropertyValueFactory<Bilet, String>("bilet_fiyat"));
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
    private void geri(ActionEvent a) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("satici.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SaticiBiletDuzenleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cikis(ActionEvent a) {
        GirisOffCommand command = new GirisOffCommand();
        command.giris("Satici", a);
    }
}
