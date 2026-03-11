package burger.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


public class AddIngredientTest {
    private Burger burger;
    private Ingredient sauceMock;
    private Ingredient cheeseMock;
    int expected;
    int actual;

    @Before
    public void setUp() {
        sauceMock = Mockito.mock(Ingredient.class);
        cheeseMock = Mockito.mock(Ingredient.class);
        burger = new Burger();

    }

    @Test
    public void shouldAddOneIngredientTest() {
        burger.addIngredient(sauceMock);

        expected = 1;
        actual = burger.ingredients.size();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddTwoIngredientsTest() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(cheeseMock);
        expected = 2;
        actual = burger.ingredients.size();

        assertEquals(expected, actual);
    }
}