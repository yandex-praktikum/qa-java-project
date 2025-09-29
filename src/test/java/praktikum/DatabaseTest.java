package praktikum;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testAvailableBunsNotEmpty() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();
        assertFalse(buns.isEmpty());
    }

    @Test
    public void testAvailableIngredientsNotEmpty() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();
        assertFalse(ingredients.isEmpty());
    }
}