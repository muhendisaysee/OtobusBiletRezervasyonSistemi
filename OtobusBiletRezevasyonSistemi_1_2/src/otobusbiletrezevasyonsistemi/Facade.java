/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otobusbiletrezevasyonsistemi;

import entity.Bilet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javax.swing.Action;
import util.DBConnection;


/**
 *
 * @author hp
 */
public class Facade {
    private final FacadeServer facadeServer ;
    
    public Facade(FacadeServer facadeServer){
        this.facadeServer=facadeServer;
    }
    
    public void sistemiBaslat() throws SQLException{
        facadeServer.nesneOlustur(); //Veri tabanından nesne oluşur.
        facadeServer.dataBaseBaslat(); //Veri tabanınına bağlanır.
        facadeServer.dataBaseOku();   //Biletleri okur ve diğer arayüzlerdeki tablolara gönderir.
    }
    
    public void sistemiDurdur(){
        facadeServer.nesneTemizle();
        facadeServer.dataBaseDurdur();
        
    }
    }
    

