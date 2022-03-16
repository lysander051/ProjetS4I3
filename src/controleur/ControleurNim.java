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

    @Override
    void initialisationPartie() {
        tasJeu.initialiser();
        int coupMax=((IhmNim)ihm).demanderCoupMax();
        tasJeu.setCoupMax(coupMax);
        initialisationJoueur();
        partie();
    }

    @Override
    void traiterCoup(Joueur joueur) throws CoupInvalideException {
        ihm.afficherTour(joueur.getNom());
        List<Integer> l=((IhmNim)ihm).demanderCoup();
        CoupNim coup = new CoupNim(l.get(0),l.get(1));
        tasJeu.gererCoup(coup);
    }

   @Override
    void affichageSupport() {
       ihm.afficherEtat(tasJeu.toString());
   }


    @Override
    void partie() {
        while(!tasJeu.partieTerminee()){
            tour();
        }
        ihm.afficherGagnant(gagnantPartie().getNom());
    }

    @Override
   public void initJeu(){
        enregistrerNbTas();
        enregistrementNom();
    }

    private void enregistrerNbTas(){
        int nbTas= ((IhmNim)ihm).demanderNbTas();
        tasJeu = new Tas(nbTas);
    }
}
