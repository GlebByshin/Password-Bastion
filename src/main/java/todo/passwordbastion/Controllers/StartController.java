package todo.passwordbastion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import todo.passwordbastion.Cryptography;
import todo.passwordbastion.Main;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signinButton;

    @FXML
    void creatingPassword(ActionEvent event) throws IOException {
        Stage stage = (Stage) signinButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLs/PasswordCreate.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        scene.getStylesheets().add("src/main/resources/todo/passwordbastion/styles.css");
        stage.show();
    }
    @FXML
    void signIn(ActionEvent event) throws IOException {
        String userPassword = passwordField.getText();
        Cryptography cryptography = new Cryptography();
        String encrypt = cryptography.encrypt(userPassword);
        String correctPassword = Files.readString(Paths.get("src/main/resources/todo/passwordbastion/data/encrypted.txt"));
        System.out.println("Correct" + correctPassword);
        System.out.println("Encrypted"+encrypt);
        System.out.println(correctPassword.equals(encrypt));
        Boolean isCorrect = correctPassword.equals(encrypt);
        if (isCorrect) {
            System.out.println("You are now logged in");
            Stage stage = (Stage) signinButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLs/Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void initialize() {
        assert signinButton != null : "fx:id=\"signinButton\" was not injected: check your FXML file 'start.fxml'.";

    }

}
