import java.util.ArrayList;
import java.util.List;

public class Carte {
    private Snoopy snoopy;
    private final int largeur = 20; // Largeur fixe de la carte
    private final int hauteur = 10; // Hauteur fixe de la carte
    private int[][] grille; // Grille représentant le niveau
    private List<Balle> balles = new ArrayList<>();

    public static final String[] MOTS_DE_PASSE = { "mdpNiveau1", "mdpNiveau2", "mdpNiveau3" }; // Mots de passe pour
                                                                                               // chaque niveau

    public Carte(int niveau, String motDePasse) {
        verifierMotDePasse(niveau, motDePasse);
        initialiserCarte(niveau);
    }

    // Une méthode d'utilité pour copier le contenu d'une grille à une autre

    public void verifierMotDePasse(int niveau, String motDePasse) {
        if (!MOTS_DE_PASSE[niveau - 1].equals(motDePasse)) {
            throw new IllegalArgumentException("Mot de passe incorrect pour le niveau " + niveau);
        }
    }

    private void initialiserCarte(int niveau) {
        switch (niveau) {
            case 1:
                grille = creerNiveau1();
                break;
            case 2:
                grille = creerNiveau2();
                break;
            case 3:
                grille = creerNiveau3();
                break;
            default:
                throw new IllegalArgumentException("Niveau inconnu: " + niveau);
        }
    }

    public void ajouterBalle(Balle balle) {
        balles.add(balle);
    }

    public int[] trouverPositionInitialeSnoopy() {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (grille[y][x] == 9) { // 9 représente Snoopy sur la grille
                    return new int[] { x, y };
                }
            }
        }
        return null; // Retourne null si Snoopy n'est pas trouvé (ce qui ne devrait pas arriver)
    }

    private int[][] creerNiveau1() {
        return new int[][] {
                // Matrice du Niveau 1
                { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
                { 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7 },
                { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 }
        };
    }

    private int[][] creerNiveau2() {
        return new int[][] {
                // Matrice du Niveau 2
                { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
                { 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
                { 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0 },
                { 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
                { 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7 },
                { 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
                { 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0 },
                { 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 }
        };
    }

    private int[][] creerNiveau3() {
        return new int[][] {
                // Matrice du Niveau 3
                { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
                { 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 0 },
                { 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 0 },
                { 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 0 },
                { 8, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 7 },
                { 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 6, 6, 0, 0 },
                { 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 0, 0 },
                { 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 4, 4, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 }
        };
    }

    public int getCell(int x, int y) {
        return grille[y][x];
    }

    public void setCell(int x, int y, int valeur) {
        grille[y][x] = valeur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public boolean estDansLesLimites(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }

    public void setSnoopy(Snoopy snoopy) {
        this.snoopy = snoopy;
    }

    public Snoopy getSnoopy() {
        return snoopy;
    }

    public List<Balle> getBalles() {
        return this.balles;
    }

}