package praktikum;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();
        assertEquals(3, buns.size());
        assertEquals("black bun", buns.get(0).getName());
    }

    @Test
    public void testAvailableIngredients() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();
        assertEquals(6, ingredients.size());
        assertEquals("hot sauce", ingredients.get(0).getName());
    }
}
