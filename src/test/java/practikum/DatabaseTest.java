package practikum;

import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    @Test
    public void databaseReturnsBunsAndIngredients() {
        Database database = new Database();

        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();

        assertNotNull(buns);
        assertNotNull(ingredients);

        assertEquals(3, buns.size());
        assertEquals(6, ingredients.size());
    }
}
