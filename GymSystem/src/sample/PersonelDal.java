package sample;

import java.util.ArrayList;

public interface PersonelDal {
    void create(Personel p);

    int delete(int id);

    ArrayList<Personel> getAll();

    String GetByid(int id);

    ArrayList<Personel> findPersonel(String name, String surname, int pozisyon);

    int update(Personel p);

    boolean Login(String user, String pass);

    double maas();
}
