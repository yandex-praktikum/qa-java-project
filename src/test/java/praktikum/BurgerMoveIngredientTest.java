package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ingredient movement functionality.
 */
public class BurgerMoveIngredientTest extends BaseBurgerTest {

    @Test
    public void testMoveIngredientFirstToLast() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);
        burger.addIngredient(sausage);

        burger.moveIngredient(0, 2);

        assertEquals("First element should be dinosaur", dinosaur, burger.ingredients.get(0));
        assertEquals("Second element should be sausage", sausage, burger.ingredients.get(1));
        assertEquals("Third element should be cutlet", cutlet, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientLastToFirst() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);
        burger.addIngredient(sausage);

        burger.moveIngredient(2, 0);

        assertEquals("First element should be sausage", sausage, burger.ingredients.get(0));
        assertEquals("Second element should be cutlet", cutlet, burger.ingredients.get(1));
        assertEquals("Third element should be dinosaur", dinosaur, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientMiddleToMiddle() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);
        burger.addIngredient(sausage);
        burger.addIngredient(hotSauce);

        burger.moveIngredient(1, 2);

        assertEquals("First element should be cutlet", cutlet, burger.ingredients.get(0));
        assertEquals("Second element should be sausage", sausage, burger.ingredients.get(1));
        assertEquals("Third element should be dinosaur", dinosaur, burger.ingredients.get(2));
        assertEquals("Fourth element should be hot sauce", hotSauce, burger.ingredients.get(3));
    }

    @Test
    public void testMoveIngredientSamePosition() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);

        burger.moveIngredient(0, 0);

        assertEquals("First element should remain cutlet", cutlet, burger.ingredients.get(0));
        assertEquals("Second element should remain dinosaur", dinosaur, burger.ingredients.get(1));
    }
}