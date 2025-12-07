package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BurgerAddIngredientTest {

    @Test
    public void testIngredientCountAfterAdd() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddedIngredientIsStoredCorrectly() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertTrue("Ингредиент должен быть сохранён в списке",
                burger.ingredients.get(0) == ingredient);
    }
}

