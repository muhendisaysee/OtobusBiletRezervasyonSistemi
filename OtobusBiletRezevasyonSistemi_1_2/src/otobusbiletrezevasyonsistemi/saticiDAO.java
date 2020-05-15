/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import entity.Satici;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

/**
 *
 * @author hp
 */
public class saticiDAO {

    private DBConnection connector;
    private Connection connection;
    static Satici aktifSatici = new Satici();

    //SaticiGirisController giris=new SaticiGirisController();
  

    public void biletleriGoster() {
        DBConnection db = DBConnection.getDBConnection();
        Connection c = db.connect();
        SaticiController saticiController = new SaticiController();
        SaticiBiletDuzenleController saticiBiletDuzenleController = new SaticiBiletDuzenleController();
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from bilet where satici_id="
                    + aktifSatici.getSatici_id());

            while (rs.next()) {

                Bilet bilet = new Bilet(rs.getInt("bilet_id"),
                        rs.getString("kalkis_tarihi"),
                        rs.getString("varis_tarihi"),
                        rs.getString("kalkis_yeri"),
                        rs.getString("varis_yeri"),
                        rs.getString("bilet_fiyat"),
                        rs.getString("kalkis_saati"),
                        rs.getInt("satici_id"));
                saticiController.biletListele.add(bilet);
                saticiBiletDuzenleController.biletListele.add(bilet);
                System.out.println("Bilet id : " + bilet.getBilet_id());
                System.out.println("Kalkiş Tarihi : " + bilet.getKalkis_tarihi());
                System.out.println("Varış Tarihi  : " + bilet.getVaris_tarihi());
                System.out.println("Kalkış Yeri : " + bilet.getKalkis_yeri());
                System.out.println("Varış Yeri : " + bilet.getVaris_yeri());
                System.out.println("Bilet Fiyatı : " + bilet.getBilet_fiyat());
                System.out.println("Kalkış Saati : "+bilet.getKalkis_saati());
                System.out.println("-------------------------------------------");
            }
        } catch (SQLException e) {
            e.getMessage();
        }

    }

}
