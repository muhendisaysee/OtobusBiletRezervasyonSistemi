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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static otobusbiletrezevasyonsistemi.MusteriController.biletListele;
import static otobusbiletrezevasyonsistemi.SaticiBiletDuzenleController.aktifSatici;
import static otobusbiletrezevasyonsistemi.SaticiBiletDuzenleController.biletListele;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MusteriBiletDuzenleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static Musteri aktifMusteri = new Musteri();
    public static List<Bilet> alinanBiletler = new ArrayList();
    musteriDAO musteriDAO = new musteriDAO();
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
                st.executeUpdate("delete from musteri_bilet where bilet_id="
                        + bilet.getBilet_id());
                alinanBiletler.remove(bilet);
            } catch (SQLException exception) {
                exception.getMessage();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        alinanBiletler.removeAll(alinanBiletler);
        musteriDAO.biletleriGoster();
        biletTableView.getItems().setAll(alinanBiletler);

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
    private void geri(ActionEvent a) {
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

    @FXML
    private void cikis(ActionEvent a) {
        FacadeServer facadeServer=new FacadeServer();
        Facade facade=new Facade(facadeServer);
        facade.sistemiDurdur();
        GirisOffCommand command = new GirisOffCommand();
        command.giris("Musteri", a);

    }
}
