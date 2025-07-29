package todo.passwordbastion.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("dao")
public class DAO {

    private final JdbcTemplate jdbcTemplate;

    public DAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initDatabase() {
        try {
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tasks (" +
                    "task_id INTEGER PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "description TEXT, " +
                    "done BOOLEAN DEFAULT FALSE, " +
                    "owner_id INT NOT NULL" +
                    ")");
            System.out.println("Table 'tasks' created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
