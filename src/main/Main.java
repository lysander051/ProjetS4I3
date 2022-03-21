package main;

import controleur.*;
import vue.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int jeu=Ihm.selectionJeu();
        int nbJoueur = Ihm.ordiOuJoueur() ;
        Controleur controleur;
        Ihm ihm;
        if (jeu == 1){
            if (nbJoueur == 2) {
                ihm = new IhmNim();
                controleur = new ControleurNim(ihm);
            }
            else { //appeler d'autres ihm et contrôleur spécifique pour le jeu contre l'IA
                ihm = new IhmNim();
                controleur = new ControleurNim(ihm);
            }
        }
        else {
            if (nbJoueur == 2){
                ihm=new IhmPuissance();
                controleur=new ControleurPuissance(ihm);
            }
            else{ //appeler d'autres ihm et contrôleur spécifique pour le jeu contre l'IA
                ihm=new IhmPuissance();
                controleur=new ControleurPuissance(ihm);
            }
        }
        controleur.jouer();
    }
}
