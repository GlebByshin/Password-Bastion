package todo.passwordbastion.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import todo.passwordbastion.Config.Config;
import todo.passwordbastion.Cryptography;
import todo.passwordbastion.database.DAO;
import todo.passwordbastion.models.Password;

@Component
public class MenuController {

    @FXML
    private TableView<Password> table;

    @FXML
    private TableColumn<Password, String> serviceColumn;

    @FXML
    private TableColumn<Password, String> passwordCollumn;

    @FXML
    private Button editPassword;

    @FXML
    private Button deletePassword;

    @FXML
    private Button addPassword;

    @FXML
    private Pane addingPasswordPane;

    @FXML
    private TextField serviceFieldAdd;

    @FXML
    private PasswordField passwordFieldAdd;

    @FXML
    private Button submitAdd;

    @FXML
    private Pane editingPane;

    @FXML
    private TextField serviceFieldEdit;

    @FXML
    private PasswordField passwordFieldEdit;

    @FXML
    private Button submitEdit;

    private ObservableList<Password> passwords;

    @FXML
    void initialize() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        DAO dao = context.getBean("dao", DAO.class);
        passwords = dao.getPasswords();
        for (Password password : passwords) {
            password.setService(Cryptography.decrypt(password.getService()));
            password.setPassword(Cryptography.decrypt(password.getPassword()));
        }
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("service"));
        passwordCollumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        table.setItems(passwords);
    }

    @FXML
    void test(MouseEvent event) {
        Password selectedPassword = table.getSelectionModel().getSelectedItem();
        if (selectedPassword != null) {
            editPassword.setVisible(true);
            deletePassword.setVisible(true);
            System.out.println("Selected Service: " + selectedPassword.getService());
            System.out.println("Selected Password: " + selectedPassword.getPassword());
        } else {
            System.out.println("No row selected.");
        }
    }

    @FXML
    void addPassword() {
        addingPasswordPane.setVisible(true);
    }

    @FXML
    void addPasswordToDb() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        DAO dao = context.getBean("dao", DAO.class);
        dao.addPassword(new Password(passwordFieldAdd.getText(), serviceFieldAdd.getText()));
        addingPasswordPane.setVisible(false);
        passwords.add(new Password(passwordFieldAdd.getText(), serviceFieldAdd.getText()));
    }

    @FXML
    void deletePassword() {
        Password selectedPassword = table.getSelectionModel().getSelectedItem();
        if (selectedPassword != null) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
            DAO dao = context.getBean("dao", DAO.class);
            dao.deletePassword(selectedPassword);
            passwords.remove(selectedPassword);
        } else {
            System.out.println("No row selected.");
        }
    }

    @FXML
    void editPassword() {
        Password selectedPassword = table.getSelectionModel().getSelectedItem();
        if (selectedPassword != null) {
            editingPane.setVisible(true);
            serviceFieldEdit.setText(selectedPassword.getService());
            passwordFieldEdit.setText(selectedPassword.getPassword());
        }
    }

    @FXML
    void submitEdit() {
        Password selectedPassword = table.getSelectionModel().getSelectedItem();
        if (selectedPassword != null) {
            Password newPassword = new Password(serviceFieldEdit.getText(), passwordFieldEdit.getText());
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
            DAO dao = context.getBean("dao", DAO.class);
            dao.editPassword(selectedPassword, newPassword);
            passwords.remove(selectedPassword);
            passwords.add(newPassword);
            editingPane.setVisible(false);
        }
    }
}