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
    //(Liste(Joueur1, ..., JoueurMAX), Grille) -> OK

    POUR nbJoueurs (0,MAX)
        SI nbJoueurs < 2 ALORS erreur
    SINON ALORS Ok
     */

    @Test
    void testConstructeurPartie(){
        Grille grille = new Grille();
        List<Joueur> listeJoueurs = new ArrayList<Joueur>();
        Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs, grille), "Invalid list<Joueur> length");

        Grille grille2 = new Grille();
        List<Joueur> listeJoueurs2 = new ArrayList<Joueur>();
        listeJoueurs2.add(new Joueur(0));
        Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs2, grille2), "Invalid list<Joueur> length");

        Grille grille3 = new Grille();
        List<Joueur> listeJoueurs3 = new ArrayList<Joueur>();
        listeJoueurs3.add(new Joueur(0));
        listeJoueurs3.add(new Joueur(0));
        Assertions.assertDoesNotThrow(() -> new Partie(listeJoueurs3, grille3), "Valid list<Joueur> length");

        int nbJoueurs = (int) (Math.random() * 8);
        Grille grille5 = new Grille();
        List<Joueur> listeJoueurs5 = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurs; i ++){
            listeJoueurs5.add(new Joueur(0));
        }
        if (listeJoueurs5.size() < 2){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Partie(listeJoueurs5, grille5), "Invalid list<Joueur> length");
        }
        else{
            Assertions.assertDoesNotThrow(() -> new Partie(listeJoueurs5, grille5), "Valid list<Joueur> length");
        }
    }

    /*

     */

    @Test
    public void testAjouterPion(){

        Assertions.assertEquals(0,1);
    }
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

        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        Joueur joueur2 = new Joueur(2);
        joueurs.add(joueur2);
        Joueur joueur3 = new Joueur(3);
        joueurs.add(joueur3);
        Grille grille = new Grille();
        Partie partie = new Partie(joueurs, grille);
        partie.joueurCourant = joueur1;
        Assertions.assertEquals(joueur1, partie.getJoueurCourant());
        partie.joueurCourant = joueur2;
        Assertions.assertEquals(joueur2, partie.getJoueurCourant());
        partie.joueurCourant = joueur3;
        Assertions.assertEquals(joueur3, partie.getJoueurCourant());
    }
    @Test
    public void testGetJoueurCourantCasErreur() {

        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueurs.add(new Joueur(3));
        Grille grille = new Grille();
        Partie partie = new Partie(joueurs, grille);
        Assertions.assertThrows(NullPointerException.class, (Executable) partie.getJoueurCourant());
    }
    @Test
    public void testGetJoueurCourantGraine(){

        int nbJoueurs = 2 + (int) (Math.random() * (8 - 2));
        int idJoueurCourant = (int) (Math.random() * 10);
        Grille grille = new Grille();
        List<Joueur> joueurs = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurs; i ++){
            joueurs.add(new Joueur(i));
        }
        Partie partie = new Partie(joueurs, grille);
        if (idJoueurCourant < nbJoueurs){
            for (Joueur joueur: joueurs){
                if (joueur.id == idJoueurCourant){
                    partie.joueurCourant = joueur;
                }
            }
        }
        if (partie.joueurCourant == null){
            Assertions.assertThrows(NullPointerException.class, (Executable) partie.getJoueurCourant());
        }
        else{
            Assertions.assertEquals(idJoueurCourant, (partie.getJoueurCourant()).id);
        }
    }

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

        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur(1);
        Joueur joueur2 = new Joueur(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(new Joueur(3));
        Grille grille = new Grille();
        Partie partie = new Partie(joueurs, grille);
        partie.joueurCourant = joueur1;
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueur2, partie.joueurCourant);
    }

    @Test
    public void testChangerJoueurCourantCasNominal2(){

        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(1));
        Joueur joueur2 = new Joueur(2);
        Joueur joueur3 = new Joueur(3);
        joueurs.add(joueur2);
        joueurs.add(joueur3);
        Grille grille = new Grille();
        Partie partie = new Partie(joueurs, grille);
        partie.joueurCourant = joueur2;
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueur3, partie.joueurCourant);
    }

    @Test
    public void testChangerJoueurCourantCasNominal3(){

        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        Joueur joueur3 = new Joueur(3);
        joueurs.add(joueur3);
        Grille grille = new Grille();
        Partie partie = new Partie(joueurs, grille);
        partie.joueurCourant = joueur3;
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueur1, partie.joueurCourant);
    }
    @Test
    public void testChangerJoueurCourantCasErreur(){

        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur(1);
        joueurs.add(joueur1);
        joueurs.add(new Joueur(2));
        joueurs.add(new Joueur(3));
        Grille grille = new Grille();
        Partie partie = new Partie(joueurs, grille);
        partie.changerJoueurCourant();
        Assertions.assertEquals(joueur1, partie.joueurCourant);
    }

    @Test
    public void testChangerJoueurCourantGraine(){

        int nbJoueurs = 2 + (int) (Math.random() * (8 - 2));
        int idJoueurCourant = (int) (Math.random() * 10);
        Grille grille = new Grille();
        List<Joueur> joueurs = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurs; i ++){
            joueurs.add(new Joueur(i));
        }
        Partie partie = new Partie(joueurs, grille);
        if (idJoueurCourant < nbJoueurs){
            for (Joueur joueur: joueurs){
                if (joueur.id == idJoueurCourant){
                    partie.joueurCourant = joueur;
                }
            }
        }
        if (partie.joueurCourant == null){
            partie.changerJoueurCourant();
            Assertions.assertEquals(joueurs.get(0), partie.joueurCourant);
        }
        else if (joueurs.indexOf(partie.joueurCourant) == joueurs.size() - 1){
            partie.changerJoueurCourant();
            Assertions.assertEquals(joueurs.get(0), partie.joueurCourant);
        }
        else{
            partie.changerJoueurCourant();
            Assertions.assertEquals(joueurs.get(joueurs.indexOf(partie.joueurCourant)+ 1), partie.joueurCourant);

        }
    }
}
