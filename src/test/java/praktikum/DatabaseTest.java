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
    public void testAvailableBuns() {
        List<Bun> buns = database.availableBuns();

        assertEquals("Должно быть 3 булки", 3, buns.size());

        // Проверяем первую булку
        assertEquals("black bun", buns.get(0).getName());
        assertEquals(100f, buns.get(0).getPrice(), 0.001);

        // Проверяем вторую булку
        assertEquals("white bun", buns.get(1).getName());
        assertEquals(200f, buns.get(1).getPrice(), 0.001);

        // Проверяем третью булку
        assertEquals("red bun", buns.get(2).getName());
        assertEquals(300f, buns.get(2).getPrice(), 0.001);
    }

    @Test
    public void testAvailableIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals("Должно быть 6 ингредиентов", 6, ingredients.size());

        // Проверяем соусы (первые 3)
        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals(100f, ingredients.get(0).getPrice(), 0.001);

        assertEquals(IngredientType.SAUCE, ingredients.get(1).getType());
        assertEquals("sour cream", ingredients.get(1).getName());
        assertEquals(200f, ingredients.get(1).getPrice(), 0.001);

        assertEquals(IngredientType.SAUCE, ingredients.get(2).getType());
        assertEquals("chili sauce", ingredients.get(2).getName());
        assertEquals(300f, ingredients.get(2).getPrice(), 0.001);

        // Проверяем начинки (следующие 3)
        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals("cutlet", ingredients.get(3).getName());
        assertEquals(100f, ingredients.get(3).getPrice(), 0.001);

        assertEquals(IngredientType.FILLING, ingredients.get(4).getType());
        assertEquals("dinosaur", ingredients.get(4).getName());
        assertEquals(200f, ingredients.get(4).getPrice(), 0.001);

        assertEquals(IngredientType.FILLING, ingredients.get(5).getType());
        assertEquals("sausage", ingredients.get(5).getName());
        assertEquals(300f, ingredients.get(5).getPrice(), 0.001);
    }
}