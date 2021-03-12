package sample;

import java.util.ArrayList;

public interface MusteriDal {
    void create(Musteri m);

    int delete(int id);

    ArrayList<Musteri> getAll();

    String GetByid(int id);

    ArrayList<Musteri> findMusteri(String name, String surname, int kayıt);

    int update(Musteri p);

    double totalA();

    double totalY();
}
