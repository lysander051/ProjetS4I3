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

    private void enregistrerNbTas(){
        int nbTas= ((IhmNim)ihm).demanderNbTas();
        tasJeu = new Tas(nbTas);
    }

    @Override
    public void initJeu(){
        enregistrerNbTas();
        enregistrementNom();
    }

    @Override
    protected void initialisationPartie() {
        tasJeu.initialiser();
        int coupMax=((IhmNim)ihm).demanderCoupMax();
        tasJeu.setCoupMax(coupMax);
        initialisationJoueur();
        partie();
    }

    @Override
    protected void traiterCoup(Joueur joueur) throws CoupInvalideException {
        List<Integer> l=ihm.demanderCoup();
        CoupNim coup = new CoupNim(l.get(0),l.get(1));
        tasJeu.gererCoup(coup);
    }

   @Override
   protected void affichageDebutTour(Joueur joueur) {
        ihm.afficherEtat(tasJeu.toString());
        ihm.afficherTour(joueur.getNom());
   }

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

    @Override
    protected void partie() {
        while(!tasJeu.partieTerminee()){
            tour();
        }
        ihm.afficherGagnant(gagnantPartie().getNom());
    }

}
