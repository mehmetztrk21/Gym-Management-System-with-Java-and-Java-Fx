package sample;

import java.sql.*;
import java.util.ArrayList;

public class MySqlMusteri implements MusteriDal {
    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.
    ResultSet resultSet2;


    @Override
    public void create(Musteri m) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "insert into musteri (Ad,Soyad,Telefon,BaslamaTarihi,Talep,Ucret,CategoryId) values(?,?,?,?,?,?,?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sql); //sql kodunun çalışması için
            statement2.setString(1, m.getAd());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, m.getSoyad());
            statement2.setString(3, m.getTelefon());
            statement2.setString(4, m.getTarih());
            statement2.setString(5, m.getTalep());
            statement2.setDouble(6, m.getUcret());
            statement2.setInt(7, m.getCategoryId());

            int result = statement2.executeUpdate();  //etkilenen satır sayısını verir.

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public int delete(int id) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql_delete = "delete from musteri where id=?";
            statement2 = connection.prepareStatement(sql_delete);
            statement2.setInt(1, id);
            int result = statement2.executeUpdate();
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Musteri> getAll() {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT p.id,p.Ad,p.Soyad,p.Telefon,p.BaslamaTarihi,p.Talep,p.Ucret,po.Kategori FROM musteri p inner join category po on p.CategoryId=po.id order by p.id");  //gelen sonuçlar da resultSet e aktarılıyor.

            ArrayList<Musteri> musteriler = new ArrayList<Musteri>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                musteriler.add(new Musteri(resultSet.getInt("id"), resultSet.getString("Ad"), resultSet.getString("Soyad"), resultSet.getString("Telefon"), resultSet.getString("BaslamaTarihi"), resultSet.getString("Talep"), resultSet.getDouble("Ucret"), resultSet.getString("Kategori"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return musteriler;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public String GetByid(int id) {
        String person = " ";
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT Ad,Soyad from musteri where id=" + id);  //gelen sonuçlar da resultSet e aktarılıyor.

            ArrayList<Musteri> musteriler = new ArrayList<Musteri>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                musteriler.add(new Musteri(resultSet.getString("Ad"), resultSet.getString("Soyad"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            System.out.println(musteriler.size());  //kaç tane veri geldiğini ekrana yazdık.
            return musteriler.get(0).getAd() + " " + musteriler.get(0).getSoyad();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return person;
    }

    @Override
    public ArrayList<Musteri> findMusteri(String name, String surname, int kayıt) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT p.id,p.Ad,p.Soyad,p.Telefon,p.BaslamaTarihi,p.Talep,p.Ucret,po.Kategori FROM musteri p inner join category po on p.CategoryId=po.id where Lcase(p.Ad)=Lcase('" + name + "') or Lcase(p.Soyad)=Lcase('" + surname + "') or po.id=" + kayıt + " order by p.id");  //gelen sonuçlar da resultSet e aktarılıyor.

            ArrayList<Musteri> musteriler = new ArrayList<Musteri>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                musteriler.add(new Musteri(resultSet.getInt("id"), resultSet.getString("Ad"), resultSet.getString("Soyad"), resultSet.getString("Telefon"), resultSet.getString("BaslamaTarihi"), resultSet.getString("Talep"), resultSet.getDouble("Ucret"), resultSet.getString("Kategori"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return musteriler;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getAll();
    }

    @Override
    public int update(Musteri p) {
        int result;
        try {
            connection = helper.getConnection();  //bağlanma.
            String sql_update = "update musteri set Ad=? ,Soyad=?, Telefon=? , BaslamaTarihi=? , Talep=? ,Ucret=?, CategoryId=? where id=?";
            statement2 = connection.prepareStatement(sql_update);
            statement2.setString(1, p.getAd());
            statement2.setString(2, p.getSoyad());
            statement2.setString(3, p.getTelefon());
            statement2.setString(4, p.getTarih());
            statement2.setString(5, p.getTalep());
            statement2.setDouble(6, p.getUcret());
            statement2.setInt(7, p.getCategoryId());
            statement2.setInt(8, p.getId());
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı
            return result;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        return 0;

    }

    @Override
    public double totalA() {
        double hesap = 0;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT sum(Ucret) as aylık from musteri where CategoryId=2");  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                hesap = Double.parseDouble(resultSet.getString("aylık"));
            }
            return hesap;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        return hesap;
    }

    @Override
    public double totalY() {
        double hesap = 0;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT sum(Ucret) as yıllık from musteri where CategoryId=1");  //gelen sonuçlar da resultSet e aktarılıyor.


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                hesap = Double.parseDouble(resultSet.getString("yıllık"));
            }
            return hesap;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        return hesap;
    }


}
