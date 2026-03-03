package praktikum;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void availableBunsReturnsNonEmptyList() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertFalse(buns.isEmpty());
    }

    @Test
    public void availableIngredientsReturnsNonEmptyList() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        assertFalse(ingredients.isEmpty());
    }

    @Test
    public void availableBunsContainsCorrectCount() {
        Database database = new Database();
        assertEquals(3, database.availableBuns().size());
    }

    @Test
    public void availableIngredientsContainsCorrectCount() {
        Database database = new Database();
        assertEquals(6, database.availableIngredients().size());
    }
}
