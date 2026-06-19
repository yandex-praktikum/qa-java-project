package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerIngredientsTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void shouldAddIngredient() {
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void shouldRemoveIngredient() {
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldMoveIngredient() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(second, burger.ingredients.get(0));
        assertEquals(first, burger.ingredients.get(1));
    }
}
