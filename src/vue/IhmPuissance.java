package vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IhmPuissance extends Ihm{

    public boolean demanderAjoutContrainte(){
        scanner =new Scanner(System.in);
        boolean rep=false;
        String msg="Voulez vous ajouter contrainte? (O)ui ou (N)on : ";
        System.out.print(msg);
        while(scanner.hasNextLine()){
            String valeur= scanner.nextLine();
            if(valeur.equals("O") || valeur.equals("o")){
                rep= true;
                break;
            }
            if(valeur.equals("N") || valeur.equals("n")){
                break;
            }
            System.out.println("Erreur: Entrer O ou N");
            System.out.print(msg);
        }
        return rep;
    }

    public List<Integer> demanderCoup(){
        scanner = new Scanner(System.in);
        List<Integer> l=new ArrayList<>();
        int nb = -1;
      //  String joueur = couleur + " " + nom+ ": à vous de jouer";
        String msg="Entrer un numéro de grille entre 1 et 7: ";
        //System.out.println(joueur);
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
           /* System.out.println(joueur);
            System.out.print(msg);*/
        }
        l.add(nb);
        return l;
    }

    public void afficherPartieNulle(){
        System.out.println(spacing);
        System.out.println("La partie est nulle");
    }

    public int choixMouvement() {
        scanner = new Scanner(System.in);
        int nb = -1;
        String msg = "Entrer 0 sans mvt 1 avec: ";
        System.out.print(msg);
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            Scanner scLoc = new Scanner(ligne);
            if (!ligne.contains(" ") && scLoc.hasNextInt()) {
                nb = scLoc.nextInt();
                if (nb == 0 || nb == 1) {
                    System.out.println("--------------------------------------------------------");
                    break;
                }
            }
            System.out.println("Erreur: 1 ou 2 \n ");
            System.out.print(msg);
        }
        return nb;
    }

    public int sensRotation() {
        scanner = new Scanner(System.in);
        int nb = -1;
        String msg = "Entrer sens gauche 0 droite 1: ";
        System.out.print(msg);
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            Scanner scLoc = new Scanner(ligne);
            if (!ligne.contains(" ") && scLoc.hasNextInt()) {
                nb = scLoc.nextInt();
                if (nb == 0 || nb == 1) {
                    System.out.println("--------------------------------------------------------");
                    break;
                }
            }
            System.out.println("Erreur: 0 droite 1 gauche \n ");
            System.out.print(msg);
        }
        return nb;
    }

}
