<p align="center">
<a href="./" target="_blank"><img src="logo.png" width="300" alt="Lens Judge Logo"></a>
</p>

<h1 style="font-size: 22px">Récapitulatif des travaux réalisés en groupe</h1>

- **Jour 1 | Conception du projet**
  - Réalisation du diagramme de classes
  - Choix des patrons de conceptions

- **Jour 2 | Début du développement**
  - Modification du diagramme de classes
  - Début du codage pour le jalon Compilation

- **Jour 3 | Avancement sur les exécutions**
  - Finalisation des compilations
  - Modification du diagramme de classes
  - Début du codage pour le jalon Exécution
  - Début de la réalisation des Portfolios individuels

- **Jour 4 | Mise en place de l'exécution**
  - Création de la limitation de temps
  - Finalisation de l'exécution
  - Début du codage pour le jalon Vérification
  - Mise à jour des Portfolios

- **Jour 5 | Implémentation des vérifications**
  - Implémentation des vérifications
  - Finalisation des vérifications
  - Mise à jour des Portfolios
  - Redaction du README pour la librairie

<h1 style="font-size: 22px">Tableau récapitulatif des fonctionnalités</h1>

| _Fonctionnalité_                                        | _Patron(s) de conception_ | _Auteur(s)_   |
|------------------------------------------------------|-------------------------|-------------|
| Représentation d’un cas de test                       |    Aucun                       | FAUQUET Sacha, DERANCOURT Louis          |
| Représentation d’un problème                           |   Builder                   | FAUQUET Sacha, DERANCOURT Louis            |
| Configuration d’un problème                           |   Builder                      | FAUQUET Sacha, DERANCOURT Louis            |
| Représentation d’un processus                          |   Strategy                     |  Tout le monde           |
| Limitation du temps d’exécution d’un processus        |   Strategy                      |   NOTTEAU Romain          |
| Limitation de la mémoire d’un processus               |   Strategy                    |    NOTTEAU Romain   |
| Compilation d’un programme C                           |   Strategy                      | FAUQUET Sacha            |
| Compilation d’un programme C++                         |   Strategy                     | CHEAM Nathan            |
| Compilation d’un programme Java                        |   Strategy                      | DERANCOURT Louis            |
| Compilation d’un programme Python                      |   Strategy                      | NOTTEAU Romain            |
| Exécution d’un programme C compilé                     |   Strategy                     | CHEAM Nathan            |
| Exécution d’un programme C++ compilé                   |   Strategy                      | DERANCOURT Louis            |
| Exécution d’un programme Java compilé                  |   Strategy                      | FAUQUET Sache            |
| Vérification stricte de la solution                   |   Strategy                     | FAUQUET Sacha            |
| Vérification avec tolérance sur la casse              |   Decorator                      | NOTTEAU Romain            |
| Vérification avec tolérance sur les espaces           |   Decorator                      | NOTTEAU Romain            |
| Vérification avec tolérance sur l’ordre               |   Decorator                      | FAUQUET Sacha            |
| Vérification d’une solution parmi plusieurs           |   Strategy                      | CHEAM Nathan            |
| Vérification déléguée à un programme externe          |   Strategy                      | DERANCOURT Louis            |
| Configuration de l’exécution sur un cas de test      |    Builder                      | DERANCOURT Louis            |
| Programme principal du juge automatique                |   Aucun                      | DERANCOURT Louis            |


<h1 style="font-size: 22px">Tableau des tâches</h1>

| _Nom du jalon_ |               _Tâche effectuée (avec id du ticket)_               |      _Auteur(s)_ |
| :--------------|:-----------------------------------------------------------------:|-----------------:|
| Projet globale |                     **#4** Rédaction : README                     |     CHEAM Nathan |
| Projet globale |             **#1** Réalisation : classe Judge (main)              | DERANCOURT Louis |
| Projet globale |                      **#10** Refactorisation                      | DERANCOURT Louis |
| Projet globale |                **#12** Réalisation : diagramme UML                |   NOTTEAU Romain |
| Compilation |              **#2** Réalisation : classe FileManager              |   NOTTEAU Romain |
| Compilation |      **#6** Réalisation : compilation dans classe CppProcess      |     CHEAM Nathan |
| Compilation |       **#8** Réalisation : compilation dans classe CProcess       |    FAUQUET Sacha |
| Compilation |     **#7** Réalisation : compilation dans classe JavaProcess      | DERANCOURT Louis |
| Compilation |    **#5** Réalisation : compilation dans classe PythonProcess     |   NOTTEAU Romain |
| Compilation |        **#9** Correction : compilateurs + tests unitaires         |   NOTTEAU Romain |
| Exécution | **#3** Réalisation : classes Problème + TestCase + ProblemBuilder |    FAUQUET Sacha |
| Exécution |                **#11** Unification des stratégies                 | DERANCOURT Louis |
| Exécution |          **#13** Complétion de la classe ProblemBuilder           |     CHEAM Nathan |
| Exécution |       **#14** Réalisation : exécution dans classe CProcess        |     CHEAM Nathan |
| Exécution |     **#15** Réalisation : exécution dans classe PythonProcess     |   NOTTEAU Romain |
| Exécution |      **#16** Réalisation : exécution dans classe CppProcess       | DERANCOURT Louis |
| Exécution |      **#17** Réalisation : exécution dans classe JavaProcess      |    FAUQUET Sacha |
| Exécution |          **#18** Implémentation : interface IContrainte           |   NOTTEAU Romain |
| Exécution |          **#19** Implémentation : Contrainte de temps           |   NOTTEAU Romain |
| Exécution |          **#20** Application des contraintes            |   DERANCOURT Louis |
| Projet global |          **#21** Rédaction des commentaires en anglais            |   NOTTEAU Romain |
| Verification |          **#22** Implémentation : interface et classe vérification            |   NOTTEAU Romain FAUQUET Sacha|
| Verification |          **#23** Implémentation : validations dans Problem           |   CHEAM Nathan |
| Verification |          **#24** Implémentation final des validations          |   DERANCOURT Louis |
| Projet global |          **#25** Accorder avec SonarLint          |   DERANCOURT Louis |
| Projet global |          **#26** Gestion Appel CMD         |   NOTTEAU Romain |
| Verification |          **#27** Ajout des lecteurs          |   DERANCOURT Louis |
| Verification |          **#28** Lecture pour tout        | FAUQUET Sacha   |
| Verification |          **#29** Lecture pour un          | CHEAM Nathan   |

<h1 style="font-size: 22px">Manuel d'utilisation</h1>

<h2 style="font-size: 15px">Generation du fichier .jar</h2>
  - Utiliser la commande _./gradlew shadowJar_
  - Le fichier .jar se trouvera dans _build/libs_

<h2 style="font-size: 15px">Installation de la librairie</h2>
  - Ajouter dans le build.gradle :

  ```gradle
  dependencies {
    implementation files('libs/nom-de-la-bibliotheque.jar)'
  }
  ```

<h2 style="font-size: 15px">Lancer le .jar</h2>
  - Utiliser la commande _java -jar build/libs/    SAE-B3_Lens-Judge-1.0-SNAPSHOT.jar_ (vous préciserez le fichier source et le dossier test à la suite)
  - Des options de vérification supplémentaire sont disponibles : _-IgnoreCasse_ et _-IgnoreSpace_ ainsi qu'un choix parmis ces 3 (uniquement un seul) : _-readLine_, _-readForAll_, _-readOneForAll_
