import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Jeu {
    private Carte carte;
    private Snoopy snoopy;
    private int niveauActuel;
    private int oiseauxCollectes;
    private int score; // Score total pour tous les niveaux
    private long tempsDebutNiveau; // Temps de début du niveau actuel
    private boolean revenirAuMenu;
    private static final int TEMPS_LIMITE = 60000; // 60 secondes en millisecondes
    private static final int NOMBRE_OISEAUX_PAR_NIVEAU = 4;
    private static final int nombreTotalNiveaux = 3;

    public Jeu() {
        niveauActuel = 1; // Commencer le jeu au niveau 1
        score = 0; // Initialiser le score à 0
        oiseauxCollectes = 0; // Initialiser le compteur d'oiseaux collectés
        initialiserNiveau(niveauActuel);
    }

    private void initialiserNiveau(int niveau) {
        tempsDebutNiveau = System.currentTimeMillis(); // Enregistrer le début du niveau
        oiseauxCollectes = 0; // Réinitialiser le compteur d'oiseaux pour le nouveau niveau
        String motDePasse = Carte.MOTS_DE_PASSE[niveau - 1];
        carte = new Carte(niveau, motDePasse);

        // Trouver et placer Snoopy sur la carte
        placerSnoopy(niveau);

        // Initialiser d'autres éléments du niveau...
    }

    private void placerSnoopy(int niveau) {
        int[] positionInitialeSnoopy = carte.trouverPositionInitialeSnoopy();
        if (positionInitialeSnoopy != null) {
            snoopy = new Snoopy(carte, positionInitialeSnoopy[0], positionInitialeSnoopy[1], this);
            carte.setSnoopy(snoopy);
        } else {
            System.err.println("Erreur : La position initiale de Snoopy n'a pas été trouvée sur la carte.");
        }
    }

    public void mettreAJour() {
        long tempsEcoule = System.currentTimeMillis() - tempsDebutNiveau;
        if (tempsEcoule > TEMPS_LIMITE) {
            perdreVieEtReinitialiserSiNecessaire();
        }

        // Ajouter d'autres logiques de mise à jour si nécessaire...
    }

    private void perdreVieEtReinitialiserSiNecessaire() {
        perdreVie();
        if (snoopy.getVies() > 0) {
            initialiserNiveau(niveauActuel);
        }
    }

    public void demarrerJeu() {
        initialiserNiveau(niveauActuel); // Assurez-vous de commencer par initialiser le niveau

        while (!jeuEstTermine()) {
            afficherCarte(); // Ajoutez cette ligne pour afficher la carte à chaque mise à jour
            mettreAJour(); // Mise à jour de l'état du jeu
            // Ajouter des interactions avec l'utilisateur si nécessaire

            verifierFinDeNiveau();
        }

        afficherResultatFinal();
    }

    private void afficherCarte() {
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                System.out.print(carte.getCell(x, y) + " ");
            }
            System.out.println(); // Nouvelle ligne après chaque ligne de la carte
        }
    }

    private void verifierFinDeNiveau() {
        if (oiseauxCollectes >= NOMBRE_OISEAUX_PAR_NIVEAU) {
            passerAuNiveauSuivant();
        }
    }

    private void afficherResultatFinal() {
        if (niveauActuel > nombreTotalNiveaux) {
            gagnerJeu();
        } else {
            gameOver();
        }
    }

    private boolean jeuEstTermine() {
        return snoopy.getVies() <= 0 || niveauActuel > nombreTotalNiveaux;
    }

    public void collecterOiseau() {
        oiseauxCollectes++;
        if (oiseauxCollectes == NOMBRE_OISEAUX_PAR_NIVEAU) {
            passerAuNiveauSuivant();
        }
    }

    private void passerAuNiveauSuivant() {
        niveauActuel++;
        oiseauxCollectes = 0;
        // Calculer le score pour le niveau basé sur le temps restant multiplié par 100
        long tempsRestant = TEMPS_LIMITE - (System.currentTimeMillis() - tempsDebutNiveau);
        score += (tempsRestant / 1000) * 100; // Convertir en secondes et multiplier par 100

        if (niveauActuel < nombreTotalNiveaux) {
            initialiserNiveau(niveauActuel); // Initialiser le prochain niveau
        } else {
            gagnerJeu(); // Si c'était le dernier niveau, exécuter la logique de victoire
        }
    }

    private void gagnerJeu() {
        System.out.println("Félicitations ! Vous avez terminé tous les niveaux.");
        System.out.println("Votre score total est : " + score + ".");

        sauvegarderScore(score); // Sauvegarde le score (à implémenter)

        System.out.println("Choisissez une option :");
        System.out.println("1. Revenir au menu principal");
        System.out.println("2. Quitter le jeu");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                revenirAuMenuPrincipal(); // À implémenter
                break;
            case 2:
                quitterJeu(); // À implémenter
                break;
            default:
                System.out.println("Choix non valide. Retour au menu principal.");
                revenirAuMenuPrincipal(); // À implémenter
                break;
        }
    }

    private void sauvegarderScore(int score) {
        try (FileWriter fw = new FileWriter("scores.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(score);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du score: " + e.getMessage());
        }
    }

    public void revenirAuMenuPrincipal() {
        this.revenirAuMenu = true; // Indiquer que l'utilisateur veut revenir au menu principal
    }

    private void quitterJeu() {
        // Implémentez ici la logique pour quitter le jeu
        System.out.println("Merci d'avoir joué !");
        System.exit(0); // Quitte l'application
    }

    private void perdreVie() {
        snoopy.perdreVie();
        if (snoopy.getVies() <= 0) {
            gameOver(); // Si Snoopy n'a plus de vies, terminer le jeu
        } else {
            initialiserNiveau(niveauActuel); // Réinitialiser le niveau si Snoopy a encore des vies
        }
    }

    private void gameOver() {
        System.out.println("Game Over! Votre score final est : " + score);

        System.out.println("Choisissez une option :");
        System.out.println("1. Revenir au menu principal");
        System.out.println("2. Quitter le jeu");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                revenirAuMenuPrincipal(); // Méthode pour revenir au menu principal
                break;
            case 2:
                quitterJeu(); // Méthode pour quitter le jeu
                break;
            default:
                System.out.println("Choix non valide. Retour au menu principal.");
                revenirAuMenuPrincipal(); // Par défaut, revenir au menu principal
                break;
        }
    }

    public int getScore() {
        return score;
    }

    public boolean doitRevenirAuMenu() {
        return revenirAuMenu;
    }
    // Getters et Setters ou autres méthodes publiques pour la classe Jeu
    // ...
}
