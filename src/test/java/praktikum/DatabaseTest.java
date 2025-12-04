package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void availableBunsReturnsThreeBuns() {
        List<Bun> buns = database.availableBuns();

        assertNotNull(buns);
        assertEquals(3, buns.size());
    }

    @Test
    public void availableIngredientsReturnsSixIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
    }

    @Test
    public void availableBunsContainsBlackBunWithPrice100() {
        List<Bun> buns = database.availableBuns();

        boolean hasBlackBun = buns.stream()
                .anyMatch(bun -> "black bun".equals(bun.getName()) && bun.getPrice() == 100f);

        assertTrue(hasBlackBun);
    }
}
