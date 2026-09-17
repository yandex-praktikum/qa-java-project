package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private static final int EXPECTED_BUNS_COUNT = 3;
    private static final int EXPECTED_INGREDIENTS_COUNT = 6;

    private static final int BLACK_BUN_INDEX = 0;
    private static final int WHITE_BUN_INDEX = 1;
    private static final int RED_BUN_INDEX = 2;

    private static final String BLACK_BUN_NAME = "black bun";
    private static final String WHITE_BUN_NAME = "white bun";
    private static final String RED_BUN_NAME = "red bun";

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void availableBunsShouldReturnThreeBuns() {
        List<Bun> buns = database.availableBuns();
        assertEquals(EXPECTED_BUNS_COUNT, buns.size());
    }

    @Test
    public void availableIngredientsShouldReturnSixIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(EXPECTED_INGREDIENTS_COUNT, ingredients.size());
    }

    @Test
    public void availableBunsShouldContainBlackBun() {
        List<Bun> buns = database.availableBuns();
        assertEquals(BLACK_BUN_NAME, buns.get(BLACK_BUN_INDEX).getName());
    }

    @Test
    public void availableBunsShouldContainWhiteBun() {
        List<Bun> buns = database.availableBuns();
        assertEquals(WHITE_BUN_NAME, buns.get(WHITE_BUN_INDEX).getName());
    }

    @Test
    public void availableBunsShouldContainRedBun() {
        List<Bun> buns = database.availableBuns();
        assertEquals(RED_BUN_NAME, buns.get(RED_BUN_INDEX).getName());
    }
}