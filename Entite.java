public abstract class Entite {
    protected int x;
    protected int y;
    protected Carte carte;

    public Entite(Carte carte, int x, int y) {
        this.carte = carte;
        this.x = x;
        this.y = y;
    }

    // Méthode abstraite pour déplacer l'entité, sera implémentée par les
    // sous-classes.
    public abstract void deplacer(Direction direction);

    // Getters et Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
