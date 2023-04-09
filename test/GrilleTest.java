import modele.Grille;
import modele.Partie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GrilleTest {


    /*
    (-1) -> Erreur
    (0) -> Erreur
    (1) -> Erreur
    (2) -> Erreur
    (3) -> Ok
    (16) -> Erreur

    SI largeur < -1 ALORS Erreur
    SINON SI largeur < 3 ALORS Erreur
    SINON SI largeur > 15 ALORS Erreur
    SINON Ok
     */

    @ParameterizedTest
    @ValueSource(ints = {-1,0,1,2,3,16})
    public void testConstructeurGrille(int largeur){

        if (largeur == -1){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (négative)");
        }
        else if (largeur == 0){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (trop petite)");
        }
        else if (largeur == 1){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (trop petite)");
        }
        else if (largeur == 2){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (trop petite)");
        }
        else if (largeur == 3){
            Grille grille = new Grille(3);
            Assertions.assertEquals(3,grille.largeur);
            Assertions.assertEquals(9,grille.cases.length);
        }
        else if (largeur == 16){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (trop grande)");
        }
    }

    @Test
    public void testConstructeurGrilleGraine(){

        int largeur = (-10) + (int) (Math.random() * (20-(-10)));
        if (largeur < 0){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (négative)");
        }
        else if (largeur < 3){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (trop petite)");
        }
        else if (largeur > 15){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Grille(largeur), "Largeur de grille invalide (trop grande)");
        }
        else {
            Grille grille = new Grille(largeur);
            Assertions.assertEquals(largeur,grille.largeur);
            Assertions.assertEquals(largeur * largeur,grille.cases.length);
        }
    }

    /*
    // Plateau de 3 X 3 cases
    // Case 1,2 vides
    (0) -> False
    (1) -> True
    (2) -> True
    (3) -> False
    (9) -> Erreur (pas existante)
    (-1) -> Erreur (pas existante)

    SI index < 0 ALORS False
    SINON SI index >= grille.cases.lenght ALORS Erreur
    SINON SI grille.cases[index] != null ALORS False
    SINON True
    */

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,9,-1})
    public void testEstCaseLibre(int index) {

        Grille grille = new Grille(3);
        if (index == 0){
            Assertions.assertFalse(grille.estVide(0));
        }
        else if (index == 1){
            Assertions.assertTrue(grille.estVide(1));
        }
        else if (index == 2){
            Assertions.assertTrue(grille.estVide(2));
        }
        else if (index == 3){
            Assertions.assertFalse(grille.estVide(3));
        }
        else if (index == 9){
            Assertions.assertThrows(InvalidParameterException.class, () -> grille.estVide(index), "Case inexistante");
        }
        else if (index == -1){
            Assertions.assertThrows(InvalidParameterException.class, () -> grille.estVide(index), "Case inexistante");
        }
    }

    @Test
    public void testEstCaseLibreGraine(){

        int largeur = (int) (Math.random() * 16);
        int index = (-10) + (int) (Math.random() * (20-(-10)));
        int nbCasesOccupees = (int) (Math.random() * 16);
        String[] casesOccupees = new String[largeur * largeur];
        for (int cpt = 0; cpt < nbCasesOccupees; cpt++){
            casesOccupees[(int) (Math.random() * 16)] = "X";
        }
        Grille grille = new Grille(largeur);
        grille.cases = casesOccupees;

        if (index < 0){
            Assertions.assertThrows(InvalidParameterException.class, () -> grille.estVide(index), "Case inexistante");
        }
        else if (index >= grille.cases.length){
            Assertions.assertThrows(InvalidParameterException.class, () -> grille.estVide(index), "Case inexistante");
        }
        else if (grille.cases[index] != null){
            Assertions.assertFalse(grille.estVide(index));
        }
        else {
            Assertions.assertTrue(grille.estVide(index));
        }
    }
}