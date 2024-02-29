public class Balle extends Entite {
    private int deltaX = 1; // Changement initial en x (1 pour diagonale vers la droite, -1 pour la gauche)
    private int deltaY = 1; // Changement initial en y (1 pour diagonale vers le bas, -1 pour le haut)

    public Balle(Carte carte, int x, int y) {
        super(carte, x, y);
    }

    @Override
    public void deplacer(Direction direction) {
        // Vérifiez les collisions avec les bords de la carte
        if (x + deltaX >= carte.getLargeur() || x + deltaX < 0) {
            deltaX = -deltaX; // Changement de direction en x
        }
        if (y + deltaY >= carte.getHauteur() || y + deltaY < 0) {
            deltaY = -deltaY; // Changement de direction en y
        }

        // Mise à jour de la position de la balle
        x += deltaX;
        y += deltaY;

        // Vérifiez si la balle a touché Snoopy
        verifierCollisionAvecSnoopy();
    }

    //afficher la position de la balle en temps réel
    public void afficherPositionBalle() {
        System.out.println("Position de la balle : (" + x + ", " + y + ")");
    }

    void verifierCollisionAvecSnoopy() {
        if (carte.getCell(x, y) == Element.SNOOPY) {
            // La balle a touché Snoopy
            // Faites perdre une vie à Snoopy et réinitialisez le niveau
            // Cette logique doit être implémentée en fonction de votre structure de jeu
        }
    }
    // Autres méthodes...
}
