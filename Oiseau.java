public class Oiseau extends Entite {
    public Oiseau(Carte carte, int x, int y) {
        super(carte, x, y);
    }

    @Override
    public void deplacer(Direction direction) {
        // Les oiseaux pourraient ne pas se déplacer, ou vous pouvez ajouter ici une
        // logique spécifique
    }
}
