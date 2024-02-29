import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jeu jeu = new Jeu(); // Créer une instance du jeu

        while (true) {
            Jeu.Menuprincipal(jeu, scanner); // Afficher le menu principal
        }
    }
}
