package sample;

public class Personel {

    private int id;
    private String ad;
    private String soyad;
    private String telefon;
    private String tarih;
    private Double maas;
    private String Pozisyon;
    private int PozisyonId;

    public Personel() {

    }

    public Personel(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
    }

    public Personel(int id, String ad, String soyad, String telefon, String tarih, double maas, String pozisyonId) {
        this.setId(id);
        this.setAd(ad);
        this.setSoyad(soyad);
        this.setTelefon(telefon);
        this.setTarih(tarih);
        this.setMaas(maas);
        this.setPozisyon(pozisyonId);
    }

    public Personel(int id, String ad, String soyad, String telefon, String tarih, double maas, int pozisyonId) {
        this.setId(id);
        this.setAd(ad);
        this.setSoyad(soyad);
        this.setTelefon(telefon);
        this.setTarih(tarih);
        this.setMaas(maas);
        this.setPozisyonId(pozisyonId);
    }

    public Personel(String ad, String soyad, String telefon, String tarih, double maas, int pozisyonId) {
        this.setAd(ad);
        this.setSoyad(soyad);
        this.setTelefon(telefon);
        this.setTarih(tarih);
        this.setMaas(maas);
        this.setPozisyonId(pozisyonId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Double getMaas() {
        return maas;
    }

    public void setMaas(Double maas) {
        this.maas = maas;
    }

    public String getPozisyon() {
        return Pozisyon;
    }

    public void setPozisyon(String pozisyonId) {
        Pozisyon = pozisyonId;
    }

    public int getPozisyonId() {
        return PozisyonId;
    }

    public void setPozisyonId(int pozisyonId) {
        PozisyonId = pozisyonId;
    }
}
