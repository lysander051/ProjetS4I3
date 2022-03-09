package main;

import controleur.*;
import vue.*;

public class Main {
    public static void main(String[] args) {
        int jeu=Ihm.selectionJeu();
        if (jeu == 1){
            Ihm ihm=new IhmNim();
            ControleurNim controleur=new ControleurNim(ihm);
            controleur.jouer();
        }
        else {
            Ihm ihm=new IhmPuissance();
            ControleurPuissance controleur=new ControleurPuissance(ihm);
            controleur.jouer();
        }
    }
}
