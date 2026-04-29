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
    public void availableBuns_ReturnsThreeBuns() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
        assertEquals("black bun", buns.get(0).getName());
        assertEquals("white bun", buns.get(1).getName());
        assertEquals("red bun", buns.get(2).getName());
    }

    @Test
    public void availableIngredients_ReturnsSixIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());
    }

    @Test
    public void availableIngredients_ContainsThreeSaucesAndThreeFillings() {
        List<Ingredient> ingredients = database.availableIngredients();
        long saucesCount = ingredients.stream()
                .filter(i -> i.getType() == IngredientType.SAUCE)
                .count();
        long fillingsCount = ingredients.stream()
                .filter(i -> i.getType() == IngredientType.FILLING)
                .count();

        assertEquals(3, saucesCount);
        assertEquals(3, fillingsCount);
    }
}