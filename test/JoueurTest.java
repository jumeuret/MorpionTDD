import modele.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

public class JoueurTest {

    /*
    (0) -> id = 0
    (1) -> id = 1;
    (-1) -> Erreur

    SI id < 0 ALORS Erreur
    SINON Ok
     */

    @Test
    public void testConstructeurJoueur(){

        Joueur joueur0 = new Joueur(0);
        Assertions.assertEquals(0, joueur0.id);
        Joueur joueur1 = new Joueur(1);
        Assertions.assertEquals(1, joueur1.id);
        Assertions.assertThrows(InvalidParameterException.class, () -> new Joueur(-1), "Id négatif");
    }
    @Test
    public void testConstructeurJoueurGraine(){

        int id = (Integer.MIN_VALUE / 2) + (int) (Math.random() * (Integer.MAX_VALUE / 2 - Integer.MIN_VALUE / 2));
        if (id < 0){
            Assertions.assertThrows(InvalidParameterException.class, () -> new Joueur(id), "Id négatif");
        }
        else {
            Joueur joueurN = new Joueur(id);
            Assertions.assertEquals(id, joueurN.id);
        }
    }
}
