public class Snoopy extends Entite {
    private Jeu jeu;
    private int vies;

    public Snoopy(Carte carte, int x, int y, Jeu jeu) {
        super(carte, x, y);
        this.vies = 1; // Snoopy commence avec 3 vies
        this.jeu = jeu;
    }

    @Override
    public void deplacer(Direction direction) {
        int deltaX = 0, deltaY = 0;
        switch (direction) {
            case HAUT:
                deltaY = -1;
                break;
            case BAS:
                deltaY = 1;
                break;
            case GAUCHE:
                deltaX = -1;
                break;
            case DROITE:
                deltaX = 1;
                break;
        }

        int nouvelleX = x + deltaX;
        int nouvelleY = y + deltaY;

        if (carte.estDansLesLimites(nouvelleX, nouvelleY)) {
            int cell = carte.getCell(nouvelleX, nouvelleY);
            switch (cell) {
                case Element.ESPACE_VIDE:
                case Element.OISEAU:
                    x = nouvelleX;
                    y = nouvelleY;
                    if (cell == Element.OISEAU) {
                        carte.setCell(x, y, Element.ESPACE_VIDE); // L'oiseau est collecté
                        collecterOiseau();
                    }
                    break;
                case Element.BLOC_POUSSABLE:
                    // Appeler une méthode pour pousser le bloc si possible
                    // Cette méthode devra également déplacer Snoopy si le bloc a été poussé
                    pousserBloc(nouvelleX, nouvelleY, deltaX, deltaY);
                    break;
                case Element.TAPIS_ROULANT:
                    // Supposons que le tapis roulant pousse Snoopy vers la droite
                    deplacerSnoopySurTapis(Direction.DROITE);
                    break;
                case Element.BLOC_CASSABLE:
                    // Si le joueur appuie sur 'A', le bloc se casse
                    casserBloc(nouvelleX, nouvelleY);
                    break;
                case Element.PIEGE:
                    // Si le joueur appuie sur 'A', le piège se casse mais Snoopy perd une vie
                    casserBloc(nouvelleX, nouvelleY);
                    perdreVie();
                    break;
                // Ajouter d'autres cas si nécessaire
            }
        }
    }

    public void collecterOiseau() {
        jeu.collecterOiseau(); // Informez la classe Jeu qu'un oiseau a été collecté
    }

    private void pousserBloc(int x, int y, int dx, int dy) {
        // Vérifier si la case derrière le bloc est vide
        if (carte.getCell(x + dx, y + dy) == Element.ESPACE_VIDE) {
            // Pousser le bloc
            carte.setCell(x + dx, y + dy, Element.BLOC_POUSSABLE);
            carte.setCell(x, y, Element.ESPACE_VIDE);
            // Déplacer Snoopy sur l'emplacement du bloc initial
            this.x = x;
            this.y = y;
        }
    }

    private void deplacerSnoopySurTapis(Direction direction) {
        // La logique pour déplacer Snoopy sur le tapis roulant
    }

    private void casserBloc(int x, int y) {
        // Casser le bloc et le remplacer par un ESPACE_VIDE
        carte.setCell(x, y, Element.ESPACE_VIDE);
    }

    public void perdreVie() {
        if (vies > 0) {
            vies--;
            System.out.println("Snoopy a perdu une vie ! Vies restantes : " + vies);
            if (vies == 0) {
                System.out.println("Plus de vies restantes. Game over !");
                // Logique pour gérer la défaite ou la réinitialisation du jeu
            }
        }
    }

    // Getters et Setters
    public int getVies() {
        return vies;
    }
}
