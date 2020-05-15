/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import entity.Musteri;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static otobusbiletrezevasyonsistemi.saticiDAO.aktifSatici;
import util.DBConnection;

/**
 *
 * @author hp
 */
public class musteriDAO {
    
    static Musteri aktifMusteri=new Musteri();
    private DBConnection connector;
    private Connection connection;
    
     public void biletleriGoster()  {
          DBConnection db=DBConnection.getDBConnection();
          Connection c = db.connect();
       
          try{
              Statement st=c.createStatement();
              ResultSet rs=st.executeQuery("select bilet_id from musteri_bilet");
              
              while(rs.next()){
                
                  bilet_bul(rs.getInt("bilet_id"));
                  
              }
          }catch(SQLException e){
              e.getMessage();
          }
          
    }
    public void bilet_bul(int bilet_id){
    DBConnection db=DBConnection.getDBConnection();
          Connection c = db.connect();
        try{
            Statement st=c.createStatement();
              ResultSet rs=st.executeQuery("select * from bilet where bilet_id="+bilet_id);
              while (rs.next()) {
               
                 Bilet bilet=new Bilet(rs.getInt("bilet_id"), rs.getString("kalkis_tarihi"),
                         rs.getString("varis_tarihi"), rs.getString("kalkis_yeri"),
                         rs.getString("varis_yeri"), rs.getString("bilet_fiyat"),
                         rs.getString("kalkis_saati"),
                         rs.getInt("satici_id"));
               
                  MusteriController.alinanBiletler.add(bilet);
                  MusteriBiletDuzenleController.alinanBiletler.add(bilet);
            }
           
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    public void tumBiletleriGoster()  {
    DBConnection db=DBConnection.getDBConnection();
          Connection c = db.connect();
           SaticiController saticiController=new SaticiController();
           SaticiBiletDuzenleController saticiBiletDuzenleController=new SaticiBiletDuzenleController();
          try{
              Statement st=c.createStatement();
              ResultSet rs=st.executeQuery("select * from bilet");
              
              while(rs.next()){
                  
                  Bilet bilet=new Bilet(rs.getInt("bilet_id"), rs.getString("kalkis_tarihi"), rs.getString("varis_tarihi"), rs.getString("kalkis_yeri"), rs.getString("varis_yeri"), rs.getString("bilet_fiyat"),rs.getString("kalkis_saati"), rs.getInt("satici_id"));
                  MusteriController.biletListele.add(bilet);
                  
              }
          }catch(SQLException e){
              e.getMessage();
          }
          
    }
 

  
}
