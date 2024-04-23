module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
//    requires TrayNotification;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires TrayNotification;


    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    opens com.example.javafx.controllers to javafx.fxml;
    exports com.example.javafx.controllers ;

    opens com.example.javafx.entities ;

}