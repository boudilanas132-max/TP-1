
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private BigDecimal prix;

    // Constructeur sans parametres utilise par JPA
    public Produit() {
    }

    // Initialise un produit avec son nom et son prix
    public Produit(String nom, BigDecimal prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long nouvelId) {
        this.id = nouvelId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal nouveauPrix) {
        this.prix = nouveauPrix;
    }

    @Override
    public String toString() {
        return "Produit [id=" + id
                + ", nom=" + nom
                + ", prix=" + prix + "]";
    }
}
