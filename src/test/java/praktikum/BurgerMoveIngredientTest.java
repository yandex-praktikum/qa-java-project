package praktikum;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertTrue;

public class BurgerMoveIngredientTest {

    @Test
    public void testMoveIngredient_NewFirstIsSecond() {
        Burger burger = new Burger();

        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertTrue(burger.ingredients.get(0) == second);
    }

    @Test
    public void testMoveIngredient_NewSecondIsThird() {
        Burger burger = new Burger();

        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertTrue(burger.ingredients.get(1) == third);
    }

    @Test
    public void testMoveIngredient_NewThirdIsFirst() {
        Burger burger = new Burger();

        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertTrue(burger.ingredients.get(2) == first);
    }
}


