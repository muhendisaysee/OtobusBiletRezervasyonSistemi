/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class Bilet {

    private int bilet_id;
    private String kalkis_tarihi;
    private String varis_tarihi;
    private String kalkis_yeri;
    private String varis_yeri;
    private String bilet_fiyat;
    private String kalkis_saati;
    private int satici_id;

    public Bilet() {
    }

    public Bilet(int bilet_id, String kalkis_tarihi, String varis_tarihi, String kalkis_yeri, String varis_yeri, String bilet_fiyat, String kalkis_saati, int satici_id) {
        this.bilet_id = bilet_id;
        this.kalkis_tarihi = kalkis_tarihi;
        this.varis_tarihi = varis_tarihi;
        this.kalkis_yeri = kalkis_yeri;
        this.varis_yeri = varis_yeri;
        this.bilet_fiyat = bilet_fiyat;
        this.kalkis_saati = kalkis_saati;
        this.satici_id = satici_id;
    }
    
    public int getBilet_id() {
        return bilet_id;
    }

    public void setBilet_id(int bilet_id) {
        this.bilet_id = bilet_id;
    }

    public String getKalkis_tarihi() {
        return kalkis_tarihi;
    }

    public void setKalkis_tarihi(String kalkis_tarihi) {
        this.kalkis_tarihi = kalkis_tarihi;
    }

    public String getVaris_tarihi() {
        return varis_tarihi;
    }

    public void setVaris_tarihi(String varis_tarihi) {
        this.varis_tarihi = varis_tarihi;
    }

    public String getKalkis_yeri() {
        return kalkis_yeri;
    }

    public void setKalkis_yeri(String kalkis_yeri) {
        this.kalkis_yeri = kalkis_yeri;
    }

    public String getVaris_yeri() {
        return varis_yeri;
    }

    public void setVaris_yeri(String varis_yeri) {
        this.varis_yeri = varis_yeri;
    }

    public String getBilet_fiyat() {
        return bilet_fiyat;
    }

    public void setBilet_fiyat(String bilet_fiyat) {
        this.bilet_fiyat = bilet_fiyat;
    }

    public String getKalkis_saati() {
        return kalkis_saati;
    }

    public void setKalkis_saati(String kalkis_saati) {
        this.kalkis_saati = kalkis_saati;
    }

    public int getSatici_id() {
        return satici_id;
    }

    public void setSatici_id(int satici_id) {
        this.satici_id = satici_id;
    }

    
    
    
    
    
    
    
    
    
}
