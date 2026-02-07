package BurgerTest;

import org.junit.Before;
import org.junit.Test;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class AddIngredientTest {
    private Burger burger;
    private Ingredient sauce;
    private Ingredient cheese;
    int expected;
    int actual;

    @Before
    public void setUp() {
        sauce = new Ingredient(SAUCE, "sauce", 10);
        cheese = new Ingredient(FILLING, "cheese", 10);
        burger = new Burger();

    }

    @Test
    public void shouldAddOneIngredient() {
        burger.addIngredient(sauce);

        expected = 1;
        actual = burger.ingredients.size();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddTwoIngredients() {
        burger.addIngredient(sauce);
        burger.addIngredient(cheese);
        expected = 2;
        actual = burger.ingredients.size();

        assertEquals(expected, actual);
    }
}