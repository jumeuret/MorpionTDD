import modele.Grille;
import modele.Joueur;
import modele.Partie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
        List<Joueur> listeJoueurs = new ArrayList<Joueur>();
        Assertions.assertThrows(InvalidParameterException.class, (Executable) new Partie(listeJoueurs, grille), "Invalid list<Joueur> length");
        System.gc();

        Grille grille2 = new Grille();
        List<Joueur> listeJoueurs2 = new ArrayList<Joueur>();
        listeJoueurs2.add(new Joueur());
        Assertions.assertThrows(InvalidParameterException.class, (Executable) new Partie(listeJoueurs2, grille2), "Invalid list<Joueur> length");
        System.gc();

        Grille grille3 = new Grille();
        List<Joueur> listeJoueurs3 = new ArrayList<Joueur>();
        for (int i = 0; i < 2; i ++){
            listeJoueurs3.add(new Joueur());
        }
        Assertions.assertThrows(InvalidParameterException.class, (Executable) new Partie(listeJoueurs3, grille3), "Invalid list<Joueur> length");
        System.gc();

        Grille grille4 = new Grille();
        List<Joueur> listeJoueurs4 = new ArrayList<Joueur>();
        for (int i = 0; i < Integer.MAX_VALUE; i ++){
            listeJoueurs4.add(new Joueur());
        }
        Assertions.assertThrows(InvalidParameterException.class, (Executable) new Partie(listeJoueurs4, grille4), "Invalid list<Joueur> length");
        System.gc();

        int random = (int) (Math.random() * (Integer.MAX_VALUE));
        Grille grille5 = new Grille();
        List<Joueur> listeJoueurs5 = new ArrayList<Joueur>();
        for (int i = 0; i < random; i ++){
            listeJoueurs5.add(new Joueur());
        }
        if (listeJoueurs5.size() < 2){
            Assertions.assertThrows(InvalidParameterException.class, (Executable) new Partie(listeJoueurs5, grille5), "Invalid list<Joueur> length");
        }
        else{
            Assertions.assertDoesNotThrow((Executable) new Partie(listeJoueurs5, grille5));
        }
        System.gc();
    }

    @Test
    public void testAjouterPion(){


    }

}
