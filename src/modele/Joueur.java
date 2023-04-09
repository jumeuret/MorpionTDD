package modele;

import java.security.InvalidParameterException;

public class Joueur {

    public int id;
    public String symbole;

    public Joueur(int id) {
        if (id < 0){
            throw new InvalidParameterException("Id négatif");
        }
        else {
            this.id = id;
        }
    }
}
