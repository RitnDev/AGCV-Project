# Projet Java : AGCV

Voici un lien qui vous permet d'avoir un aperçu visuel du résultat (lors de la version 1.0.0) :
https://imgur.com/a/jwIRFjp

## 1. Présentation

Le projet Java AGCV (**A**pplication web de **G**estion de la **C**onsommation de **V**olants) a pour but de remplir la même tâche que le fichier fournit en incorporant une base de données pour faciliter la mémorisation/persistance des données.
Cela permettra également de proposer un historique.

La donnée entrante fournit est un fichier Excel « VolantV3Conso.xlsm ».

Ce projet doit incorporer également des nouveautés tel que :
-	Un moyen simple d’enregistrer les commandes de tubes (volants) pour la consommation joueur.
-	Un résumé sur une saison (ou +) de leur consommation.

## 2. Application Web

L’utilisateur naviguera à travers des pages web pour lire/écrire les données enregistrée en base. 
Ces dites pages sont écrites en HTML5 avec du CSS et parfois du Javascript.
Dans ce chapitre, je vais établir un plan de l’application.

### 2.1 Présentation des diférentes pages de l'applications web :

Sur toutes les pages de l’application un menu sera disponible afin d’accéder aux autres pages principales.
L’application sera constituée de ces différentes pages :

*	Page de consommation de volant sur la saison en cours. (Page d’accueil)
    *	Accès à une page d’historique des saisons précédentes.
    *	Gestion des compétitions.
    *	Gestion des commandes de tubes pour les joueurs.
*	Page administrateur (connexion requise pour y accéder).
    *	Gestion des membres.
    *	Gestion des saisons de badminton.
    *	Accès à 
*	Page Super-administrateur (connexion spécifique requise pour y accéder).
    * Gestion des utilisateurs.
    *	Gestion des type de volants.


## 2.2 Page de consommation de volants sur la saison en cours

Cette page est la page d’accueil de l’application.

Il y aura un menu permettant d’accéder aux autres pages, tel que :
- L'accès aux historiques des saisons précédentes.
- La page de gestion des compétitions.
-	La page des commandes de tubes pour les joueurs.
-	La page administrateur.

Les actions possibles sur cette page :
-  Changer les tubes utilisés en cours de saison (devront être créée au préalable)
-  Ajouter des tubes commandés par le club par mois et par type de volant.
-  Ajouter des tubes utilisés par les membres par mois et par type de volant.

On retrouvera sur cette page un résumé de la consommation de volants, c'est à dire : 
- Le résumé global sur une saison (12 mois) de la consomation et le coût.
- Le stock restant par type de volant.
-	Le rappel du budget prévisionnelle de la saison.
-	Le coût total des commandes.
-	Le nombre de tubes commandés.
-	Le nombre de tubes consommés.
-	Le coût des tubes consommés.
-	Le budget restant.

### 2.2.1 Page d'historique sur les saisons précédentes
Sur cette page on peut y trouver les mêmes informations décrites dans le chapitre précédent (2.2. Page de consommation de volant sur la saison en cours) mais sur les saisons précédentes.

Un moyen de choisir la saison en question sera mis en place sur cette page.
Aucune action modifiant les données sera proposée sur cette page.
Par défaut, la saison précédente à celle en cours sera affichée.

