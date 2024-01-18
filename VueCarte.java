import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class VueCarte extends JPanel {
    private Carte carte;

    public VueCarte(Carte carte) {
        this.carte = carte;
        // Taille préférée du panel, suppose que chaque cellule est un carré de 20x20
        // pixels
        setPreferredSize(new Dimension(carte.getLargeur() * 20, carte.getHauteur() * 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessinez la carte et les entités ici
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                int cell = carte.getCell(x, y);
                if (cell == Element.SNOOPY) {
                    g.setColor(Color.RED);
                } else if (cell == Element.BALLE) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * 20, y * 20, 20, 20);
            }
        }
        // Dessiner Snoopy, les balles, etc...
    }

    // Méthode pour rafraîchir l'affichage lorsque l'état de la carte change
    public void rafraichir() {
        repaint(); // Demande à Swing de repeindre le panel, ce qui appelle paintComponent
    }
}
