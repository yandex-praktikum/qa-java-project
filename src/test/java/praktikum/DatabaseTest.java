package praktikum;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    @Test
    public void availableBunsShouldReturnThreeBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
    }

    @Test
    public void availableIngredientsShouldReturnSixIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());
    }

    @Test
    public void availableBunsShouldContainBlackBun() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertEquals("black bun", buns.get(0).getName());
    }

    @Test
    public void availableBunsShouldContainWhiteBun() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertEquals("white bun", buns.get(1).getName());
    }

    @Test
    public void availableBunsShouldContainRedBun() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertEquals("red bun", buns.get(2).getName());
    }
}
