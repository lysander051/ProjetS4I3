package controleur;

import modele.CoupInvalideException;
import modele.CoupNim;
import modele.Joueur;
import modele.Tas;
import vue.Ihm;
import vue.IhmNim;

import java.util.List;

public class ControleurNim extends Controleur{
    private Tas tasJeu;

    public ControleurNim(Ihm ihm) {
        super(ihm);
    }

    /**
     * On redéfinit la méthode enregistrementNbTas pour le jeu de Nim qu'on a mis en abstract dans la classe Controleur
     */
    private void enregistrerNbTas(){
        int nbTas= ((IhmNim)ihm).demanderNbTas();
        tasJeu = new Tas(nbTas);
    }

    /**
     * On redéfinit la méthode initJeu pour le jeu de Nim
     */
    @Override
    public void initJeu(){
        enregistrerNbTas();
        enregistrementNom();
    }

    /**
     * On redéfinit la méthode initialisationPartie pour le jeu de Nim
     */
    @Override
    protected void initialisationPartie() {
        tasJeu.initialiser();
        int coupMax=((IhmNim)ihm).demanderCoupMax();
        tasJeu.setCoupMax(coupMax);
        initialisationJoueur();
        partie();
    }

    /**
     * On redéfinit la méthode traiterCoup pour le jeu de Nim
     * @param joueur est le joueur courant
     * @throws CoupInvalideException si le coup saisi est incorrect
     */
    @Override
    protected void traiterCoup(Joueur joueur) throws CoupInvalideException {
        List<Integer> l=ihm.demanderCoup();
        CoupNim coup = new CoupNim(l.get(0),l.get(1));
        tasJeu.gererCoup(coup);
    }

    /**
     * On redéfinit la méthode affichageDebutTour pour le jeu de Nim
     * @param joueur est le joueur courant
     */
   @Override
   protected void affichageDebutTour(Joueur joueur) {
        ihm.afficherEtat(tasJeu.toString());
        ihm.afficherTour(joueur.getNom());
   }

    /**
     * On redéfinit la méthode gagnantPartie pour le jeu de Nim
     * @param j correspond aux type Joueur
     * @param <T> utilisé pour avec des joueurs
     * @return le gagnant de la partie
     */
    @Override
    protected <T> Joueur gagnantPartie(T ... j) {
        joueurSuivant();
        Joueur gagnant=joueurSuivant();
        gagnant.gagnePartie();
        return gagnant;
    }

   /* protected Joueur gagnantPartie() {
        joueurSuivant();
        Joueur gagnant=joueurSuivant();
        gagnant.gagnePartie();
        return gagnant;
    }*/

    /**
     * On redéfinit la méthode partie pour le jeu de Nim
     */
    @Override
    protected void partie() {
        while(!tasJeu.partieTerminee()){
            tour();
        }
        ihm.afficherGagnant(gagnantPartie().getNom());
    }

}
