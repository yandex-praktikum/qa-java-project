package praktikum;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();

        assertNotNull(buns);
        assertFalse(buns.isEmpty());

        Bun firstBun = buns.get(0);
        assertNotNull(firstBun.getName());
        assertTrue(firstBun.getPrice() > 0);
    }

    @Test
    public void testAvailableIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();

        assertNotNull(ingredients);
        assertFalse(ingredients.isEmpty());

        Ingredient firstIngredient = ingredients.get(0);
        assertNotNull(firstIngredient.getName());
        assertTrue(firstIngredient.getPrice() > 0);
        assertNotNull(firstIngredient.getType());
    }
}
