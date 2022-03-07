package controleur;

import modele.CoupInvalideException;
import modele.Grille;
import modele.Jeton;
import modele.Joueur;
import vue.Ihm;
import vue.IhmPuissance;

import java.util.HashMap;
import java.util.Map;

public class ControleurPuissance extends Controleur{
    private Grille grille;
    private final Map<Joueur, Jeton> jetonDuJoueur=new HashMap<>();

    public ControleurPuissance(Ihm ihm) {
        super(ihm);
    }

    @Override
    protected void initialisationPartie(){
        grille=new Grille();
        initialisationJoueur();
        String ROUGE="\u001B[31m●\u001B[0m";
        String JAUNE="\u001B[33m●\u001B[0m";
        jetonDuJoueur.put(joueur1,new Jeton (ROUGE));
        jetonDuJoueur.put(joueur2,new Jeton(JAUNE));
        partie();
    }

    @Override
    protected void traiterCoup (Joueur joueur)  throws CoupInvalideException {
        int coup=((IhmPuissance)ihm).demanderCoup(joueur.getNom(), jetonDuJoueur.get(joueur).toString());
        grille.gererCoup(coup,jetonDuJoueur.get(joueur));
    }

    @Override
    protected void tour(){
        Joueur joueurActuel = joueurSuivant();
        ihm.afficherEtat(grille.toString());
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

    @Override
    protected void partie(){
        do{
            tour();
        }
        while(!grille.partieTerminee() && !grille.grilleRemplie());
        ihm.afficherEtat(grille.toString());
        if (!grille.grilleRemplie())
            ihm.afficherGagnant(gagnantPartie().getNom());
        else
            ((IhmPuissance)ihm).afficherPartieNulle();
    }

    @Override
    public void jouer(){
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
}
