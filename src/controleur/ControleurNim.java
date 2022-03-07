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
        initialisationJoueur();
        partie();
    }

    @Override
    void traiterCoup(Joueur joueur) throws CoupInvalideException {
        List<Integer> l=((IhmNim)ihm).demanderCoup();
        CoupNim coup = new CoupNim(l.get(0),l.get(1));
        tasJeu.gererCoup(coup);
    }

    @Override
    void tour() {
        Joueur joueurActuel = joueurSuivant();
        while (true) {
            ihm.afficherEtat(tasJeu.toString());
            ((IhmNim) ihm).afficherCoup(joueurActuel.getNom());
            try {
                traiterCoup(joueurActuel);
                break;
            }
            catch (CoupInvalideException e){
                ihm.afficherErreurCoup(e.getMessage());
            }
        }
    }

    @Override
    void partie() {
        while(!tasJeu.partieTerminee()){
            tour();
        }
        ihm.afficherGagnant(gagnantPartie().getNom());
    }

    @Override
    public void jouer(){
        enregistrerNbTas();
        enregistrementNom();
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

    private void enregistrerNbTas(){
        int nbTas= ((IhmNim)ihm).demanderNbTas();
        tasJeu = new Tas(nbTas);
    }
}
