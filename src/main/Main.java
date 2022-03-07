package main;

import controleur.*;
import vue.*;

public class Main {
    public static void main(String[] args) {
        Ihm ihm=new IhmNim();
        ControleurNim controleur=new ControleurNim(ihm);
        controleur.jouer();
    }
}
