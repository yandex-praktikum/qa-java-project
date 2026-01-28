package praktikum;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void availableBunsShouldReturnNonEmptyListWithExpectedSize() {
        Database db = new Database();

        List<Bun> buns = db.availableBuns();

        assertNotNull(buns);
        assertEquals(3, buns.size());
    }

    @Test
    public void availableIngredientsShouldReturnNonEmptyListWithExpectedSize() {
        Database db = new Database();

        List<Ingredient> ingredients = db.availableIngredients();

        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
    }

}
