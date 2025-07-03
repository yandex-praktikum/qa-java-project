package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseTest {
    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBuns_ReturnsNewCopy() {
        List<Bun> firstCall = database.availableBuns();
        List<Bun> secondCall = database.availableBuns();

        assertNotSame("Должна возвращаться новая копия списка", firstCall, secondCall);
    }

    @Test
    public void testAvailableIngredients_ReturnsNewCopy() {
        List<Ingredient> firstCall = database.availableIngredients();
        List<Ingredient> secondCall = database.availableIngredients();

        assertNotSame("Должна возвращаться новая копия списка", firstCall, secondCall);
    }

    @Test
    public void testAvailableBuns_ContainsCorrectData() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
    }

    @Test
    public void testAvailableIngredients_ContainsCorrectData() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());
    }

    @Test
    public void testAvailableBuns_HasCorrectBunData() {
        List<Bun> buns = database.availableBuns();
        assertTrue(buns.stream().anyMatch(b -> "black bun".equals(b.getName()) && Math.abs(100 - b.getPrice()) < 0.001));
    }

    @Test
    public void testAvailableIngredients_HasCorrectIngredientData() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertTrue(ingredients.stream().anyMatch(i ->
                IngredientType.SAUCE == i.getType() &&
                        "hot sauce".equals(i.getName()) &&
                        Math.abs(100 - i.getPrice()) < 0.001
        ));
    }
}