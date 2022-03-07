package vue;

import java.util.List;
import java.util.Scanner;

public class IhmPuissance extends Ihm{

    public int demanderCoup(String nom, String couleur){
        scanner = new Scanner(System.in);
        int nb = -1;
        String joueur = couleur + " " + nom+ ": à vous de jouer";
        String msg="Entrer un numéro de grille entre 1 et 7: ";
        System.out.println(joueur);
        System.out.print(msg);
        while(scanner.hasNextLine()){
            String ligne = scanner.nextLine();
            Scanner scLoc = new Scanner(ligne);
            if(!ligne.contains(" ") && scLoc.hasNextInt()){
                nb=scLoc.nextInt();
                break;
            }
            System.out.println(spacing);
            System.out.println("Erreur: le numéro de grille doit être un entier entre 1 et 7 ");
            System.out.println(joueur);
            System.out.print(msg);
        }
        return nb;
    }

    public void afficherPartieNulle(){
        System.out.println(spacing);
        System.out.println("La partie est nulle");
    }
}
