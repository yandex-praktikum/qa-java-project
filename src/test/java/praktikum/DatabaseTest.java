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
    public void testDatabaseInitialization() {
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();

        assertNotNull("Buns list should not be null", buns);
        assertNotNull("Ingredients list should not be null", ingredients);

        assertEquals("Should have 3 buns", 3, buns.size());
        assertEquals("Should have 6 ingredients", 6, ingredients.size());
    }

    @Test
    public void testAvailableBuns() {
        List<Bun> buns = database.availableBuns();

        Bun firstBun = buns.get(0);
        assertEquals("First bun name should be 'black bun'", "black bun", firstBun.getName());
        assertEquals("First bun price should be 100", 100f, firstBun.getPrice(), 0.001);

        Bun secondBun = buns.get(1);
        assertEquals("Second bun name should be 'white bun'", "white bun", secondBun.getName());
        assertEquals("Second bun price should be 200", 200f, secondBun.getPrice(), 0.001);

        Bun thirdBun = buns.get(2);
        assertEquals("Third bun name should be 'red bun'", "red bun", thirdBun.getName());
        assertEquals("Third bun price should be 300", 300f, thirdBun.getPrice(), 0.001);
    }

    @Test
    public void testAvailableIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals("First ingredient type should be SAUCE", IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("First ingredient name should be 'hot sauce'", "hot sauce", ingredients.get(0).getName());
        assertEquals("First ingredient price should be 100", 100f, ingredients.get(0).getPrice(), 0.001);

        assertEquals("Second ingredient type should be SAUCE", IngredientType.SAUCE, ingredients.get(1).getType());
        assertEquals("Second ingredient name should be 'sour cream'", "sour cream", ingredients.get(1).getName());
        assertEquals("Second ingredient price should be 200", 200f, ingredients.get(1).getPrice(), 0.001);

        assertEquals("Third ingredient type should be SAUCE", IngredientType.SAUCE, ingredients.get(2).getType());
        assertEquals("Third ingredient name should be 'chili sauce'", "chili sauce", ingredients.get(2).getName());
        assertEquals("Third ingredient price should be 300", 300f, ingredients.get(2).getPrice(), 0.001);

        assertEquals("Fourth ingredient type should be FILLING", IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals("Fourth ingredient name should be 'cutlet'", "cutlet", ingredients.get(3).getName());
        assertEquals("Fourth ingredient price should be 100", 100f, ingredients.get(3).getPrice(), 0.001);

        assertEquals("Fifth ingredient type should be FILLING", IngredientType.FILLING, ingredients.get(4).getType());
        assertEquals("Fifth ingredient name should be 'dinosaur'", "dinosaur", ingredients.get(4).getName());
        assertEquals("Fifth ingredient price should be 200", 200f, ingredients.get(4).getPrice(), 0.001);

        assertEquals("Sixth ingredient type should be FILLING", IngredientType.FILLING, ingredients.get(5).getType());
        assertEquals("Sixth ingredient name should be 'sausage'", "sausage", ingredients.get(5).getName());
        assertEquals("Sixth ingredient price should be 300", 300f, ingredients.get(5).getPrice(), 0.001);
    }

    @Test
    public void testDatabaseReturnsSameLists() {
        List<Bun> buns1 = database.availableBuns();
        List<Bun> buns2 = database.availableBuns();

        List<Ingredient> ingredients1 = database.availableIngredients();
        List<Ingredient> ingredients2 = database.availableIngredients();

        assertSame("Should return the same buns list", buns1, buns2);
        assertSame("Should return the same ingredients list", ingredients1, ingredients2);
    }
}