module todo.passwordbastion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires spring.jdbc;
    requires spring.context;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    exports todo.passwordbastion;
    exports todo.passwordbastion.Controllers;
    opens todo.passwordbastion.Controllers to javafx.fxml;

    opens todo.passwordbastion.models to javafx.base; // 💥 Это критично
    requires spring.beans;
    requires spring.core;

    // JavaFX

    requires javafx.graphics;
    requires javafx.base;

    requires org.slf4j;

    exports todo.passwordbastion.Config;
    exports todo.passwordbastion.database;

    opens todo.passwordbastion.Config to spring.core;
    opens todo.passwordbastion.database to spring.core;
    opens todo.passwordbastion to javafx.fxml, spring.core;

}
