package com.example.finalfinal;

public class Masa {
    private String masaId;
    private String durum;
    private String siparis;

    public Masa() {
        // Boş yapıcı metot gereklidir.
    }

    public Masa(String masaId, String durum, String siparis) {
        this.masaId = masaId;
        this.durum = durum;
        this.siparis = siparis;
    }

    public String getMasaId() {
        return masaId;
    }

    public void setMasaId(String masaId) {
        this.masaId = masaId;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getSiparis() {
        return siparis;
    }

    public void setSiparis(String siparis) {
        this.siparis = siparis;
    }
}


