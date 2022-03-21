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

    /**
     * On redéfinit la méthode initJeu pour le puissance 4
     */
    @Override
    protected  void initJeu(){
        enregistrementNom();
    }

    /**
     * On redéfinit la méthode initialisationPartie pour le puissance 4
     * on associe à un joueur un couleur de jeton
     * on demande si on veut jouer le jeu avec une contrainte ou non
     */
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

    /**
     * On redéfinit la méthode affichageDebutTour pour le puissance 4
     * @param joueur est le joueur courant
     */
    @Override
    protected void affichageDebutTour(Joueur joueur) {
        ihm.afficherEtat(grille.toString());
        ihm.afficherTour(jetonDuJoueur.get(joueur).toString()+" "+joueur.getNom());
    }

    /**
     * On redéfinit la méthode traiterCoup pour le puissance 4
     * @param joueur est le joueur qui vient de jouer
     * @throws CoupInvalideException si le coup est invalide
     */
    @Override
    protected void traiterCoup (Joueur joueur)  throws CoupInvalideException {
        if(nbRotation.get(joueur)>=0){
            int choix=((IhmPuissance)ihm).choixMouvement(nbRotation.get(joueur));
            if(choix==1/*avec*/){
                traiterCoupavecRotation(joueur);
                return;
            }
        }
        List<Integer> coup=(ihm.demanderCoup());
            grille.gererCoup(coup.get(0),jetonDuJoueur.get(joueur));
    }

    /**
     * On appelle cette méthode si le joueur souhaite faire pivoter la grille dans le sens de rotation qu'il souhaite
     * @param joueur est le joueur qui va faire tourner la grille
     * @throws CoupInvalideException si le nombre de rotation du joueur est epuisé
     */
    private void traiterCoupavecRotation (Joueur joueur) throws CoupInvalideException {
        if(nbRotation.get(joueur)<=0){
            throw new CoupInvalideException("Vous ne pouvez plus faire une rotation");
        }
        else{
            int nbRot=nbRotation.get(joueur);
            nbRotation.put(joueur,--nbRot);
            int sens=((IhmPuissance)ihm).sensRotation();
            grille.gererRotation(sens);
        }
    }

    /**
     * On redéfinit la méthode partie pour le puissance 4
     */
    @Override
    protected void partie(){
        Set<Jeton> lesJetonsGagnants;
        do{
            tour();
            lesJetonsGagnants=grille.partieTerminee();
        }
        while(lesJetonsGagnants.isEmpty() && !grille.grilleRemplie());
        ihm.afficherEtat(grille.toString());
        if (lesJetonsGagnants.size()==1)
        {
            Object[] jetonGagnant=lesJetonsGagnants.toArray();
            ihm.afficherGagnant(gagnantPartie((Jeton)jetonGagnant[0]).getNom());
        }
        else
            ((IhmPuissance)ihm).afficherPartieNulle();
    }

    /**
     * Teste si le jeton du joueur passé en paramètre a permis de gagner la partie ou non
     * @param j correspond au jeton du joueur
     * @param <Jeton> correspond a un type Jeton
     * @return le joueur qui a gagné ou null si la partie n'est pas fini ou n'a pas de gagnant
     */
   /* @Override
    protected <Jeton> Joueur gagnantPartie(Jeton ... j) {
        for (Joueur joueur : jetonDuJoueur.keySet()  ){
            if (j.equals(jetonDuJoueur.get(joueur))){
                return joueur;
            }
        }
        return null;
    }*/
    /**
     * @param j jeton gagnant
     * @return le joueur qui a le jeton passé en paramètre
     */
    private Joueur gagnantPartie(Jeton j) {
        for (Joueur joueur : jetonDuJoueur.keySet()  ){
            if (j.equals(jetonDuJoueur.get(joueur))){
                joueur.gagnePartie();
                return joueur;
            }
        }
        return null;
    }

}
