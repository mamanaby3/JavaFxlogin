package com.example.javafx.controllers;
   import com.example.javafx.entities.User;
   import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
   import javafx.fxml.FXMLLoader;
   import javafx.scene.Node;
   import javafx.scene.Parent;
   import javafx.scene.Scene;
   import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;
   import javafx.stage.Stage;

   import javax.persistence.EntityManager;
   import javax.persistence.EntityManagerFactory;
   import javax.persistence.EntityTransaction;
   import javax.persistence.Persistence;
   import java.io.IOException;

public class loginController {

    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @FXML
    private Button loginbtn;

    @FXML
    private TextField logintfd;

    @FXML
    private Button registerbtn;
    @FXML

    private TextField mdptfd;


    public User login(String login, String password) {
        User user = null;
        try {
            transaction.begin();
            user = entityManager.createNamedQuery("Connexion", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    @FXML
    void loginFunction(ActionEvent event) {
        String login = logintfd.getText();
        String password = mdptfd.getText();
        if (login.trim().equals("") || password.trim().equals("")) {
            com.example.javafx.tools.Notification.NotifError("Erreur !", "Tous les champs sont obligatoires !");
        } else {
            User user = login(login, password);
            if (user != null) {
                try {
                    com.example.javafx.tools.Notification.NotifSuccess("Succès !", "Connexion réussie !");
                    com.example.javafx.tools.Outils.load(event, "Bienvenue", "/page/first.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                com.example.javafx.tools.Notification.NotifError("Erreur !", "Login et/ou mot de passe incorrects !");
            }
        }
    }

    @FXML
    void registerFunction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/page/inscription.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
