# TP 1
## 1. Configuration Maven

Le fichier pom.xml contient les dépendances nécessaires au fonctionnement de JPA, Hibernate et H2.

Les principales dépendances utilisées sont :

- javax.persistence-api
- hibernate-core
- h2
- slf4j-api
- slf4j-simple

La version de Java utilisée pour la compilation est configurée dans le fichier Maven.

## 2. Création de l'entité Produit

Une classe Produit a été créée dans le package :

com.example.model

Elle représente une table dans la base de données grâce à l'annotation :

@Entity

L'identifiant est généré automatiquement avec :

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

Hibernate utilise ainsi cette classe pour générer et gérer la table PRODUIT.

## 3. Insertion des données

Dans la classe App, trois produits sont créés :

Laptop      999.99
Smartphone  499.99
Tablette    299.99

Ils sont ensuite enregistrés dans la base avec :

em.persist(p1);
em.persist(p2);
em.persist(p3);

La transaction est validée avec :

em.getTransaction().commit();

Un message confirme ensuite que l'insertion a été effectuée avec succès.

## 4. Lecture des données avec JPQL

Les produits enregistrés sont récupérés avec une requête JPQL :

List<Produit> produits =
    em.createQuery("SELECT p FROM Produit p", Produit.class)
      .getResultList();

L'application affiche ensuite la liste des produits dans la console.

Une recherche par identifiant est également réalisée avec :


Produit produit = em.find(Produit.class, 2L);

Cette instruction permet de récupérer le produit dont l'identifiant est `2`.


## 5. Résultat obtenu

Après l'exécution du programme, Hibernate crée la table `PRODUIT` et enregistre les trois produits.

Le résultat attendu dans la console H2 est similaire à :

PS C:\Users\boudi\IdeaProjects\TP 1> mvn clean compile exec:java "-Dexec.mainClass=com.example.App"      
[INFO] Scanning for projects...
Produits insérés avec succès !
Hibernate: 
    select
        produit0_.id as id1_0_,
        produit0_.nom as nom2_0_,
        produit0_.prix as prix3_0_ 
    from
        Produit produit0_

Liste des produits :
Produit{id=1, nom='Laptop', prix=999.99}
Produit{id=2, nom='Smartphone', prix=499.99}
Produit{id=3, nom='Tablette', prix=299.99}

Recherche du produit avec ID=2 :
Produit{id=2, nom='Smartphone', prix=499.99}
oct. 08, 2026 9:07:49 P.M. org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.882 s
[INFO] Finished at: 2026-10-08T21:07:49+01:00
[INFO] ------------------------------------------------------------------------



## 6. Exécution du projet

Le projet peut être exécuté directement depuis l'IDE en lançant la classe :

com.example.App

Il est également possible d'utiliser Maven :

mvn clean compile exec:java -Dexec.mainClass="com.example.App"


Lors de l'exécution, plusieurs informations sont affichées :

- les requêtes SQL générées par Hibernate ;
- la confirmation de l'insertion des produits ;
- la liste des produits récupérés ;
- le résultat de la recherche du produit ayant l'ID `2`.



