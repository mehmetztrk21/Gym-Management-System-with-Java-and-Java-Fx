package sample;

import java.sql.*;
import java.util.ArrayList;

public class MySqlPersonel implements PersonelDal {
    Connection connection = null;
    DbHelper helper = new DbHelper();  //veritabanı bağlantı kodlarını yazdığımız class.
    Statement statement = null;  //Select işlemi için yani veritabanından bilgi çekmek için.
    PreparedStatement statement2 = null; //insert,update,delete gibi işlemler için.
    ResultSet resultSet; //gelen sonucun tutulması için.

    @Override
    public void create(Personel p) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "insert into personel (Ad,Soyad,Telefon,BaslamaTarihi,Maas,Pozisyon_id) values(?,?,?,?,?,?)"; //burada sql kodunu tanımla ve değikene at.
            statement2 = connection.prepareStatement(sql); //sql kodunun çalışması için
            statement2.setString(1, p.getAd());  //bunlar da koddaki soru işaretleri yerine gelecekler.
            statement2.setString(2, p.getSoyad());
            statement2.setString(3, p.getTelefon());
            statement2.setString(4, p.getTarih());
            statement2.setDouble(5, p.getMaas());
            statement2.setInt(6, p.getPozisyonId());

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
            String sql_delete = "delete from personel where id=?";
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
    public ArrayList<Personel> getAll() {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT p.id,p.Ad,p.Soyad,p.Telefon,p.BaslamaTarihi,p.Maas,po.PozisyonAd FROM personel p inner join pozisyon po on p.Pozisyon_id=po.id order by p.id");  //gelen sonuçlar da resultSet e aktarılıyor.

            ArrayList<Personel> personeller = new ArrayList<Personel>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                personeller.add(new Personel(resultSet.getInt("id"), resultSet.getString("Ad"), resultSet.getString("Soyad"), resultSet.getString("Telefon"), resultSet.getString("BaslamaTarihi"), resultSet.getDouble("Maas"), resultSet.getString("PozisyonAd"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return personeller;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;

    }

    @Override
    public ArrayList<Personel> findPersonel(String name, String surname, int pozisyon) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT p.id,p.Ad,p.Soyad,p.Telefon,p.BaslamaTarihi,p.Maas,po.PozisyonAd FROM personel p inner join pozisyon po on p.Pozisyon_id=po.id where Lcase(p.ad)=Lcase('" + name + "') or Lcase(p.soyad)=Lcase('" + surname + "') or po.id=+" + pozisyon + " order by p.id");  //gelen sonuçlar da resultSet e aktarılıyor.

            ArrayList<Personel> personeller = new ArrayList<Personel>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                personeller.add(new Personel(resultSet.getInt("id"), resultSet.getString("Ad"), resultSet.getString("Soyad"), resultSet.getString("Telefon"), resultSet.getString("BaslamaTarihi"), resultSet.getDouble("Maas"), resultSet.getString("PozisyonAd"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return personeller;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getAll();
    }

    @Override
    public int update(Personel p) {
        int result;
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql_update = "update personel set Ad=? ,Soyad=?, Telefon=? , BaslamaTarihi=? , Maas=?, Pozisyon_id=? where id=?";
            statement2 = connection.prepareStatement(sql_update);
            statement2.setString(1, p.getAd());
            statement2.setString(2, p.getSoyad());
            statement2.setString(3, p.getTelefon());
            statement2.setString(4, p.getTarih());
            statement2.setDouble(5, p.getMaas());
            statement2.setInt(6, p.getPozisyonId());
            statement2.setInt(7, p.getId());
            result = statement2.executeUpdate();  //son iki satır olmasa da olur sadece kontrol amaçlı
            return result;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        return 0;
    }

    @Override
    public String GetByid(int id) {
        String person = " ";
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            statement = connection.createStatement();  //statement böyle oluyor.(select için)

            resultSet = statement.executeQuery("SELECT Ad,Soyad from personel where id=" + id);  //gelen sonuçlar da resultSet e aktarılıyor.

            ArrayList<Personel> personeller = new ArrayList<Personel>();


            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                personeller.add(new Personel(resultSet.getString("Ad"), resultSet.getString("Soyad"))); //select ile çağırdığımız verileri bir arraylist e attık.
            }
            return personeller.get(0).getAd() + " " + personeller.get(0).getSoyad();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return person;
    }


    @Override
    public boolean Login(String user, String pass) {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "Select * from admin where UserName='" + user + "' and Password='" + pass + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int result = 0;

            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                result += 1;
            }
            if (result != 0)
                return true;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public double maas() {
        try {
            connection = helper.getConnection();  //bağlanma.
            System.out.println("Bağlantı oluştu.");
            String sql = "Select sum(Maas) as maas from personel";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            double toplam = 0;

            while (resultSet.next()) { //burda da gelen dataları array liste atıyoruz.
                toplam = Double.parseDouble(resultSet.getString("maas"));
            }
            return toplam;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

}
