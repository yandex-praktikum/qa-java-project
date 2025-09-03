package BurgerTests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurgerMoveIngredientTest extends BurgerBaseTest{
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 1);
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveNonExistentIngredient() {
        burger.moveIngredient(0, 1);
    }
}
