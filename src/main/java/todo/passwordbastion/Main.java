package todo.passwordbastion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import todo.passwordbastion.Config.Config;
import todo.passwordbastion.database.DAO;

import java.io.IOException;
@ComponentScan
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXMLs/start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        scene.getStylesheets().add("src/main/resources/todo/passwordbastion/styles.css");
        stage.show();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        // Получаем DAO как бин
        DAO dao = context.getBean("dao",DAO.class);
        dao.initDatabase();
        launch();
    }
}