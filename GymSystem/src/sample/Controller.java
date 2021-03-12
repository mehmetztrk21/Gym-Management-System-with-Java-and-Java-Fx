package sample;

import com.sun.prism.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller implements Initializable {

    //login


    @FXML
    public Button Pbtn;

    @FXML
    public TextField araName;
    @FXML
    public TextField araSurname;


    @FXML
    public TableView<Personel> Ptable;

    @FXML
    private TableColumn<Personel, Integer> Pid;
    @FXML
    private TableColumn<Personel, String> Pad;
    @FXML
    private TableColumn<Personel, String> Psoyad;
    @FXML
    private TableColumn<Personel, String> Ptarih;
    @FXML
    private TableColumn<Personel, Double> Pmaas;
    @FXML
    private TableColumn<Personel, String> Ptel;
    @FXML
    private TableColumn<Personel, String> Ppozisyon;


    //Personel Kayıt
    @FXML
    private ComboBox comboPozisyon;
    @FXML
    private TextField pkad;
    @FXML
    private TextField pksoyad;
    @FXML
    private TextField pktel;
    @FXML
    private TextField pkmaas;
    @FXML
    private DatePicker pktarih;
    @FXML
    private Button pkaydet;

    @FXML
    private ComboBox ppozisyon;


    //Personel Silme
    @FXML
    private TextField silp;

    @FXML
    private Label siladp;

    @FXML
    private Label silsoyadp;
    @FXML
    public Button kontrolp;

    @FXML
    public Button silpbtn;

    //Personel Güncelleme
    @FXML
    private ComboBox pgpozisyon;
    @FXML
    private TextField pgad;
    @FXML
    private TextField pgsoyad;
    @FXML
    private TextField pgtel;
    @FXML
    private TextField pgmaas;
    @FXML
    private DatePicker pgtarih;
    @FXML
    private TextField pgid;
    @FXML
    private Button pgbtn;


    //Müşteri

    @FXML
    public TableView<Musteri> tableM;

    @FXML
    private TableColumn<Personel, Integer> tableid;
    @FXML
    private TableColumn<Personel, String> tablead;
    @FXML
    private TableColumn<Personel, String> tablesoyad;
    @FXML
    private TableColumn<Personel, String> tabletel;
    @FXML
    private TableColumn<Personel, String> tabletarih;
    @FXML
    private TableColumn<Personel, String> tabletalep;
    @FXML
    private TableColumn<Personel, String> tablekayıt;
    @FXML
    private TableColumn<Personel, Double> tableucret;

    @FXML
    private TextField mad;

    @FXML
    private TextField msoyad;
    @FXML
    private ComboBox makayıt;

    @FXML
    private Button tablebtn;

    //Müşteri sil

    @FXML
    private Button silmbtn;
    @FXML
    private Button kontrolm;

    @FXML
    private TextField silm;

    @FXML
    private Label siladm;

    @FXML
    private Label silsoyadm;


    //Müşteri Kayıt

    @FXML
    private TextField mkad;
    @FXML
    private TextField mksoyad;
    @FXML
    private TextField mktel;
    @FXML
    private TextField mktalep;
    @FXML
    private TextField mkucret;
    @FXML
    private ComboBox mkkayıt;
    @FXML
    private Button mkkaydet;
    @FXML
    private DatePicker mktarih;


    //Müşteri Güncelleme

    @FXML
    private TextField mgad;
    @FXML
    private TextField mgsoyad;
    @FXML
    private TextField mgtel;
    @FXML
    private TextField mgtalep;
    @FXML
    private TextField mgucret;
    @FXML
    private ComboBox mgkayıt;
    @FXML
    private Button mgbtn;
    @FXML
    private DatePicker mgtarih;
    @FXML
    private TextField mgid;

//gelir-gider

    @FXML
    private TextField elektrik;

    @FXML
    private TextField gaz;

    @FXML
    private TextField su;

    @FXML
    private TextField kira;

    @FXML
    private TextField diger;

    @FXML
    private TextField maas;

    @FXML
    private Button hesapla;

    @FXML
    private TextArea yaz;

    @FXML
    private PieChart grafik;

    @FXML
    private Label tarih;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime x = LocalDateTime.now();
        String now = dtf.format(x).toString();
        tarih.setStyle("-fx-background-radius: 250px; -fx-background-color:black;");
        tarih.setText(now.split(" ")[0]);
        tarih.setTextAlignment(TextAlignment.CENTER);
        //hesapla

        Double maasf = total();
        maas.setText(Double.toString(maasf));
        hesapla.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                try {

                    Double maasf = total();
                    Double suf = Double.parseDouble(su.getText());
                    Double gazf = Double.parseDouble(gaz.getText());
                    Double elektrikf = Double.parseDouble(elektrik.getText());
                    Double digerf = Double.parseDouble(diger.getText());
                    Double kiraf = Double.parseDouble(kira.getText());
                    Double toplam = maasf + suf + gazf + elektrikf + digerf + kiraf;
                    double[] musteriu = Arrays.copyOf(totalM(), 2);
                    yaz.setText("Aylık Müşterilerden Geliriniz: " + musteriu[0] + "\n\nYıllık Müşterilerden Geliriniz: " + musteriu[1] + "\n\nBu Ayki Toplam Gideriniz: " + toplam);

                    ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                            new PieChart.Data("Elektrik", elektrikf),
                            new PieChart.Data("Su", suf),
                            new PieChart.Data("Doğalgaz", gazf),
                            new PieChart.Data("Diğer Giderler", digerf),
                            new PieChart.Data("Kira", kiraf),
                            new PieChart.Data("Personel Maaşları", maasf)
                    );
                    grafik.setData(data);

                } catch (Exception ex) {
                    GirisHatası();
                }

            }
        });

        //login


        //Personel


        Pid.setCellValueFactory(new PropertyValueFactory<Personel, Integer>("id"));
        Pad.setCellValueFactory(new PropertyValueFactory<Personel, String>("ad"));

        Psoyad.setCellValueFactory(new PropertyValueFactory<Personel, String>("soyad"));
        Ptel.setCellValueFactory(new PropertyValueFactory<Personel, String>("telefon"));
        Ptarih.setCellValueFactory(new PropertyValueFactory<Personel, String>("tarih"));
        Pmaas.setCellValueFactory(new PropertyValueFactory<Personel, Double>("maas"));
        Ppozisyon.setCellValueFactory(new PropertyValueFactory<Personel, String>("Pozisyon"));


        // Ptable.getColumns().addAll(Pid,Pad, Psoyad,Ptel,Ptarih,Pmaas,Ppozisyon);
        Ptable.setItems(getPersonel());


        Pbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = araName.getText();
                String surname = araSurname.getText();
                int pozisyon = ppozisyon.getSelectionModel().getSelectedIndex() + 1;

                Ptable.setItems(findPersonel(name, surname, pozisyon));
                ppozisyon.getSelectionModel().select("");
                ppozisyon.setPromptText("Pozisyon");

            }
        });

        //personel silme

        silpbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                siladp.setStyle("-fx-background-color:none;");
                silsoyadp.setStyle("-fx-background-color:none;");
                siladp.setText("");
                silsoyadp.setText("");
                int id;
                try {
                    id = Integer.parseInt(silp.getText());
                    MySqlPersonel p = new MySqlPersonel();
                    int result = p.delete(id);
                    if (result != 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Başarılı");
                        alert.setHeaderText("Başarılı");
                        alert.setContentText(id + " numaralı personel veritabanından silindi.");
                        alert.showAndWait();
                        silp.setText("");
                        Ptable.setItems(getPersonel());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText("Kayıt hatası!");
                        alert.setContentText("Veritabanında girilen id numarasına sahip personel bulunamadı.");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Kayıt hatası!");
                    alert.setContentText("Veritabanında girilen id numarasına sahip personel bulunamadı.");
                    alert.showAndWait();
                }
            }
        });
        //kontrol
        kontrolp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                siladp.setStyle("-fx-background-color:none;");
                silsoyadp.setStyle("-fx-background-color:none;");
                siladp.setText("");
                silsoyadp.setText("");

                int id;
                try {
                    String personel;
                    id = Integer.parseInt(silp.getText());
                    MySqlPersonel p = new MySqlPersonel();
                    personel = p.GetByid(id);
                    if (personel == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText("Kayıt hatası!");
                        alert.setContentText("Veritabanında girilen id numarasına sahip personel bulunamadı.");
                        alert.showAndWait();
                    } else {
                        siladp.setText(personel.split(" ")[0]);
                        silsoyadp.setText(personel.split(" ")[1]);
                        siladp.setStyle("-fx-background-color:white;");
                        silsoyadp.setStyle("-fx-background-color:white;");

                    }
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Kayıt hatası!");
                    alert.setContentText("Veritabanında girilen id numarasına sahip personel bulunamadı.");
                    alert.showAndWait();

                }

            }
        });

        //Personel Kayıt
        ObservableList<String> pozisyon_list = FXCollections.observableArrayList("Eğitmen", "Uzman Eğitmen", "Reklam", "Yönetim", "Temizlik");
        comboPozisyon.setItems(pozisyon_list);
        pgpozisyon.setItems(pozisyon_list);
        ppozisyon.setItems(pozisyon_list);
        pkaydet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String ad = pkad.getText();
                    String soyad = pksoyad.getText();
                    String tarih = pktarih.getValue().toString();
                    Double maas = Double.parseDouble(pkmaas.getText());
                    String telefon = pktel.getText();
                    int pozisyon = comboPozisyon.getSelectionModel().getSelectedIndex() + 1;
                    MySqlPersonel p = new MySqlPersonel();
                    p.create(new Personel(ad, soyad, telefon, tarih, maas, pozisyon));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Başarılı");
                    alert.setHeaderText("Başarılı");
                    alert.setContentText(ad + " adlı personel sisteme kaydedildi.");
                    alert.showAndWait();
                    Ptable.setItems(getPersonel());
                    pkad.setText("");
                    pksoyad.setText("");
                    pktel.setText("");
                    pkmaas.setText("");
                } catch (Exception ex) {
                    GirisHatası();
                }

            }
        });

        //Personel Güncelleme
        pgbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(pgid.getText());
                    String ad = pgad.getText();
                    String soyad = pgsoyad.getText();
                    String tarih = pgtarih.getValue().toString();
                    Double maas = Double.parseDouble(pgmaas.getText());
                    String telefon = pgtel.getText();
                    int pozisyon = pgpozisyon.getSelectionModel().getSelectedIndex() + 1;
                    MySqlPersonel p = new MySqlPersonel();
                    Personel personel = new Personel(id, ad, soyad, telefon, tarih, maas, pozisyon);
                    int result = p.update(personel);
                    if (result == 0) {
                        GirisHatası();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Başarılı");
                        alert.setHeaderText("Başarılı");
                        alert.setContentText(id + " numaralı personelin bilgileri güncellendi.");
                        alert.showAndWait();
                        Ptable.setItems(getPersonel());
                        pgad.setText("");
                        pgsoyad.setText("");
                        pgtel.setText("");
                        pgmaas.setText("");
                        pgid.setText("");
                    }
                } catch (Exception ex) {
                    GirisHatası();
                }
            }
        });

        //MÜŞTERİ

        tableid.setCellValueFactory(new PropertyValueFactory<Personel, Integer>("id"));
        tablead.setCellValueFactory(new PropertyValueFactory<Personel, String>("ad"));
        tablesoyad.setCellValueFactory(new PropertyValueFactory<Personel, String>("soyad"));
        tabletel.setCellValueFactory(new PropertyValueFactory<Personel, String>("telefon"));
        tabletarih.setCellValueFactory(new PropertyValueFactory<Personel, String>("tarih"));
        tabletalep.setCellValueFactory(new PropertyValueFactory<Personel, String>("talep"));
        tablekayıt.setCellValueFactory(new PropertyValueFactory<Personel, String>("category"));
        tableucret.setCellValueFactory(new PropertyValueFactory<Personel, Double>("ucret"));

        tableM.setItems(getMusteri());
        ObservableList<String> kayıt = FXCollections.observableArrayList("Yıllık", "Aylık", "Deneme");
        makayıt.setItems(kayıt);
        mkkayıt.setItems(kayıt);
        mgkayıt.setItems(kayıt);

        //arama
        tablebtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = mad.getText();
                String surname = msoyad.getText();
                int kayıt = makayıt.getSelectionModel().getSelectedIndex() + 1;

                tableM.setItems(findMusteri(name, surname, kayıt));
                makayıt.getSelectionModel().select("");
                makayıt.setPromptText("Kayıt Tipi");
            }
        });

        //müşteri sil
        silmbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                siladm.setStyle("-fx-background-color:none;");
                silsoyadm.setStyle("-fx-background-color:none;");
                siladm.setText("");
                silsoyadm.setText("");
                int id;
                try {
                    id = Integer.parseInt(silm.getText());
                    MySqlMusteri m = new MySqlMusteri();
                    int result = m.delete(id);
                    if (result != 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Başarılı");
                        alert.setHeaderText("Başarılı");
                        alert.setContentText(id + " numaralı müşteri veritabanından silindi.");
                        alert.showAndWait();
                        silm.setText("");
                        tableM.setItems(getMusteri());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText("Kayıt hatası!");
                        alert.setContentText("Veritabanında girilen id numarasına sahip müşteri bulunamadı.");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Kayıt hatası!");
                    alert.setContentText("Veritabanında girilen id numarasına sahip müşteri bulunamadı.");
                    alert.showAndWait();
                }
            }
        });
        //kontrol
        kontrolm.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                siladm.setStyle("-fx-background-color:none;");
                silsoyadm.setStyle("-fx-background-color:none;");
                siladm.setText("");
                silsoyadm.setText("");

                int id;
                try {
                    String musteri;
                    id = Integer.parseInt(silm.getText());
                    MySqlMusteri m = new MySqlMusteri();
                    musteri = m.GetByid(id);
                    if (musteri == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText("Kayıt hatası!");
                        alert.setContentText("Veritabanında girilen id numarasına sahip müşteri bulunamadı.");
                        alert.showAndWait();
                    } else {
                        siladm.setText(musteri.split(" ")[0]);
                        silsoyadm.setText(musteri.split(" ")[1]);
                        siladm.setStyle("-fx-background-color:white;");
                        silsoyadm.setStyle("-fx-background-color:white;");

                    }
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Kayıt hatası!");
                    alert.setContentText("Veritabanında girilen id numarasına sahip müşteri bulunamadı.");
                    alert.showAndWait();

                }

            }
        });

        //Müşteri Kayıt
        mkkaydet.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    String ad = mkad.getText();
                    String soyad = mksoyad.getText();
                    String telefon = mktel.getText();
                    String talep = mktalep.getText();
                    Double ucret = Double.parseDouble(mkucret.getText());
                    int kayıt = mkkayıt.getSelectionModel().getSelectedIndex() + 1;
                    String tarih = mktarih.getValue().toString();

                    MySqlMusteri m = new MySqlMusteri();
                    m.create(new Musteri(ad, soyad, telefon, talep, tarih, ucret, kayıt));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Başarılı");
                    alert.setHeaderText("Başarılı");
                    alert.setContentText(ad + " adlı müşteri sisteme kaydedildi.");
                    alert.showAndWait();
                    tableM.setItems(getMusteri());
                    mkad.setText("");
                    mksoyad.setText("");
                    mktel.setText("");
                    mktalep.setText("");
                    mkucret.setText("");
                } catch (Exception ex) {
                    GirisHatası();
                }


            }
        });


        //Müşteri Güncelleme

        mgbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(mgid.getText());
                    String ad = mgad.getText();
                    String soyad = mgsoyad.getText();
                    String telefon = mgtel.getText();
                    String talep = mgtalep.getText();
                    Double ucret = Double.parseDouble(mgucret.getText());
                    int kayıt = mgkayıt.getSelectionModel().getSelectedIndex() + 1;
                    String tarih = mgtarih.getValue().toString();

                    MySqlMusteri m = new MySqlMusteri();
                    int result = m.update(new Musteri(id, ad, soyad, telefon, talep, tarih, ucret, kayıt));
                    if (result == 0) {
                        GirisHatası();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Başarılı");
                        alert.setHeaderText("Başarılı");
                        alert.setContentText(ad + " adlı müşterinin bilgileri güncellendi.");
                        alert.showAndWait();
                        tableM.setItems(getMusteri());
                        mgad.setText("");
                        mgsoyad.setText("");
                        mgtel.setText("");
                        mgtalep.setText("");
                        mgucret.setText("");
                        mgid.setText("");
                    }
                } catch (Exception ex) {
                    GirisHatası();
                }

            }
        });


    }

    public void GirisHatası() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hata");
        alert.setHeaderText("Kayıt hatası!");
        alert.setContentText("Lütfen bilgileri kontrol edip tekrar deneyiniz.");
        alert.showAndWait();
    }

    public ObservableList<Personel> getPersonel() {
        ObservableList<Personel> personels = FXCollections.observableArrayList();
        MySqlPersonel p = new MySqlPersonel();
        ArrayList<Personel> personeller = p.getAll();
        for (int i = 0; i < personeller.size(); i++) {
            personels.add((personeller.get(i)));
        }
        return personels;
    }

    public ObservableList<Musteri> getMusteri() {
        ObservableList<Musteri> musteriler = FXCollections.observableArrayList();
        MySqlMusteri m = new MySqlMusteri();
        ArrayList<Musteri> musteris = m.getAll();
        for (int i = 0; i < musteris.size(); i++) {
            musteriler.add((musteris.get(i)));
        }
        return musteriler;
    }

    public ObservableList<Personel> findPersonel(String name, String surname, int pozisyon) {

        ObservableList<Personel> personels = FXCollections.observableArrayList();
        MySqlPersonel p = new MySqlPersonel();
        ArrayList<Personel> personeller = p.findPersonel(name, surname, pozisyon);
        for (int i = 0; i < personeller.size(); i++) {
            personels.add((personeller.get(i)));
        }
        if (personels.size() == 0) {
            return getPersonel();
        }
        return personels;
    }

    public ObservableList<Musteri> findMusteri(String name, String surname, int kayıt) {
        ObservableList<Musteri> musteris = FXCollections.observableArrayList();
        MySqlMusteri m = new MySqlMusteri();
        ArrayList<Musteri> musteriler = m.findMusteri(name, surname, kayıt);
        for (int i = 0; i < musteriler.size(); i++) {
            musteris.add((musteriler.get(i)));
        }
        if (musteris.size() == 0) {
            return getMusteri();
        }
        return musteris;
    }

    public double total() {
        MySqlPersonel p = new MySqlPersonel();
        return p.maas();
    }

    public double[] totalM() {
        MySqlMusteri m = new MySqlMusteri();
        double[] total = new double[2];
        total[0] = m.totalA();
        total[1] = m.totalY();
        return total;
    }
}
