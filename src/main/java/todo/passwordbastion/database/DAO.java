package todo.passwordbastion.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import todo.passwordbastion.Cryptography;
import todo.passwordbastion.Main;
import todo.passwordbastion.models.Password;

import java.util.List;

@Component("dao")
public class DAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Password> taskRowMapper = (rs, _) -> {
        Password password = new Password();
        password.setService(rs.getString("service"));
        password.setPassword(rs.getString("password"));
        return password;
    };



    public void initDatabase() {
        try {
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tasks (" +
                    "service VARCHAR(255) NOT NULL, " +
                    "password VARCHAR(255) NOT NULL " +
                    ")");
            System.out.println("Table 'tasks' created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    public ObservableList<Password> getPasswords() {
        String sql = "SELECT * FROM tasks";
        List<Password> passwords = jdbcTemplate.query(sql, taskRowMapper);
        return FXCollections.observableArrayList(passwords);
    }
    public void addPassword(Password password) {
        String sql = "INSERT INTO tasks (service, password) VALUES (?, ?)";
        String encryptedPassword = Cryptography.encrypt(password.getPassword());
        String encryptedService = Cryptography.encrypt(password.getService());
        jdbcTemplate.update(sql, encryptedService, encryptedPassword);
    }
    public void deletePassword(Password password) {
        String sql = "DELETE FROM tasks WHERE service = ? AND password = ?";
              String encryptedPassword = Cryptography.encrypt(password.getPassword());
        String encryptedService = Cryptography.encrypt(password.getService());
        jdbcTemplate.update(sql, encryptedService, encryptedPassword);
    }
    public void editPassword(Password oldPassword, Password newPassword) {
        String sql = "update tasks set password = ? AND SERVICE = ? where service = ? AND password = ?";
        String encryptedPassword = Cryptography.encrypt(oldPassword.getPassword());
        String encryptedService = Cryptography.encrypt(oldPassword.getService());
        String encryptedPasswordNew = Cryptography.encrypt(newPassword.getPassword());
        String encryptedServiceNew = Cryptography.encrypt(newPassword.getService());
        jdbcTemplate.update(sql, encryptedService, encryptedPassword,encryptedServiceNew,encryptedPasswordNew);
    }


}
