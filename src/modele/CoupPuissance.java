package modele;

public class CoupPuissance extends Coup{
    private int colonne;
    private Jeton jeton;

    public CoupPuissance(int colonne, Jeton jeton) {
        this.colonne = colonne;
        this.jeton = jeton;
    }

    public int getColonne() {
        return colonne;
    }

    public Jeton getJeton() {
        return jeton;
    }
}
