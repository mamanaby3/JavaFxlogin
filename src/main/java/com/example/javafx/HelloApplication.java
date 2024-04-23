package com.example.javafx;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

         //Charger le fichier FXML et afficher la scène
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/page/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void init() {
        // Initialize JPA EntityManagerFactory and perform database operations
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        // Perform database operations here
        transaction.commit();
        // Close EntityManager and EntityManagerFactory when done
        entityManager.close();
        managerFactory.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
