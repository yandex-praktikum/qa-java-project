package BurgerTests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurgerGetPriceTest extends BurgerBaseTest{
    @Test
    public void testGetPriceWithoutIngredients() {
        assertEquals(100.0f, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        assertEquals(140.0f, burger.getPrice(), 0.001);
    }
}
