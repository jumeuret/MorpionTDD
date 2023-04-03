import modele.Grille;
import modele.Joueur;
import modele.Partie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class PartieTest {

    /*
    //(Grille) -> erreur
    //(Grille, Grille) -> erreur
    //(Liste(Joueur1, Joueur2), Liste(Joueur1, Joueur2) -> erreur
    //(Liste(vide) -> erreur
    //(Liste(Joueur1) -> erreur
    //(Liste(Joueur1, Joueur2)) -> erreur
    (Liste(vide),Grille) -> erreur
    (Liste(Joueur1),Grille) -> erreur
    (Liste(Joueur1, Joueur2),Grille) -> OK
    //(Liste(Joueur1, Joueur2),Grille, Grille) -> erreur
    //(Liste(Joueur1, Joueur2),Grille, Liste(Joueur1, Joueur2)) -> erreur
    //(Liste(Joueur1, ..., JoueurMAX)) -> erreur
    (Liste(Joueur1, ..., JoueurMAX), Grille) -> OK

    POUR nbJoueurs (0,MAX)
        SI nbJoueurs < 2 ALORS erreur
    SINON ALORS Ok
     */

    @Test
    void testConstructeurPartie(){
        Grille grille = new Grille();
        List<Joueur> listeJoueurs = new ArrayList<Joueur>;
        Assertions.assertThrows(InvalidParameterException.class, new Partie(listeJoueurs, grille));
        System.gc();

        Grille grille = new Grille();
        List<Joueur> listeJoueurs = new ArrayList<Joueur>;
        listeJoueurs.Add(new Joueur());
        Assertions.assertThrows(InvalidParameterException.class, new Partie(listeJoueurs, grille));
        grille.delete();
        listeJoueurs.delete();

        Grille grille = new Grille();
        List<Joueur> listeJoueurs = new ArrayList<Joueur>;
        for (int i = 0; i < 2; i ++){
            listeJoueurs.Add(new Joueur());
        }
        Assertions.assertThrows(InvalidParameterException.class, new Partie(listeJoueurs, grille));
        grille.delete();
        listeJoueurs.delete();

        Grille grille = new Grille();
        List<Joueur> listeJoueurs = new ArrayList<Joueur>;
        for (int i = 0; i < Integer.MAX_VALUE; i ++){
            listeJoueurs.Add(new Joueur());
        }
        Assertions.assertThrows(InvalidParameterException.class, Partie partie = new Partie(listeJoueurs, grille));
        grille.delete();
        listeJoueurs.delete();

        int random = (Math.random() * (Integer.MAX_VALUE))
        Grille grille = new Grille();
        List<Joueur> listeJoueurs = new ArrayList<Joueur>;
        for (int i = 0; i < random; i ++){
            listeJoueurs.Add(new Joueur());
        }
        if (listeJoueurs.lenght() < 0){
            Assertions.assertThrows(InvalidParameterException.class, Partie partie = new Partie(listeJoueurs, grille));
        }
        else{
            Assertions.assertDoesNotThrow(InvalidParameterException.class, Partie partie = new Partie(listeJoueurs, grille));
        }
        grille.delete();
        listeJoueurs.delete();
    }

}
