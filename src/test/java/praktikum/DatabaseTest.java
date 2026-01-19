package praktikum;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DatabaseTest {

    @Test
    public void availableBunsReturnsData() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();
        assertFalse(buns.isEmpty());
        assertEquals(3, buns.size());
    }

    @Test
    public void availableIngredientsReturnsData() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();
        assertFalse(ingredients.isEmpty());
        assertEquals(6, ingredients.size());
    }
}
