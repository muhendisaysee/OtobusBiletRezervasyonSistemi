package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author hp
 */
public class FacadeServer {
    public DBConnection nesneOlustur() {
        DBConnection db = DBConnection.getDBConnection();
        return db;
    }
    public void dataBaseBaslat() {
        DBConnection db = nesneOlustur();
        db.connect();
    }
    public void dataBaseOku() throws SQLException {
        DBConnection db = nesneOlustur();
        Connection c = db.connect();
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from bilet");
            while (rs.next()) {
                Bilet bilet = new Bilet(rs.getInt("bilet_id"),
                                        rs.getString("kalkis_tarihi"),
                                        rs.getString("varis_tarihi"), 
                                        rs.getString("kalkis_yeri"),
                                        rs.getString("varis_yeri"),
                                        rs.getString("bilet_fiyat"), 
                                        rs.getString("kalkis_saati"), 
                                        rs.getInt("satici_id"));
                MusteriController.biletListele.add(bilet);
                SaticiController.biletListele.add(bilet);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    public void nesneTemizle() {
        Runtime.getRuntime().gc(); //Çöp temizleyici
        MusteriController.biletListele = null;
        MusteriBiletDuzenleController.alinanBiletler = null;
        SaticiController.biletListele = null;
        SaticiBiletDuzenleController.biletListele = null;
    }
    public void dataBaseDurdur() {
        DBConnection db = nesneOlustur();
        db = null;
    }
}
