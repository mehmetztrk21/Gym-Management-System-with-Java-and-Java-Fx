package sample;

public class Musteri {
    private int id;
    private String ad;
    private String soyad;
    private int CategoryId;
    private double ucret;
    private String telefon;
    private String tarih;
    private String talep;
    private String category;

    public Musteri() {

    }

    public Musteri(int id, String ad, String soyad, String telefon, String tarih, String talep, double ucret, String kategori) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.tarih = tarih;
        this.talep = talep;
        this.ucret = ucret;
        this.category = kategori;
    }

    public Musteri(String ad, String soyad, String telefon, String talep, String tarih, Double ucret, int kayit) {
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.talep = talep;
        this.tarih = tarih;
        this.ucret = ucret;
        this.CategoryId = kayit;
        //                    m.create(new Personel(ad,soyad,telefon,talep,tarih,ucret,kayıt));
    }

    public Musteri(int id, String ad, String soyad, String telefon, String talep, String tarih, Double ucret, int kayit) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.talep = talep;
        this.tarih = tarih;
        this.ucret = ucret;
        this.CategoryId = kayit;
        //                    m.create(new Personel(ad,soyad,telefon,talep,tarih,ucret,kayıt));
    }

    public Musteri(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
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

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public double getUcret() {
        return ucret;
    }

    public void setUcret(double ucret) {
        this.ucret = ucret;
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

    public String getTalep() {
        return talep;
    }

    public void setTalep(String talep) {
        this.talep = talep;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
