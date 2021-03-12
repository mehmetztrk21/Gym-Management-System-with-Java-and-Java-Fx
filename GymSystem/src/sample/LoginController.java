package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Button login;

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String user = username.getText();
                String pass = password.getText();
                MySqlPersonel p = new MySqlPersonel();
                if (p.Login(user, pass)) {

                    try {
                        Main.root = FXMLLoader.load(getClass().getResource("sample.fxml"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.stage.setScene(new Scene(Main.root));
                    Main.stage.show();
                } else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime x = LocalDateTime.now();
                    String now = dtf.format(x).toString();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText("Hatalı Giriş!");
                    alert.setContentText("Kullanıcı adı veya parola yanlış.");
                    alert.showAndWait();
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\Desktop\\Projeler\\GymSystem\\GymSystem\\HataliGirisler.txt", true)); //bunu yazınca try-cath i kendisi yapıyor yine. hataya bas yeter.
                        writer.newLine();  //yeni bir satır oluştur sonra yaz demek. Yani yan yana değil de alt alta yaz demek.
                        writer.write("\nKullacının Adı: " + user + "\nParola: " + pass + "\nTarih: " + now.toString());
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
