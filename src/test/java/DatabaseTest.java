import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    void testDatabaseInitialization() {
        assertNotNull(database);
    }

    @Test
    void testAvailableBuns() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());

        assertEquals("black bun", buns.get(0).getName());

        assertEquals("white bun", buns.get(1).getName());

        assertEquals("red bun", buns.get(2).getName());

    }

    @Test
    void testAvailableIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());

        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("hot sauce", ingredients.get(0).getName());

        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals("cutlet", ingredients.get(3).getName());
    }
}