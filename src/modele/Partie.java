package modele;

import java.security.InvalidParameterException;
import java.util.List;

public class Partie {
    public Joueur joueurCourant;
    public List<Joueur> listeJoueurs;

    public Partie(List<Joueur> listeJoueurs, Grille grille) {

        if (listeJoueurs.size() < 2)
            throw new InvalidParameterException("Invalid list<Joueur> length");
        this.listeJoueurs = listeJoueurs;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void changerJoueurCourant() {
        if (joueurCourant == null) {
            joueurCourant = listeJoueurs.get(0);
        }
        else if (listeJoueurs.indexOf(joueurCourant) == listeJoueurs.size() - 1) {
            joueurCourant = listeJoueurs.get(0);
        }
        else {
            joueurCourant = listeJoueurs.get(listeJoueurs.indexOf(joueurCourant) + 1);
        }
    }

    public void ajouterPion(int index) {
    }
}
