package controleur;

import modele.*;
import vue.Ihm;
import java.util.*;

public abstract class Controleur {
    protected final Ihm ihm;
    protected Joueur joueur1;
    protected Joueur joueur2;
    protected final Queue<Joueur> ordreDesJoueurs = new LinkedList<>();
    protected Plateau plateau;

    public Controleur(Ihm ihm) {
        this.ihm=ihm;
    }

    /**
     * Enregistre les noms des joueurs et crée les joueurs
     */
    protected void enregistrementNom(){
        joueur1=new Joueur(ihm.demanderNom(1));
        joueur2=new Joueur(ihm.demanderNom(2));
    }

    /**
     * Initialise le jeu
     */
    protected abstract void initJeu();

    /**
     * Initialise la file des joueurs pour chaque début de partie, le joueur 1 en premier puis le joueur 2
     */
    protected void initialisationJoueur(){
        while (!ordreDesJoueurs.isEmpty()){
            ordreDesJoueurs.poll();
        }
        ordreDesJoueurs.add(joueur1);
        ordreDesJoueurs.add(joueur2);
    }

    /**
     * Initialise un support de jeu, crée une file pour l'ordre des joueurs,
     * et lance la partie
     */
    protected abstract void initialisationPartie();

    /**
     * Affiche l'état du jeu au moment de l'appel de la méthode sur les jeux spécifiques et le tour du joueur courant
     * @param joueur correspond au joueur courant
     */
    protected abstract void affichageDebutTour(Joueur joueur);

    /**
     * Traite le coup saisit par le joueur
     * Vérifie si le coup est valide et l'enregistre
     * L'état du support sera mis à jour
     * @throws CoupInvalideException si le coup est invalide
     */
    protected abstract void traiterCoup (Joueur joueur)  throws CoupInvalideException;

    /**
     *
     * @return le joueur suivant pour qu'il puisse jouer son tour
     */
    protected Joueur joueurSuivant(){
        Joueur suivant = ordreDesJoueurs.poll();
        ordreDesJoueurs.add(suivant);
        return suivant;
    }

    /**
     * gère le tour d'un joueur
     * affiche les informations nécessaire à un début de tour du joueur courant
     * Si le coup est valide, l'état de la grille change et on change de joueur
     * Sinon, un message d'erreur est affiché et le joueur rejoue son tour jusqu'à ce que son coup soit valide
     */
    protected void tour() {
        Joueur joueurActuel = joueurSuivant();
        affichageDebutTour(joueurActuel);
        while (true) {
            try {
                traiterCoup(joueurActuel);
                break;
            }
            catch (CoupInvalideException e){
                ihm.afficherErreurCoup(e.getMessage());
            }
        }
    }

    /**
     * retrouve le gagnant de la partie
     * le gagnant de la partie se trouve à la fin du file car on vient de l'ajouter du fait qu'il vient de jouer
     * Appelle gagnePartie() de la classe Joueur pour incrémenter son nombre de victoires
     * @return le gagnant de la partie
     */
    //protected abstract <T> Joueur gagnantPartie(T ... j);

    /**
     * Teste si la partie est finie ou non
     * Si la partie est finie  on affiche le gagnant de la partie
     * Si la partie est un match nul on affiche la nullité de la partie
     * Si la partie n'est pas terminée, on change le tour
     */
    protected abstract void partie();

    /**
     *
     * @return le nom du gagnant du jeu à la fin de toutes les parties en fonction de son nombre de victoires, s'ils ont le même score ils sont ex-aequo
     */
    protected String gagnantJeu(){
        if (joueur1.getNbPartiesGagnees()==joueur2.getNbPartiesGagnees())
            return "ex-aequo";
        if (joueur1.getNbPartiesGagnees()<joueur2.getNbPartiesGagnees())
            return joueur2.getNom();
        return joueur1.getNom();
    }

    /**
     *  jouer un jeu de société jusqu'à avoir le gagnant du jeu
     * Enregistre les noms de des joueurs
     * lance une partie tant qu'ils voudront jouer
     * A la fin du jeu ,la méthode afficherGagnantJeu affiche le nom du grand gagnant
     */
    public void jouer(){
        initJeu();
        do{
            initialisationPartie();
        }
        while(ihm.demanderRejouer());
        ihm.afficherGagnantJeu(
                joueur1.getNom(),
                joueur2.getNom(),
                joueur1.getNbPartiesGagnees(),
                joueur2.getNbPartiesGagnees(),
                gagnantJeu());
    }

}
