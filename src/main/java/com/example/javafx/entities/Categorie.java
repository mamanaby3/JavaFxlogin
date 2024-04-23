package com.example.javafx.entities;

import javax.persistence.*;


@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "libelle")
    private String libelle;
}
