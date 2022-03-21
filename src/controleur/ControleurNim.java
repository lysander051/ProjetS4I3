package controleur;

import modele.CoupInvalideException;
import modele.CoupNim;
import modele.Joueur;
import modele.Tas;
import vue.Ihm;
import vue.IhmNim;
import java.util.List;

public class ControleurNim extends Controleur{

    public ControleurNim(Ihm ihm) {
        super(ihm);
    }

    /**
     * On enregistre le nombre de tas souhaité pour jouer le jeu de Nim
     */
    private void enregistrerNbTas(){
        int nbTas= ((IhmNim)ihm).demanderNbTas();
        plateau = new Tas(nbTas);
    }

    /**
     * On redéfinit la méthode initJeu pour le jeu de Nim
     * on enregistre le nombre de tas et les noms des joueurs
     */
    @Override
    protected void initJeu(){
        enregistrerNbTas();
        enregistrementNom();
    }

    /**
     * On redéfinit la méthode initialisationPartie pour le jeu de Nim
     * on définit un coup max qui est le nombre d'allumettes maximum à retirer ou 0 si on ne veut pas de nombre d'allumettes

     * */
    @Override
    protected void initialisationPartie() {
        ((Tas)plateau).initialiser();
        int coupMax=((IhmNim)ihm).demanderCoupMax();
        ((Tas)plateau).setCoupMax(coupMax);
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
        plateau.gererCoup(coup);
    }

    /**
     * On redéfinit la méthode affichageDebutTour pour le jeu de Nim
     * @param joueur est le joueur courant
     */
   @Override
   protected void affichageDebutTour(Joueur joueur) {
        ihm.afficherEtat(plateau.toString());
        ihm.afficherTour(joueur.getNom());
   }

    /**
     * On redéfinit la méthode gagnantPartie pour le jeu de Nim
     * @param j correspond aux type Joueur
     * @param <T> utilisé pour avec des joueurs
     * @return le gagnant de la partie
     */
    /*@Override
    protected <T> Joueur gagnantPartie(T ... j) {
        joueurSuivant();
        Joueur gagnant=joueurSuivant();
        gagnant.gagnePartie();
        return gagnant;
    }*/

    /**
     * @return le gagnant de la partie
     */
    private Joueur gagnantPartie() {
        joueurSuivant();
        Joueur gagnant=joueurSuivant();
        gagnant.gagnePartie();
        return gagnant;
    }

    /**
     * On redéfinit la méthode partie pour le jeu de Nim
     */
    @Override
    protected void partie() {
        while(!((Tas)plateau).partieTerminee()){
            tour();
        }
        ihm.afficherGagnant(gagnantPartie().getNom());
    }

}
