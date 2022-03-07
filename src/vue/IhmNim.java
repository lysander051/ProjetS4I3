package vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IhmNim extends Ihm{

    public List<Integer> demanderCoup() {
        scanner =new Scanner(System.in);
        String msg="\n Entrer votre coup : ";
        List<Integer> l=new ArrayList<>();
        System.out.print(msg);
        while (scanner.hasNextLine()) {
            Scanner scInt = new Scanner(scanner.nextLine());
            while (scInt.hasNext()) {
                if (scInt.hasNextInt()) {
                    l.add(scInt.nextInt());
                }
                else {
                    scInt.next();
                    l.clear();
                    break;
                }
            }
            if(l.size()!=2){
                l.clear();
                System.out.println("Il faut entrer 2 entiers, rejouez");
                System.out.print(msg);
            }
            else {
                break;
            }
        }
        return l;
    }

    public int demanderNbTas(){
        scanner = new Scanner(System.in);
        int nb = -1;
        String msg="Entrer un nombre de tas positif: ";
        System.out.print(msg);
        while(scanner.hasNextLine()){
            String ligne = scanner.nextLine();
            Scanner scLoc = new Scanner(ligne);
            if(!ligne.contains(" ") && scLoc.hasNextInt()){
                nb=scLoc.nextInt();
                if(nb>=1){
                    System.out.println("--------------------------------------------------------");
                    break;
                }
            }
            System.out.println("Erreur: le tas doit être un entier positif supérieur à 1 \n ");
            System.out.print(msg);
        }
        return nb;
    }

    public void afficherCoup(String nom){
        System.out.println(nom+": à vous de jouer un coup sous la forme 'm n' " +
                "\n où m est le tas choisi et n le nombre d'allumettes " +
                "\n à retirer dans ce tas");
    }
}
