import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class ChangeReturnTest {

    // méthode de test générale qui appelle des cas de test spécifiques
    @Test
    public void testGiveChange() { // tout les tests demandé
        testChangeForAmount(10L, map(10L, 1L));
        testChangeForAmount(11L, map(5L, 1L, 2L, 3L));
        testChangeForAmount(21L, map(10L, 1L, 5L, 1L, 2L, 3L));
        testChangeForAmount(23L, map(10L, 1L, 5L, 1L, 4L, 2L));
        testChangeForAmount(31L, map(10L, 2L, 5L, 1L, 2L, 3L));
    }

    // méthode utilitaire pour simplifier l'appel de la méthode de test
    private void testChangeForAmount(long amount, Map<Long, Long> expectedSolution) {
        // appelle la méthode giveChange et vérifie si la solution correspond à celle attendue
        Map<Long, Long> actualSolution = ChangeReturn.giveChange(amount);
        assertEquals(expectedSolution, actualSolution);
    }

    // méthode utilitaire pour créer une Map à partir d'une liste variable de valeurs clé-valeur
    private Map<Long, Long> map(Long... values) {
        // les valeurs proviennet de la ligne de testGiveChange
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < values.length; i += 2) {
            map.put(values[i], values[i + 1]);
        }
        return map;
    }
}
