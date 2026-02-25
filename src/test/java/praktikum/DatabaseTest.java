package praktikum;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
        assertFalse(buns.isEmpty());
        assertTrue(buns.stream().anyMatch(bun -> bun.getName().equals("black bun")));
        assertTrue(buns.stream().anyMatch(bun -> bun.getName().equals("white bun")));
        assertTrue(buns.stream().anyMatch(bun -> bun.getName().equals("red bun")));
    }

    @Test
    public void testAvailableIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
        assertFalse(ingredients.isEmpty());
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("hot sauce")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("sour cream")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("chili sauce")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("cutlet")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("dinosaur")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("sausage")));
    }
}