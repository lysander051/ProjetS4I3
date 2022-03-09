package vue;

import modele.Jeton;

import java.util.List;
import java.util.Scanner;

public abstract class Ihm {
    protected final String spacing = "--------------------------------------------------------";
    static protected Scanner scanner;

    public static int selectionJeu (){
        scanner = new Scanner(System.in);
        int nb=0;
        System.out.println("1 pour jeu de Nim");
        System.out.println("2 pour jeu de puissance 4");
        System.out.print("Votre choix:");
        while(scanner.hasNextLine()){
            String ligne = scanner.nextLine();
            Scanner scLoc = new Scanner(ligne);
            if(!ligne.contains(" ") && scLoc.hasNextInt()){
                nb=scLoc.nextInt();
                if (1==nb || 2==nb)
                    break;
            }
            System.out.println("Erreur: le numéro doit être 1 ou 2");
            System.out.print("Votre choix:");
        }
        return nb;
    }

    /**
     * Demande le nom du joueur
     * @param num correspond au numéro du joueur (joueur 1 ou 2)
     * @return le nom du joueur
     */
    public String demanderNom(int num){
        scanner = new Scanner(System.in);
        String nom="";
        String msg="Entrer le nom du joueur "+num+":";
        System.out.print(msg);
        while(scanner.hasNextLine()){
            nom= scanner.nextLine();
            if(!nom.equals("")){
                break;
            }
            else{
                System.out.println("Erreur: le joueur "+num+" doit avoir un nom");
                System.out.print(msg);
            }
        }
        return nom;
    }

    /**
     * Affiche l'état de la grille au moment de l'appel
     * @param support correspond a la grille au moment de l'appel
     */
    public void afficherEtat(String support){
        System.out.println(spacing);
        System.out.println(support);
    }

    /**
     * Affiche un message en cas de coup invalide
     * @param msg correspond au message affiché au joueur
     */
    public void afficherErreurCoup(String msg){
        System.out.println(spacing);
        System.out.println("Erreur: "+msg+", rejouez");
    }
    /**
     * Affiche le nom du gagnant de la partie
     * @param nom correspond au nom du gagnant
     */
    public void afficherGagnant(String nom){
        System.out.println(spacing);
        System.out.println("Félicitation "+nom+" ,vous avez gagné la partie");
    }

    /**
     * Demande aux joueurs s'ils souhaitent refaire une partie ou non
     * @return true s'ils souhaitent rejouer, false sinon
     */
    public boolean demanderRejouer(){
        scanner =new Scanner(System.in);
        boolean rep=false;
        String msg="Voulez vous rejouer? (O)ui ou (N)on : ";
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

    /**
     * Affiche le nom du gagnant du jeu
     * @param nom1 correspond au nom du joueur 1
     * @param nom2 correspond au nom du joueur 2
     * @param partiegagne1 correspond au nombre de parties gagnées par le joueur 1
     * @param partiegagne2 correspond au nombre de parties gagnées par le joueur 2
     * @param gagnant correspond au nom du gagnant de la partie ou ex-aequo en cas d'égalité
     */
    public void afficherGagnantJeu(String nom1,String nom2,int partiegagne1,int partiegagne2,String gagnant){
        System.out.println(spacing);
        String s="Nombre de victoire : \n "+ nom1+" : "+partiegagne1+"\n "+ nom2+" : "+partiegagne2+"\n";
        if(gagnant.equals("ex-aequo")){
            s+="Vous êtes à "+gagnant;
        }
        else{
            s+="Félicitations "+gagnant+", vous avez gagné";
        }
        System.out.println(s);
    }
}
