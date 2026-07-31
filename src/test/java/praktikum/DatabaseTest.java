package praktikum;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
        assertFalse(buns.isEmpty());
    }

    @Test
    public void testAvailableIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
        assertFalse(ingredients.isEmpty());
    }
}