package BurgerTests;

import org.junit.Test;

import static org.junit.Assert.*;

public class BurgerAddAndRemoveIngredientTest extends BurgerBaseTest{

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        assertEquals(2, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient1));
        assertTrue(burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(mockIngredient1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNonExistentIngredient() {
        burger.removeIngredient(0);
    }
}
