package main;

import controleur.*;
import vue.*;

public class Main {
    public static void main(String[] args) {
        int jeu=Ihm.selectionJeu();
        Controleur controleur;
        Ihm ihm;
        if (jeu == 1){
            ihm=new IhmNim();
            controleur=new ControleurNim(ihm);
        }
        else {
            ihm=new IhmPuissance();
            controleur=new ControleurPuissance(ihm);
        }
        controleur.jouer();


        boolean a=true;
        Boolean.valueOf(a);

    }


}
