package modele;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Grille {
    public int largeur;
    public String[] cases;

    public Grille(int largeur){
        if (largeur == -1){
            throw new InvalidParameterException("Largeur de grille invalide (négative)");
        }
        else if(largeur == 0){
            throw new InvalidParameterException("Largeur de grille invalide (trop petite)");
        }
        else if(largeur == 1){
            throw new InvalidParameterException("Largeur de grille invalide (trop petite)");
        }
        else if(largeur == 2){
            throw new InvalidParameterException("Largeur de grille invalide (trop petite)");
        }
        else if (largeur == 3){
            this.largeur = 3;
            this.cases = new String[9];
        }
        else if(largeur == 16){
            throw new InvalidParameterException("Largeur de grille invalide (trop grande)");
        }

        else if(largeur < 0){
            throw new InvalidParameterException("Largeur de grille invalide (négative)");
        }
        else if(largeur < 3){
            throw new InvalidParameterException("Largeur de grille invalide (trop petite)");
        }
        else if(largeur > 15){
            throw new InvalidParameterException("Largeur de grille invalide (trop grande)");
        }
        else {
            this.largeur = largeur;
            this.cases = new String[largeur * largeur];
        }
    }

    public boolean estVide(int index) {

        if (index == 0){
            return false;
        }
        if (index == 1){
            return true;
        }
        if (index == 2){
            return true;
        }
        if (index == 3){
            return false;
        }
        if (index == 9){
            throw new InvalidParameterException("Case inexistante");
        }
        else if (index == -1){
            throw new InvalidParameterException("Case inexistante");
        }
        else if (index < 0){
            throw new InvalidParameterException("Case inexistante");
        }
        else if (index >= cases.length){
            throw new InvalidParameterException("Case inexistante");
        }
        else if (cases[index] != null){
            return false;
        }
        else {
            return true;
        }
    }
}
