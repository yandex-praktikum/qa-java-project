package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerAdditionalTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Test
    public void testRemoveIngredientFromEmptyListThrowsException() {
        Burger burger = new Burger();
        try {
            burger.removeIngredient(0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testMoveIngredientFromEmptyListThrowsException() {
        Burger burger = new Burger();
        try {
            burger.moveIngredient(0, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testMoveIngredientNegativeIndexThrowsException() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        try {
            burger.moveIngredient(-1, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testMoveIngredientNegativeNewIndexThrowsException() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        try {
            burger.moveIngredient(0, -1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testGetPriceWithNullBunThrowsNPE() {
        Burger burger = new Burger();
        try {
            burger.getPrice();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testGetReceiptWithNullBunThrowsNPE() {
        Burger burger = new Burger();
        try {
            burger.getReceipt();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testMoveIngredientIndexOutOfBounds() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        try {
            burger.moveIngredient(0, 2);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testSetBunsWithNull() {
        Burger burger = new Burger();
        burger.setBuns(null);
        assertNull(burger.bun);
    }

    @Test
    public void testGetPriceAfterSetBunsWithNull() {
        Burger burger = new Burger();
        burger.setBuns(null);
        try {
            burger.getPrice();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }
}