/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hp
 */
public class Satici {
    private int satici_id;
    private String satici_adi_soyadi;
    private String satici_adres;
    private String satici_tel;
    private String satici_sifre;

    public Satici() {
    }

    public Satici(int satici_id, String satici_adi_soyadi, String satici_adres, String satici_tel, String satici_sifre) {
        this.satici_id = satici_id;
        this.satici_adi_soyadi = satici_adi_soyadi;
        this.satici_adres = satici_adres;
        this.satici_tel = satici_tel;
        this.satici_sifre = satici_sifre;
    }

    public String getSatici_adi_soyadi() {
        return satici_adi_soyadi;
    }

    public void setSatici_adi_soyadi(String satici_adi_soyadi) {
        this.satici_adi_soyadi = satici_adi_soyadi;
    }

    
    public String getSatici_adres() {
        return satici_adres;
    }

    public void setSatici_adres(String satici_adres) {
        this.satici_adres = satici_adres;
    }

    public String getSatici_tel() {
        return satici_tel;
    }

    public void setSatici_tel(String satici_tel) {
        this.satici_tel = satici_tel;
    }

    public String getSatici_sifre() {
        return satici_sifre;
    }

    public void setSatici_sifre(String satici_sifre) {
        this.satici_sifre = satici_sifre;
    }

    public int getSatici_id() {
        return satici_id;
    }

    public void setSatici_id(int satici_id) {
        this.satici_id = satici_id;
    }

    @Override
    public String toString() {
        return "Satici{" + "satici_id=" + satici_id + ", satici_adi_soyadi=" + satici_adi_soyadi + ", satici_adres=" + satici_adres + ", satici_tel=" + satici_tel + ", satici_sifre=" + satici_sifre + '}';
    }

    
}
