package controleur;

import modele.CoupInvalideException;
import modele.Grille;
import modele.Jeton;
import modele.Joueur;
import vue.Ihm;
import vue.IhmPuissance;

import java.util.*;

public class ControleurPuissance extends Controleur{
    private Grille grille;
    private final Map<Joueur, Jeton> jetonDuJoueur=new HashMap<>();
    private final Map<Joueur,Integer> nbRotation=new HashMap<>();
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
        int nbRotAutorise=-1;
        if(((IhmPuissance)ihm).demanderAjoutContrainte()){
            nbRotAutorise=4;
        }
        nbRotation.put(joueur1,nbRotAutorise);
        nbRotation.put(joueur2,nbRotAutorise);
        partie();
    }

    @Override
    protected void traiterCoup (Joueur joueur)  throws CoupInvalideException {
        ihm.afficherTour(jetonDuJoueur.get(joueur).toString()+" "+joueur.getNom());
        if(nbRotation.get(joueur)>=0){
            int choix=((IhmPuissance)ihm).choixMouvement();
            if(choix==1/*avec*/){
                traiterCoupavecRotation(joueur);
                return;
            }
        }
        List<Integer> coup=(ihm.demanderCoup());
            grille.gererCoup(coup.get(0),jetonDuJoueur.get(joueur));

    }
    protected void traiterCoupavecRotation (Joueur joueur)  throws CoupInvalideException {
        if(nbRotation.get(joueur)<=0){
            throw new CoupInvalideException("Vous ne pouvez plus faire une rotation");
        }
        else{
            int nb=nbRotation.get(joueur);
            nbRotation.put(joueur,--nb);
            int sens=((IhmPuissance)ihm).sensRotation();
            grille.gererRotation(sens);

        }

    }

    @Override
    void affichageSupport() {
        ihm.afficherEtat(grille.toString());
    }

    @Override
    protected void partie(){
        Set<Jeton> lesJetonsGagnants=new HashSet<>();
        do{
            tour();
            lesJetonsGagnants=grille.partieTerminee();

        }
        while(lesJetonsGagnants!=null && !grille.grilleRemplie());
        ihm.afficherEtat(grille.toString());

        if (lesJetonsGagnants.size()==1)
        {
            Jeton[] jetonGagnant=(Jeton [])lesJetonsGagnants.toArray();
            ihm.afficherGagnant(gagnantPartie(jetonGagnant[0])).getNom());
        }
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

    /* AFFICHER GAGNANT ON DOIT LE FAIRE A PARTIR DU COULEUR DU JETON ET NON DU ENLEVER ENLEVER */

}
