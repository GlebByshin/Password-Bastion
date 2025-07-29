package todo.passwordbastion.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import todo.passwordbastion.models.Password;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Password, String> passwordCollumn;

    @FXML
    private TableColumn<Password, String> serviceColumn;

    @FXML
    private TableView<Password> table;

    @FXML
    void initialize() {
        ObservableList<Password> passwords = FXCollections.observableArrayList(
                new Password("Zamay86","Spotify"),
                new Password("Slava","Apple"),
                new Password("Rachev","God"),
                new Password("Love","Stacy")
        );
        passwordCollumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("service"));
        table.setItems(passwords);
    }

}
