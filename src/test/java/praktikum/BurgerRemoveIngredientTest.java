package praktikum;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;


public class BurgerRemoveIngredientTest {
    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }
}
