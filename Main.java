import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jeu jeu = new Jeu(); // Créer une instance du jeu

        while (true) {
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
    }

    private static void jouer(Jeu jeu, Scanner scanner) {
        jeu.demarrerJeu(); // Méthode pour démarrer le jeu
        while (!jeu.doitRevenirAuMenu()) {
            // Logique du jeu (mise à jour, interactions, etc.)
            // Ceci peut être une boucle de jeu ou une série d'interactions
        }
        // Après la fin du jeu, revenir automatiquement au menu principal
    }

    private static void afficherMeilleursScores() {
        try (Stream<String> stream = Files.lines(Paths.get("scores.txt"))) {
            stream.mapToInt(Integer::parseInt)
                    .sorted()
                    .limit(3)
                    .forEach(score -> System.out.println("Score: " + score));
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des scores: " + e.getMessage());
        }
    }

    private static void testerCarteAvecMotDePasse(Scanner scanner) {
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
}
