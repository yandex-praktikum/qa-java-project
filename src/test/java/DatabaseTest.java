import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    private Database database;
    @BeforeEach
    void setUp() {
        database = new Database();
    }
    @Test
    public void testAvailableBuns() {
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
        assertEquals(3, buns.size());
        assertEquals("black bun", buns.get(0).getName());
        assertEquals(100.0f, buns.get(0).getPrice());
        assertEquals("white bun", buns.get(1).getName());
        assertEquals(200.0f, buns.get(1).getPrice());
        assertEquals("red bun", buns.get(2).getName());
        assertEquals(300.0f, buns.get(2).getPrice());
    }
    @Test
    public void testAvailableBunsReturnsList() {
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
        assertEquals(3, buns.size());
        // Проверяем, что список содержит правильные данные
        assertEquals("black bun", buns.get(0).getName());
        assertEquals("white bun", buns.get(1).getName());
        assertEquals("red bun", buns.get(2).getName());
    }
    @Test
    public void testAvailableIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
        Assertions.assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals(100.0f, ingredients.get(0).getPrice());
        assertEquals(IngredientType.SAUCE, ingredients.get(1).getType());
        assertEquals("sour cream", ingredients.get(1).getName());
        assertEquals(200.0f, ingredients.get(1).getPrice());
        assertEquals(IngredientType.SAUCE, ingredients.get(2).getType());
        assertEquals("chili sauce", ingredients.get(2).getName());
        assertEquals(300.0f, ingredients.get(2).getPrice());
        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals("cutlet", ingredients.get(3).getName());
        assertEquals(100.0f, ingredients.get(3).getPrice());
        assertEquals(IngredientType.FILLING, ingredients.get(4).getType());
        assertEquals("dinosaur", ingredients.get(4).getName());
        assertEquals(200.0f, ingredients.get(4).getPrice());
        assertEquals(IngredientType.FILLING, ingredients.get(5).getType());
        assertEquals("sausage", ingredients.get(5).getName());
        assertEquals(300.0f, ingredients.get(5).getPrice());
    }

    @Test
    public void testAvailableIngredientsReturnsList() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals("sour cream", ingredients.get(1).getName());
        assertEquals("chili sauce", ingredients.get(2).getName());
        assertEquals("cutlet", ingredients.get(3).getName());
        assertEquals("dinosaur", ingredients.get(4).getName());
        assertEquals("sausage", ingredients.get(5).getName());
    }

    @Test
    public void testDatabaseDataIntegrity() {
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(3, buns.size());
        assertEquals(100.0f, buns.get(0).getPrice());
        assertEquals(200.0f, buns.get(1).getPrice());
        assertEquals(300.0f, buns.get(2).getPrice());
        assertEquals(6, ingredients.size());
        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
    }

    @Test
    public void testDatabaseReturnsSameLists() {
        List<Bun> buns1 = database.availableBuns();
        List<Bun> buns2 = database.availableBuns();

        assertSame(buns1, buns2);

        List<Ingredient> ingredients1 = database.availableIngredients();
        List<Ingredient> ingredients2 = database.availableIngredients();

        assertSame(ingredients1, ingredients2);
    }

}