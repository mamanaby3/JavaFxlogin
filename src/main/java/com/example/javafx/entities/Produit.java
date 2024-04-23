package com.example.javafx.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "quantite")
    private String quantite;

    @Column(name = "prixUnitaire")
    private int prix;

    @ManyToOne
    @JoinColumn(name = "idCategorie", referencedColumnName = "id")
    private Categorie categorie;

}
