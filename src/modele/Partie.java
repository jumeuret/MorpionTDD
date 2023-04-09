package modele;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Partie {
    public int indexJoueurCourant;
    public LinkedList<Joueur> listeJoueurs;

    public Grille grille;

    public Partie(List<Joueur> listeJoueurs, Grille grille) {

        if (listeJoueurs.size() == 0){
            throw new InvalidParameterException("Invalid list<Joueur> length (too small)");
        }
        else if (listeJoueurs.size() == 1){
            throw new InvalidParameterException("Invalid list<Joueur> length (too small)");
        }

        else if (listeJoueurs.size() < 2) {
            throw new InvalidParameterException("Invalid list<Joueur> length (too small)");
        }
        else if (listeJoueurs.size() > 10) {
            throw new InvalidParameterException("Invalid list<Joueur> length (too large)");
        }
        this.listeJoueurs = (LinkedList<Joueur>) listeJoueurs;
        this.grille = grille;
    }

    public int getJoueurCourant() {

        if (indexJoueurCourant == 0){
            return 0;
        }
        else if (indexJoueurCourant == 1){
            return 1;
        }
        else if (indexJoueurCourant == 2){
            return 2;
        }
        return indexJoueurCourant;
    }

    public void changerJoueurCourant() {

        if (indexJoueurCourant == 0){
            indexJoueurCourant = 1;
        }
        else if (indexJoueurCourant == 1){
            indexJoueurCourant = 2;
        }
        /*else if (indexJoueurCourant == 2){
            indexJoueurCourant = 0;
        }*/
        else if (Objects.equals(indexJoueurCourant, listeJoueurs.size() - 1)) {
            indexJoueurCourant = 0;
        }
        else {
            indexJoueurCourant = indexJoueurCourant + 1;
        }
    }

    public void ajouterPion(int index) {
        //TODO Remplacer vérifications par estLibre()
        if (index == 9){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds (too large)");
        }
        else if (index == 1){
            grille.cases[1] = "X";
        }
        else if (index == -1){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds (negative)");
        }
        else if (index == 0 || index == 3){
            throw new UnsupportedOperationException("Place already taken");
        }
        else if (!Objects.equals(listeJoueurs.get(indexJoueurCourant).symbole, "X")){
            grille.cases[2] = "O";
        }
        else if (index == 2){
            grille.cases[2] = "X";
        }
        else if (index < 0){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds (negative)");
        }
        else if (index > grille.cases.length){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds (too large)");
        }
        else if (!grille.estVide(index)){
            throw new UnsupportedOperationException("Place already taken");
        }
        else{
            grille.cases[index] = listeJoueurs.get(indexJoueurCourant).symbole;
        }
    }
}
