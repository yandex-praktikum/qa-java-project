package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerAdditionalTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Test
    public void testRemoveIngredientFromEmptyListThrowsException() {
        Burger burger = new Burger();
        burger.removeIngredient(0);
    }

    @Test
    public void testMoveIngredientFromEmptyListThrowsException() {
        Burger burger = new Burger();
        burger.moveIngredient(0, 0);
    }

    @Test
    public void testMoveIngredientNegativeIndexThrowsException() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        burger.moveIngredient(-1, 0);
    }

    @Test
    public void testMoveIngredientNegativeNewIndexThrowsException() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        burger.moveIngredient(0, -1);
    }

    @Test
    public void testGetPriceWithNullBunThrowsNPE() {
        Burger burger = new Burger();
        burger.getPrice();
    }

    @Test
    public void testGetReceiptWithNullBunThrowsNPE() {
        Burger burger = new Burger();
        burger.getReceipt();
    }

    @Test
    public void testMoveIngredientIndexOutOfBounds() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        burger.moveIngredient(0, 2);
    }

    @Test
    public void testSetBunsWithNull() {
        Burger burger = new Burger();
        burger.setBuns(null);
        burger.getPrice();
    }
}