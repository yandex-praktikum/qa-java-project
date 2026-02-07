package BurgerTest;

import org.junit.Before;
import org.junit.Test;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class RemoveIngredientTest {
    private Burger burger;
    private Ingredient sauce;
    private Ingredient beef;
    int index;

    @Before
    public void setUp() {
        burger = new Burger();
        sauce = new Ingredient(SAUCE, "sauce", 10);
        beef = new Ingredient(FILLING, "beef", 12);
    }

    @Test
    public void shouldRemoveIngredient() {
        burger.addIngredient(sauce);
        index = burger.ingredients.indexOf(sauce);
        burger.removeIngredient(index);

        assertFalse(burger.ingredients.contains(sauce));
    }

    @Test
    public void shouldRemoveCorrectIngredient() {
        burger.addIngredient(beef);
        burger.addIngredient(sauce);
        index = burger.ingredients.indexOf(sauce);
        burger.removeIngredient(index);

        assertFalse(burger.ingredients.contains(sauce));
    }

    @Test
    public void cantRemoveIngredientWithInvalidIndex() {
        try {
            burger.removeIngredient(3);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("out of bounds"));
        }
    }

    @Test
    public void cantRemoveFromEmptyList() {
        try {
            burger.removeIngredient(0);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().contains("out of bounds"));
        }
    }
}
