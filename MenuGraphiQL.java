import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGraphiQL extends JFrame {
    // Constructeur pour initialiser la fenêtre
    public MenuGraphiQL() {
        super("Menu Principal"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termine l'application à la fermeture de la fenêtre

        // Créer un JPanel pour contenir les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Disposition en grille avec 5 lignes, 1 colonne

        // Créer les boutons
        JButton jouerButton = new JButton("Jouer");
        JButton motDePasseButton = new JButton("Mot de passe");
        JButton meilleursScoresButton = new JButton("Meilleurs scores");
        JButton quitterButton = new JButton("Quitter");

        // Ajouter des écouteurs d'événements à chaque boutonimport javax.swing.*;
        //import java.awt.*;
        //import java.awt.event.ActionEvent;
        //
        //public class MenuGraphiQL extends JFrame {
        //    // Constructeur pour initialiser la fenêtre
        //    public MenuGraphiQL() {
        //        super("Menu Principal"); // Titre de la fenêtre
        //        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termine l'application à la fermeture de la fenêtre
        //
        //        // Utiliser un JPanel avec un FlowLayout pour une disposition plus flexible
        //        JPanel panel = new JPanel();
        //        panel.setLayout(new FlowLayout()); // Disposition en flux pour une meilleure répartition des boutons
        //
        //        // Définir une couleur de fond pour le panel
        //        panel.setBackground(Color.lightGray);
        //
        //        // Créer les boutons avec des icônes (les chemins d'accès aux icônes sont hypothétiques)
        //        JButton jouerButton = new JButton("Jouer", new ImageIcon("iconJouer.png"));
        //        JButton motDePasseButton = new JButton("Mot de passe", new ImageIcon("iconMotDePasse.png"));
        //        JButton meilleursScoresButton = new JButton("Meilleurs scores", new ImageIcon("iconScores.png"));
        //        JButton quitterButton = new JButton("Quitter", new ImageIcon("iconQuitter.png"));
        //
        //        // Ajuster les dimensions des boutons pour qu'ils soient plus grands et plus cliquables
        //        Dimension buttonSize = new Dimension(150, 75);
        //        jouerButton.setPreferredSize(buttonSize);
        //        motDePasseButton.setPreferredSize(buttonSize);
        //        meilleursScoresButton.setPreferredSize(buttonSize);
        //        quitterButton.setPreferredSize(buttonSize);
        //
        //        // Ajouter des écouteurs d'événements lambda à chaque bouton pour simplifier le code
        //        jouerButton.addActionListener(e -> jouer());
        //        motDePasseButton.addActionListener(e -> testerMotDePasse());
        //        meilleursScoresButton.addActionListener(e -> afficherMeilleursScores());
        //        quitterButton.addActionListener(e -> System.exit(0)); // Quitter l'application
        //
        //        // Ajouter les boutons au panel
        //        panel.add(jouerButton);
        //        panel.add(motDePasseButton);
        //        panel.add(meilleursScoresButton);
        //        panel.add(quitterButton);
        //
        //        // Ajouter le panel à la fenêtre
        //        this.add(panel);
        //
        //        // Ajouter une barre de menu pour des fonctionnalités supplémentaires
        //        JMenuBar menuBar = new JMenuBar();
        //        JMenu fichierMenu = new JMenu("Fichier");
        //        JMenuItem quitterMenuItem = new JMenuItem("Quitter");
        //        quitterMenuItem.addActionListener(e -> System.exit(0));
        //        fichierMenu.add(quitterMenuItem);
        //        menuBar.add(fichierMenu);
        //        this.setJMenuBar(menuBar);
        //
        //        // Définir la taille de la fenêtre et la rendre visible
        //        this.setSize(500, 400);
        //        this.setVisible(true);
        //    }
        //
        //    private void jouer() {
        //        // Implémentez la logique pour jouer
        //        System.out.println("Lancement du jeu...");
        //    }
        //
        //    private void testerMotDePasse() {
        //        // Implémentez la logique pour tester le mot de passe
        //        System.out.println("Test du mot de passe...");
        //    }
        //
        //    private void afficherMeilleursScores() {
        //        // Implémentez la logique pour afficher les meilleurs scores
        //        System.out.println("Affichage des meilleurs scores...");
        //    }
        //
        //    public static void main(String[] args) {
        //        // Assurez-vous que l'interface graphique est lancée dans le fil d'exécution de l'interface graphique
        //        SwingUtilities.invokeLater(MenuGraphiQL::new);
        //    }
        //}
        jouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appeler ici la méthode pour jouer
                jouer();
            }
        });

        motDePasseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appeler ici la méthode pour tester le mot de passe
                testerMotDePasse();
            }
        });

        meilleursScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appeler ici la méthode pour afficher les meilleurs scores
                afficherMeilleursScores();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quitter l'application
            }
        });

        // Ajouter les boutons au panel
        panel.add(jouerButton);
        panel.add(motDePasseButton);
        panel.add(meilleursScoresButton);
        panel.add(quitterButton);

        // Ajouter le panel à la fenêtre
        this.add(panel);

        // Définir la taille de la fenêtre et la rendre visible
        this.setSize(400, 300);
        this.setVisible(true);
    }

    private void jouer() {
        // Implémentez la logique pour jouer
        System.out.println("Lancement du jeu...");
    }

    private void testerMotDePasse() {
        // Implémentez la logique pour tester le mot de passe
        System.out.println("Test du mot de passe...");
    }

    private void afficherMeilleursScores() {
        // Implémentez la logique pour afficher les meilleurs scores
        System.out.println("Affichage des meilleurs scores...");
    }

    // Point d'entrée de l'application

}
