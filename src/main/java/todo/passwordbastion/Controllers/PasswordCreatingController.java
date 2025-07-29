package todo.passwordbastion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import todo.passwordbastion.Cryptography;

import javax.crypto.Cipher;

public class PasswordCreatingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField password;

    @FXML
    private Button submitCreatingPassword;

    @FXML
    void submitPassword(ActionEvent event) {
        String userPassword = password.getText();
        Cryptography cryptography = new Cryptography();
        String encrypt;
        encrypt = Cryptography.encryptAndAddPassword(userPassword);
        System.out.println(encrypt);
        System.out.println(encrypt.equals(userPassword));

        


    }


    @FXML
    void initialize() {
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'PasswordCreate.fxml'.";
        assert submitCreatingPassword != null : "fx:id=\"submitCreatingPassword\" was not injected: check your FXML file 'PasswordCreate.fxml'.";

    }

}
