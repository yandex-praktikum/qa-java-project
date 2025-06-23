package praktikum;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBunsReturnsNotNull() {
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
    }

    @Test
    public void testAvailableBunsReturnsCorrectSize() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
    }

    @Test
    public void testAvailableIngredientsReturnsNotNull() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
    }

    @Test
    public void testAvailableIngredientsReturnsCorrectSize() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());
    }

    @Test
    public void testFirstBunHasCorrectName() {
        List<Bun> buns = database.availableBuns();
        assertEquals("black bun", buns.get(0).getName());
    }

    @Test
    public void testFirstBunHasCorrectPrice() {
        List<Bun> buns = database.availableBuns();
        assertEquals(100.0f, buns.get(0).getPrice(), 0.0f);
    }

    @Test
    public void testFirstIngredientHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals("hot sauce", ingredients.get(0).getName());
    }

    @Test
    public void testFirstIngredientHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
    }
}
