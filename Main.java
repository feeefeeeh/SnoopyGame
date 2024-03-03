import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Créer et afficher la fenêtre du menu
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGraphiQL();
            }
        });
    }
}
