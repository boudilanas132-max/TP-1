```java
package com.example;

import com.example.model.Produit;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(String[] args) {
        lancerConsoleH2();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("hibernate-demo");

        try {
            ajouterProduits(factory);
            afficherProduits(factory);
        } finally {
            factory.close();
        }
    }

    // Démarre l'interface web de H2
    private static void lancerConsoleH2() {
        try {
            Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("Console H2 : http://localhost:8082");
        } catch (Exception exception) {
            System.err.println("Impossible de lancer la console H2.");
            exception.printStackTrace();
        }
    }

    // Enregistre plusieurs produits dans la base de données
    private static void ajouterProduits(EntityManagerFactory factory) {
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            manager.persist(new Produit("Ordinateur portable",
                    new BigDecimal("999.99")));
            manager.persist(new Produit("Telephone",
                    new BigDecimal("499.99")));
            manager.persist(new Produit("Tablette tactile",
                    new BigDecimal("299.99")));

            manager.getTransaction().commit();
            System.out.println("Enregistrement des produits terminé.");

        } catch (Exception exception) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            exception.printStackTrace();
        } finally {
            manager.close();
        }
    }

    // Affiche les produits et recherche celui dont l'identifiant vaut 2
    private static void afficherProduits(EntityManagerFactory factory) {
        EntityManager manager = factory.createEntityManager();

        try {
            List<Produit> resultat = manager
                    .createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList();

            System.out.println("\n--- Produits disponibles ---");
            resultat.forEach(System.out::println);

            Long identifiantRecherche = 2L;
            System.out.println("\n--- Recherche du produit n°"
                    + identifiantRecherche + " ---");

            Produit trouve = manager.find(
                    Produit.class, identifiantRecherche);

            if (trouve == null) {
                System.out.println("Aucun produit correspondant.");
            } else {
                System.out.println("Produit trouvé : " + trouve);
            }

        } finally {
            manager.close();
        }
    }
}
```
