[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/t19xNtmg)

# TP1 - Design Pattern Observer  
**Auteur : IASD02**

---

## 1. Récupération du code source
Dans cette première étape, nous avons récupéré le code fourni contenant la structure de base du projet Maven multi-modules.  
Le projet contient quatre modules principaux :

- **time-service** : définit l’interface `TimerService` et les interfaces liées à l’observation.  
- **time-service-impl** : implémente le service via la classe `DummyTimeServiceImpl`.  
- **timer-service-client** : contient les classes clientes comme `Horloge` et `CompteARebours`.  
- **launcher** : point d’entrée du programme.

---

## 2. Ouverture et observation du projet
Le projet a été ouvert dans IntelliJ IDEA. Nous avons observé que :

- Le module `time-service` contient l’interface `TimerService` et deux autres interfaces pour gérer les observateurs.  
- L’implémentation de base `DummyTimeServiceImpl` simule le passage du temps.  
- Aucun affichage direct de l’heure n’est encore implémenté.

---

## 3. Implémentation de la classe Horloge

### Objectif
Faire en sorte que la classe `Horloge` affiche l’heure courante à chaque seconde, en observant le service de temps.

### Travail effectué
1. Instanciation du `TimerService` dans la classe principale `App`.  
2. Injection de ce service dans une instance de `Horloge`.  
3. Modification de `Horloge` pour qu’elle s’inscrive comme observateur du service.  
4. Vérification que `Horloge` dépend uniquement de l’interface `TimerService`.

### Résultat attendu
Plusieurs horloges affichent en console l’heure courante simultanément.

---

## 4. Création de la classe CompteARebours

### Objectif
Créer une classe cliente similaire à `Horloge`, mais qui décrémente une valeur entière à chaque seconde.

### Travail effectué
1. Création de la classe `CompteARebours` prenant un entier initial en paramètre.  
2. Décrémentation à chaque seconde jusqu’à 0.  
3. Ajout d’un mécanisme de désinscription automatique quand le compteur atteint 0.  
4. Instanciation de 10 compteurs avec des valeurs aléatoires entre 10 et 20.

### Observation
Lors des premiers tests, plusieurs erreurs se produisent lorsque plusieurs objets sont enregistrés comme observateurs, notamment des conflits d’accès concurrents.

### Question
Pourquoi ces bogues apparaissent-ils ?  
**Réponse :** Le mécanisme d’observation initial n’est pas thread-safe et ne gère pas correctement les inscriptions multiples ou les suppressions d’observateurs pendant la notification.

---

## 5. Amélioration avec PropertyChangeSupport

### Objectif
Remplacer le mécanisme d’observation manuel par la classe standard `PropertyChangeSupport`.

### Travail effectué
- Modification du service pour utiliser `PropertyChangeSupport`.  
- L’interface `TimerChangeListener` hérite désormais de `PropertyChangeListener`.  
- Les clients (`Horloge`, `CompteARebours`) s’enregistrent avec `addPropertyChangeListener()`.

### Résultat
Les problèmes de bogues disparaissent. Les notifications sont désormais gérées correctement.

---

## 6. Bonus : Interface graphique

### Objectif
Créer une interface graphique (Swing) affichant l’heure en temps réel.

### Travail effectué
- Création d’une classe `HorlogeGraphique` dans le module `Gui`.  
- Utilisation d’un `JLabel` pour afficher l’heure mise à jour à chaque seconde.  
- Inscription de la fenêtre comme observatrice du `TimerService`.

---
![Horloge](pics/horloge.png)

## Conclusion
Ce TP nous a permis de comprendre :

- La structuration modulaire d’un projet Maven.  
- La mise en œuvre d’un service observable.  
- L’utilisation correcte de `PropertyChangeSupport` pour la synchronisation.  
- La liaison entre logique applicative et interface graphique.
