package burger.test;

import org.junit.Before;
import org.junit.Test;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class MoveIngredientNegativeTest {
    private Burger burger;
    private Ingredient sauce;
    private Ingredient cheese;
    private Ingredient beef;
    private Ingredient salad;
    private List<Ingredient> ingredients;
    private List<Ingredient> expected;
    private List<Ingredient> actual;


    @Before
    public void setUp() {
        burger = new Burger();
        sauce = new Ingredient(SAUCE, "sauce", 1);
        cheese = new Ingredient(FILLING, "cheese", 1);
        beef = new Ingredient(FILLING, "beef", 1);
        salad = new Ingredient(FILLING, "salad", 1);
    }

    @Test
    public void cantMoveInEmptyListTest() {
        try {
            burger.moveIngredient(0, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("out of bounds"));
        }
    }

    @Test
    public void cantMoveToInvalidIndexTest() {
        try {
            burger.addIngredient(cheese);
            burger.addIngredient(beef);

            burger.moveIngredient(0, 3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("Index: 3"));
        }
    }

    @Test
    public void cantMoveFromInvalidIndexTest() {
        try {
            burger.addIngredient(cheese);
            burger.addIngredient(beef);

            burger.moveIngredient(3, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("out of bounds"));
        }
    }
}