### 2.2.2 Page de gestion des compétitions
Sur cette page on y retrouvera le nombre de tube dans le stock de compétition (stock alloué pour une compétition).
Il sera possible de définir le nombre de tube qu’on ajoute dans ce stock, à partir d’un formulaire à remplir. 
![image](https://user-images.githubusercontent.com/69145464/134897512-d1af3df1-e1a1-4c6c-bf45-d1446c813b24.png)

Lors d’un ajustement du stock cela prend dans le nombre de tubes utilisés du mois en cours de la saison pour les volants de compétition.
Un second formulaire pour créer une compétition qui enlèvera la quantité de tube au stock alloué.
![image](https://user-images.githubusercontent.com/69145464/134907134-619d5692-e9d2-4811-8874-436ea62759a9.png)

Un résumé permettra de voir le nombre de tube utilisé par compétition et sur une saison.

### 2.2.3 Page des commandes tubes des membres
Cette page permet de visualiser les commandes effectuées par les membres. 
On peut y retrouver :
-	Si la commande a été réglée ou non. 
-	Le nombre de tubes commandés.
-	Le montant total des commandes (part du club).
-	Le montant total des commandes (part des membres).
-	Le montant non réglé.
-	Le montant déduit pour le club.

![image](https://user-images.githubusercontent.com/69145464/134908023-d5e00f33-be04-4218-937f-c2d7ad86c440.png)

Ces informations seront déduites du stock saison des tubes de compétition, dans le nombre de tubes utilisés du mois en cours de la saison pour les volants de compétition.
Il est possible sur une commande sont de changer le statut. C’est-à-dire dire si elle est réglée.

Un formulaire pour créer une nouvelle commande est disponible sur cette page :
![image](https://user-images.githubusercontent.com/69145464/134908257-9846cde2-e7e3-442a-bcb7-07713b16098e.png)
![image](https://user-images.githubusercontent.com/69145464/134908292-80af9207-f88c-41e3-87ee-c3e5703ddf0a.png)

Les commandes concernent uniquement les tubes de volant de compétition.

## 2.3 Page administrateur
La page administrateur nécessite un compte "admin" pour s’y rendre.
Il sera possible de : 
- Revenir à la page principale.
- Se déconnecter (retour sur la page index).
- Accéder à la page de gestion des saisons.
- Accéder à la page de gestion des membres.
- Accéder à la page de gestion des prix tubes.

On retrouvera sur cette page les différentes options et paramètres de l’application pouvant être modifiable.
Notamment : 
-	Initialiser le stock de tube pour chaque type de volant pour la saison en cours. 
-	Paramétrer le budget par défaut lors de la création d'une saison. (le budget peut-être modifié à la création de la saison ou après)
-	Nombre de tubes pour déclencher une alerte (pour faire un commande club) pour les 3 types de tubes distinctement.

### 2.3.1 Page Saisons :
Page de listing de toutes les saisons de l'application.
Quelques modifications possible sur les données de la saison.

Formulaire de création d'une nouvelle saison :

![image](https://user-images.githubusercontent.com/69145464/134914890-1721a89d-4925-4167-9afe-89f967db0707.png)

### 2.3.2 Page Membres :
Page de listing des membres de l'application. Les membres devront être créer au préalable pour pouvoir les utilisés lors du formulaire de création d'une commande.
Les membres peuvent être désactivé s'il ne font plus parti du club ou pour qu'il ne soit pas listé dans la liste déroulante lors de la création de commande.

Formulaire de création de nouveau membre :

![image](https://user-images.githubusercontent.com/69145464/134915374-002106fc-43c8-40f1-9e7c-223334d3b4dc.png)

### 2.3.3 Page des prix des tubes :

Listing des prix (club / membres) et marques par type de volant.

Formulaire de création d'un nouveau prix de tube :

![image](https://user-images.githubusercontent.com/69145464/134920213-332d788a-6906-4bbc-94fb-55ef7681292d.png)

## 3. Technologies utilisées

### 3.1 Les langages utilisées

L’application suivra une architecture de conception appelé MVC (Modèle Vue Contrôleur). 
L’architecture de conception MVC est un pattern architectural qui sépare les données (le modèle), l'interface utilisateurs (la vue) et la logique de contrôle (le contrôleur).

Ce modèle de conception impose donc une séparation en 3 couches :
-	Le modèle : Il représente les données de l’application. Il définit aussi l’interaction avec la base dde données et le traitement de ces données.
-	La vue : Elle représente l’interface utilisateur, ce avec quoi il interagit. Elle n’effectue aucun traitement, elle se contente d’afficher les données que lui fournit le modèle. Il peut tout à fait y avoir plusieurs vues qui présentent les données d’un même modèle.
-	Le contrôleur : Il gère l’interface entre le modèle et le client. Il va interpréter la requête de ce dernier pour lui envoyer la vue correspondante. Il effectue la synchronisation entre le modèle et les vues.

## 3.2 Interface utilisateurs

Comme dit au départ, c’est une application web. Qui dit application web, dit navigateur internet et page web.
L’interface utilisateur sera donc faite de différentes page web qui seront écrites en html(5).
Du javascript sera présent pour animer ou ajouter de l’interaction au page.
Pour la partie visuelle et esthétique des pages du CSS est utilisé.

## 3.3 Contrôleur de l'application

Le contrôleur de l’application est le cerveau de celle-ci. L’application tournera sur un serveur d’application Tomcat embarqué.
En effet, le projet utilisant spring boot, il peut incorporer un serveur web pour ce lancer.
Le programme du contrôleur sera écrit en Java afin de bénéficier de la programmation orienté objet.
Maven, Spring et Hibernate sont 3 Frameworks (boite à outils) utilisé dans l'application. Ils permettront plus facilement le développement de l’application sous l’architecture de conception MVC.

## 3.4 Le modèle

Le modèle est le nom utilisé dans l’architecture de conception MVC pour la partie base de données.
La base de données que j’utiliserais est celle de MariaDB/MySQL.
Pour la partie test : H2 database.

# 4. Conclusion

L'application tourne actuellement sur un raspberry sur un serveur local.
L'application est lancé en tant que service linux. Si le raspberry vient à être éteint/rallumé (coupure de courant), le service se relance automatiquement au bout de quelques minutes.
Il est simple de mettre à jour l'application en utilisant le serveur ftp du raspberry pour déposé la nouvelle version.