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
public class Musteri {
    private int musteri_id;
    private String musteri_adi_soyadi;
    private String musteri_tel;
    private String musteri_adres;
    private String musteri_sifre;

    public Musteri() {
    }

    public Musteri(int musteri_id, String musteri_adi_soyadi, String musteri_tel, String musteri_adres, String musteri_sifre) {
        this.musteri_id = musteri_id;
        this.musteri_adi_soyadi = musteri_adi_soyadi;
        this.musteri_tel = musteri_tel;
        this.musteri_adres = musteri_adres;
        this.musteri_sifre = musteri_sifre;
    }

    public String getMusteri_adi_soyadi() {
        return musteri_adi_soyadi;
    }

    public void setMusteri_adi_soyadi(String musteri_adi_soyadi) {
        this.musteri_adi_soyadi = musteri_adi_soyadi;
    }
    

    public int getMusteri_id() {
        return musteri_id;
    }

    public void setMusteri_id(int musteri_id) {
        this.musteri_id = musteri_id;
    }
    
    public String getMusteri_tel() {
        return musteri_tel;
    }

    public void setMusteri_tel(String musteri_tel) {
        this.musteri_tel = musteri_tel;
    }

    public String getMusteri_adres() {
        return musteri_adres;
    }

    public void setMusteri_adres(String musteri_adres) {
        this.musteri_adres = musteri_adres;
    }

    public String getMusteri_sifre() {
        return musteri_sifre;
    }

    public void setMusteri_sifre(String musteri_sifre) {
        this.musteri_sifre = musteri_sifre;
    }

    
    
}
