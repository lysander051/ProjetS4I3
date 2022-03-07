package main;

import controleur.*;
import vue.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(Ihm.selectionJeu());
        Ihm ihm=new IhmPuissance();
        ControleurPuissance controleur=new ControleurPuissance(ihm);
        controleur.jouer();
    }
}
