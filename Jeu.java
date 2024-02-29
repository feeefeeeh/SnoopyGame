import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class Jeu {
    private Carte carte;
    private Snoopy snoopy;
    private static int niveauActuel;
    private static int oiseauxCollectes;
    private static int score; // Score total pour tous les niveaux
    private long tempsDebutNiveau; // Temps de début du niveau actuel
    private static boolean revenirAuMenu;
    private static final int TEMPS_LIMITE = 10000; // 60 secondes en millisecondes
    private static final int NOMBRE_OISEAUX_PAR_NIVEAU = 0;
    private static final int nombreTotalNiveaux = 3;

    public Jeu() {
        niveauActuel = 1; // Commencer le jeu au niveau 1
        score = 0; // Initialiser le score à 0
        oiseauxCollectes = 0; // Initialiser le compteur d'oiseaux collectés
        initialiserNiveau(niveauActuel);

    }

    void initialiserNiveau(int niveau) {
        System.out.println("Niveau " + niveau);
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
            System.out.println("Snoopy a été placé sur la carte.");
        } else {
            System.err.println("Erreur : La position initiale de Snoopy n'a pas été trouvée sur la carte.");
        }
    }


    public void mettreAJour() {
        // Mettre à jour les balles
        for (Balle balle : carte.getBalles()) {
            balle.deplacer(null); // Assurez-vous que la balle a une logique de déplacement définie
            balle.verifierCollisionAvecSnoopy(); // Vérifiez si la balle a touché Snoopy
        }

        // Vérifier le temps écoulé pour le niveau
        if (tempsEcoule() >= TEMPS_LIMITE) {
            System.out.println("Snoopy perd une vie !");
            snoopy.perdreVie(); // Snoopy perd une vie si le temps est écoulé
            if (snoopy.getVies() > 0) {
                System.out.println(" Le niveau recommence il reste à Snoopy " + snoopy.getVies() + "!");
                initialiserNiveau(niveauActuel); // Réinitialiser le niveau si Snoopy a encore des vies
            } else {
                gameOver(); // Sinon, c'est la fin du jeu
            }
        }

        // Vérifier si tous les oiseaux ont été collectés
        if (oiseauxCollectes >= NOMBRE_OISEAUX_PAR_NIVEAU) {
            passerAuNiveauSuivant(); // Passer au niveau suivant
        }
    }


    public void demarrerJeu() {
        initialiserNiveau(niveauActuel);
        afficherCarte();
        while (!jeuEstTermine()) {
            // Logique de jeu ici
            mettreAJour();
            afficherCarte();
        }
        if (doitRevenirAuMenu()) {
            // Préparer tout pour revenir au menu
            initialiserPourMenu();
        }
    }

    private boolean doitRevenirAuMenu() {
        initialiserPourMenu();
        Menuprincipal(this, new Scanner(System.in));
        return true;
    }

    private static void initialiserPourMenu() {
        // Réinitialiser les variables du jeu à leurs états initiaux
        niveauActuel = 1;
        score = 0;
        oiseauxCollectes = 0;
        revenirAuMenu = false;
        // Il faudrait probablement aussi réinitialiser l'état de la carte et des
        // entités ici.
        // ...
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
        score += (int) ((tempsRestant / 1000) * 100); // Convertir en secondes et multiplier par 100

        if (niveauActuel <= nombreTotalNiveaux) {
            initialiserNiveau(niveauActuel); // Initialiser le prochain niveau
            //afficherCarte();
        } else {
            gagnerJeu(); // Si c'était le dernier niveau, exécuter la logique de victoire
        }
    }

    private void gagnerJeu() {
        System.out.println("Félicitations ! Vous avez terminé tous les niveaux.");
        System.out.println("Votre score total est : " + score + ".");

        sauvegarderScore(score); // Sauvegarde le score (à implémenter)
        MenuFinDePartie();
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
        //aller au menu principal
        initialiserPourMenu();
        revenirAuMenu = true;
    }

    //méthode pour revenir au menu principal


    private void quitterJeu() {
        // Implémentez ici la logique pour quitter le jeu
        System.out.println("Merci d'avoir joué !");
        System.exit(0); // Quitte l'application
    }

    private void gameOver() {
        System.out.println("Game Over !");
        System.out.println("Votre score final est : " + score + ".");
        MenuFinDePartie();
    }
    public void MenuFinDePartie () {
        System.out.println("Choisissez une option :");
        System.out.println("1. Revenir au menu principal");
        System.out.println("2. Quitter le jeu");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                initialiserPourMenu();
                Menuprincipal(this, scanner);
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

    public static void Menuprincipal(Jeu jeu, Scanner scanner) {
        initialiserPourMenu();
        System.out.println("Menu Principal:");
        System.out.println("1. Jouer");
        System.out.println("2. Mot de passe");
        System.out.println("3. Meilleurs scores");
        System.out.println("4. Quitter");
        System.out.print("Votre choix: ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consomme le retour à la ligne

        switch (choix) {
            case 1:
                jouer(jeu, scanner); // Méthode pour lancer le jeu
                break;
            case 2:
                testerCarteAvecMotDePasse(scanner);
                break;
            case 3:
                afficherMeilleursScores();
                break;
            case 4:
                System.out.println("Fin du jeu.");
                return;
            default:
                System.out.println("Choix invalide.");
                break;
        }
    }


    private static void jouer(Jeu jeu, Scanner scanner) {
        jeu.demarrerJeu(); // Méthode pour démarrer le jeu
        // Après la fin du jeu, revenir automatiquement au menu principal
    }

    static void afficherMeilleursScores() {
        try (Stream<String> stream = Files.lines(Paths.get("scores.txt"))) {
            stream.mapToInt(Integer::parseInt)
                    .boxed() // Convertit IntStream en Stream<Integer> pour permettre le tri en ordre décroissant
                    .sorted(Collections.reverseOrder()) // Trie en ordre décroissant
                    .limit(3) // Limite aux 3 premiers éléments après le tri
                    .forEach(score -> System.out.println("Score: " + score));
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des scores: " + e.getMessage());
        }
    }

    static void testerCarteAvecMotDePasse(Scanner scanner) {
        System.out.print("Entrez le mot de passe de la carte: ");
        String motDePasse = scanner.nextLine();

        try {
            int niveau = determinerNiveauParMotDePasse(motDePasse);
            Carte carte = new Carte(niveau, motDePasse);
            afficherCarte(carte);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static int determinerNiveauParMotDePasse(String motDePasse) {
        for (int i = 0; i < Carte.MOTS_DE_PASSE.length; i++) {
            if (Carte.MOTS_DE_PASSE[i].equals(motDePasse)) {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("Mot de passe inconnu.");
    }

    private static void afficherCarte(Carte carte) {
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                System.out.print(carte.getCell(x, y) + " ");
            }
            System.out.println(); // Nouvelle ligne après chaque ligne de la carte
        }
    }

    public int getScore() {
        return score;
    }

    private long tempsEcoule() {
        return System.currentTimeMillis() - tempsDebutNiveau;
    }

    public Carte getCarte() {
        return this.carte;
    }
}
// Getters et Setters ou autres méthodes publiques pour la classe Jeu
// ..