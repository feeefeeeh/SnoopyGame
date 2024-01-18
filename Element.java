public class Element {
    private Carte carte;
    public static final int ESPACE_VIDE = 0;
    public static final int BLOC_CASSABLE = 1;
    public static final int BLOC_POUSSABLE = 2;
    public static final int PIEGE = 3;
    public static final int BLOC_INVINCIBLE = 4;
    public static final int TAPIS_ROULANT = 6;
    public static final int BALLE = 7;
    public static final int SNOOPY = 8;
    public static final int OISEAU = 9;

    private int type;
    private boolean estTraversable;

    public Element(int type) {
        this.type = type;
        this.estTraversable = determineTraversabilite(type);
    }

    private boolean determineTraversabilite(int type) {
        switch (type) {
            case ESPACE_VIDE:
            case BLOC_POUSSABLE:
            case TAPIS_ROULANT:
            case OISEAU:
                return true;
            case BLOC_CASSABLE:
            case PIEGE:
            case BLOC_INVINCIBLE:
            case BALLE:
            case SNOOPY:
                return false;
            default:
                throw new IllegalArgumentException("Type d'élément inconnu: " + type);
        }
    }

    // Getters et Setters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        this.estTraversable = determineTraversabilite(type);
    }

    public boolean estTraversable() {
        return estTraversable;
    }

    public void interagir(Snoopy snoopy, char action, Direction direction) {
        switch (type) {
            case BLOC_CASSABLE:
                if (action == 'A') {
                    casserBloc();
                }
                break;
            case BLOC_POUSSABLE:
                pousserBloc(snoopy, direction);
                break;
            case PIEGE:
                if (action == 'A') {
                    casserBloc();
                    snoopy.perdreVie();
                }
                break;
            case OISEAU:
                collecterOiseau();
                break;
            case TAPIS_ROULANT:
                deplacerSnoopySurTapis(snoopy);
                break;
            // Ajoutez d'autres cas au besoin
        }
    }

    private void casserBloc() {
        setType(ESPACE_VIDE);
    }

    private void pousserBloc(Snoopy snoopy, Direction direction) {
        // Calculer la nouvelle position du bloc basée sur la direction de Snoopy
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

        // La position actuelle du bloc devant Snoopy
        int blocX = snoopy.getX() + deltaX;
        int blocY = snoopy.getY() + deltaY;

        // Vérifier si le bloc peut être poussé (la case devant le bloc est vide)
        if (carte.estDansLesLimites(blocX + deltaX, blocY + deltaY)
                && carte.getCell(blocX + deltaX, blocY + deltaY) == Element.ESPACE_VIDE) {
            // Pousser le bloc en mettant à jour la carte
            carte.setCell(blocX + deltaX, blocY + deltaY, Element.BLOC_POUSSABLE); // Nouvelle position du bloc
            carte.setCell(blocX, blocY, Element.ESPACE_VIDE); // Ancienne position du bloc devient vide
        }
    }

    private void collecterOiseau() {
        setType(ESPACE_VIDE);
    }

    private void deplacerSnoopySurTapis(Snoopy snoopy) {
        // Cette méthode doit déplacer Snoopy d'une case vers la droite
        // si la case adjacente est libre (c'est-à-dire un ESPACE_VIDE).
        int x = snoopy.getX();
        int y = snoopy.getY();

        // Vérifier si la case à droite est libre
        if (carte.getCell(x + 1, y) == ESPACE_VIDE) {
            snoopy.deplacer(Direction.DROITE); // Déplacer Snoopy d'une case vers la droite
        }
        // Sinon, Snoopy reste sur place (le tapis roulant n'a pas d'effet)
    }

}
