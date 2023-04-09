import modele.Grille;
import modele.Joueur;
import modele.Partie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PartieTest {

    /*
    // Nb max joueurs = 10
        public Partie(List<Joueur> listeJoueurs, Grille grille)
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
    //(Liste(Joueur1, ..., JoueurMAX), Grille) -> OK

    POUR nbJoueurs (0,MAX)
        SI nbJoueurs < 2 ALORS erreur
    SINON ALORS Ok
     */

    @Test
    void testConstructeurPartie(){

        Grille grille = new Grille(1);
        List<Joueur> listeJoueurs = new LinkedList<>();
        Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs, grille), "Invalid list<Joueur> length (too small)");

        Grille grille2 = new Grille(1);
        List<Joueur> listeJoueurs2 = new LinkedList<>();
        listeJoueurs2.add(new Joueur(0));
        Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs2, grille2), "Invalid list<Joueur> length (too small)");

        Grille grille3 = new Grille(1);
        List<Joueur> listeJoueurs3 = new LinkedList<>();
        listeJoueurs3.add(new Joueur(0));
        listeJoueurs3.add(new Joueur(0));
        Assertions.assertDoesNotThrow(() -> new Partie(listeJoueurs3, grille3), "Valid list<Joueur> length");

        int nbJoueurs = (int) (Math.random() * 15);
        Grille grille5 = new Grille(1);
        List<Joueur> listeJoueurs5 = new LinkedList<>();
        for (int i = 0; i < nbJoueurs; i ++){
            listeJoueurs5.add(new Joueur(0));
        }
        if (listeJoueurs5.size() < 2){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs5, grille5), "Invalid list<Joueur> length (too small)");
        }
        else if (listeJoueurs5.size() > 10){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs5, grille5), "Invalid list<Joueur> length (too large)");
        }
        else{
            Assertions.assertDoesNotThrow(() -> new Partie(listeJoueurs5, grille5), "Valid list<Joueur> length");
        }
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------------*/

    /*
        public void ajouterPion(int case)
    // Plateau de 3 X 3 cases
    // Case 1,2 vides
    (0) -> Erreur (occupée)
    (1) -> Ok
    (2) -> Ok
    (3) -> Erreur (occupée)
    (9) -> Erreur (pas existante)
    (-1) -> Erreur (pas existante)

    SI numCase < 0 ALORS Erreur
    SINON SI numCase > grille.largeur ** 2 - 1 ALORS Erreur
    SINON SI grille.cases[numCase].estLibre() == false ALORS Erreur
    SINON Ok
     */

    @Test
    public void testAjouterPionCasErreur1(){

        List<Joueur> joueurs = new LinkedList<>();
        joueurs.add(new Joueur(1));
        joueurs.add(new Joueur(2));
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = 0;
        grille.cases[0] = "O";
        Assertions.assertThrows(UnsupportedOperationException.class, () -> partie.ajouterPion(0), "Place already taken");
    }

    @Test
    public void testAjouterPionCasNominal1(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueur1.symbole = "X";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = 0;
        partie.ajouterPion(1);
        Assertions.assertEquals(joueurs.get(partie.indexJoueurCourant).symbole, grille.cases[1]);
    }

    @Test
    public void testAjouterPionCasNominal2(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueur1.symbole = "X";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = 0;
        grille.cases[3] = "O";
        partie.ajouterPion(2);
        Assertions.assertEquals(joueurs.get(partie.indexJoueurCourant).symbole, grille.cases[2]);
    }

    @Test
    public void testAjouterPionCasNominal3(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        Joueur joueur2 = new Joueur(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueur1.symbole = "X";
        joueur2.symbole = "O";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        grille.cases[3] = "X";
        partie.indexJoueurCourant = 1;
        partie.ajouterPion(2);
        Assertions.assertEquals(joueurs.get(partie.indexJoueurCourant).symbole, grille.cases[2]);
    }

    @Test
    public void testAjouterPionCasErreur2(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueur1.symbole = "X";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = 0;
        grille.cases[3] = "O";
        Assertions.assertThrows(UnsupportedOperationException.class, () -> partie.ajouterPion(3), "Place already taken");
    }

    @Test
    public void testAjouterPionCasErreur3(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueur1.symbole = "X";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = 0;
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> partie.ajouterPion(9), "Index out of bounds (too large)");
    }

    @Test
    public void testAjouterPionCasErreur4(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueur1.symbole = "X";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = 0;
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> partie.ajouterPion(-1), "Index out of bounds (negative)");
    }

    @Test
    public void testAjouterPionGraine(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        Joueur joueur2 = new Joueur(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueur1.symbole = "X";
        joueur2.symbole = "M";
        Grille grille = new Grille(3);
        Partie partie = new Partie(joueurs, grille);
        int joueurCourant = (int) (Math.random() * 2);
        partie.indexJoueurCourant = joueurCourant;
        grille.cases[1] = "O";
        grille.cases[3] = "O";
        int numCase = (-(grille.largeur + 5) + (int) (Math.random() * (grille.largeur + 5)));
        if (numCase < 0){
            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> partie.ajouterPion(numCase), "Index out of bounds (negative)");
        }
        else if (numCase > ((grille.largeur * grille.largeur) - 1)){
            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> partie.ajouterPion(numCase), "Index out of bounds (too large)");
        }
        else if (grille.cases[numCase] != null){
            Assertions.assertThrows(UnsupportedOperationException.class, () -> partie.ajouterPion(numCase), "Place already taken");
        }
        else{
            partie.ajouterPion(numCase);
            Assertions.assertEquals(joueurs.get(partie.indexJoueurCourant).symbole, grille.cases[numCase]);
        }
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------------*/

    /*
        // Joueurs = {Joueur1, Joueur2, Joueur3}
        // JoueurCourant = Joueur1
        () -> Joueur1
        // JoueurCourant = Joueur2
        () -> Joueur2
        // JoueurCourant = Joueur3
        () -> Joueur3
        // JoueurCourant = null
        () -> Erreur
        SI JoueurCourant == null ALORS Erreur
        SINON JoueurCourant
     */

    @Test
    public void testGetJoueurCourantCasNominaux() {

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        Joueur joueur2 = new Joueur(2);
        joueurs.add(joueur2);
        Joueur joueur3 = new Joueur(3);
        joueurs.add(joueur3);
        Grille grille = new Grille(1);
        Partie partie = new Partie(joueurs, grille);
        Assertions.assertEquals(0, partie.getJoueurCourant());
        partie.indexJoueurCourant = joueurs.indexOf(joueur1);
        Assertions.assertEquals(joueurs.indexOf(joueur1), partie.getJoueurCourant());
        partie.indexJoueurCourant = joueurs.indexOf(joueur2);
        Assertions.assertEquals(joueurs.indexOf(joueur2), partie.getJoueurCourant());
        partie.indexJoueurCourant = joueurs.indexOf(joueur3);
        Assertions.assertEquals(joueurs.indexOf(joueur3), partie.getJoueurCourant());
    }
    @Test
    public void testGetJoueurCourantGraine(){

        int nbJoueurs = 2 + (int) (Math.random() * (9 - 2));
        int indexJoueurCourant = (int) (Math.random() * nbJoueurs );
        Grille grille = new Grille(1);
        List<Joueur> joueurs = new LinkedList<>();
        for (int i = 0; i < nbJoueurs; i ++){
            joueurs.add(new Joueur(i));
        }
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = indexJoueurCourant;
        Assertions.assertEquals(indexJoueurCourant, partie.getJoueurCourant());
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------------*/


    /*
    // Joueurs = {Joueur1, Joueur2, Joueur3}
    // JoueurCourant = Joueur1
    () -> JoueurCourant = Joueur2
    // JoueurCourant = Joueur2
    () -> JoueurCourant = Joueur3
    // JoueurCourant = Joueur3
    () -> JoueurCourant = Joueur1
    // JoueurCourant = null
    () -> JoueurCourant = Joueur1
    SI JoueurCourant == null || JoueurCourant == DernierJoueurListe ALORS JoueurCourant = PremierJoueurListe
    SINON JoueurCourant = JoueurSuivant
     */

    @Test
    public void testChangerJoueurCourantCasNominal1(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        Joueur joueur2 = new Joueur(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(new Joueur(3));
        Grille grille = new Grille(1);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = joueurs.indexOf(joueur1);
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueurs.indexOf(joueur2), partie.indexJoueurCourant);
    }

    @Test
    public void testChangerJoueurCourantCasNominal2(){

        List<Joueur> joueurs = new LinkedList<>();
        joueurs.add(new Joueur(1));
        Joueur joueur2 = new Joueur(2);
        Joueur joueur3 = new Joueur(3);
        joueurs.add(joueur2);
        joueurs.add(joueur3);
        Grille grille = new Grille(1);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = joueurs.indexOf(joueur2);
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueurs.indexOf(joueur3), partie.indexJoueurCourant);
    }

    @Test
    public void testChangerJoueurCourantCasNominal3(){

        List<Joueur> joueurs = new LinkedList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        Joueur joueur3 = new Joueur(3);
        joueurs.add(joueur3);
        Grille grille = new Grille(1);
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = joueurs.indexOf(joueur3);
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueurs.indexOf(joueur1), partie.indexJoueurCourant);
    }

    @Test
    public void testChangerJoueurCourantGraine(){

        int nbJoueurs = 3 + (int) (Math.random() * (9 - 2));
        int indexJoueurCourant = (int) (Math.random() * nbJoueurs);
        Grille grille = new Grille(1);
        List<Joueur> joueurs = new LinkedList<>();
        for (int i = 0; i < nbJoueurs; i ++){
            joueurs.add(new Joueur(i));
        }
        Partie partie = new Partie(joueurs, grille);
        partie.indexJoueurCourant = indexJoueurCourant;
        if (partie.indexJoueurCourant == joueurs.size() - 1){
            partie.changerJoueurCourant();
            Assertions.assertEquals(0, partie.indexJoueurCourant);
        }
        else{
            partie.changerJoueurCourant();
            Assertions.assertEquals(indexJoueurCourant + 1, partie.indexJoueurCourant);
        }
    }
}
