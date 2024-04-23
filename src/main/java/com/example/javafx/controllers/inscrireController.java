package com.example.javafx.controllers;



import com.example.javafx.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class inscrireController {

    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @FXML
    private TextField logintfd;

    @FXML
    private TextField mdptfd;

    @FXML
    private TextField nomtfd;

    @FXML
    private TextField prenomtfd;

    public void register(String nom, String prenom,String login, String password) {
        try {
            transaction.begin();
            User user = new User();
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setLogin(login);
            user.setMdp(password);
            user.setRole("admin");

            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @FXML
    void inscrireFunction(ActionEvent event) {
        String nom = nomtfd.getText();
        String prenom = prenomtfd.getText();
        String login = logintfd.getText();
        String password = mdptfd.getText();
        if (login.trim().equals("") || password.trim().equals("")) {
            com.example.javafx.tools.Notification.NotifError("Erreur !", "Tous les champs sont obligatoires !");
        } else {
            try {
            register(nom,prenom,login, password);

            com.example.javafx.tools.Notification.NotifSuccess("Succès !", "Inscription réussie !");
                com.example.javafx.tools.Outils.load(event, "Bienvenue", "/page/login.fxml");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}


