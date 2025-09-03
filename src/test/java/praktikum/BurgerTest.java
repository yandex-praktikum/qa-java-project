package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauce;

    @Mock
    private Ingredient mockIngredientFilling;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Bun should be set correctly", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(mockIngredientSauce);
        assertEquals("Ingredients list size should increase by 1 after adding", initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientContainsAddedIngredient() {
        burger.addIngredient(mockIngredientSauce);
        assertTrue("Ingredients list should contain the added ingredient", burger.ingredients.contains(mockIngredientSauce));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);
        int initialSize = burger.ingredients.size();

        burger.removeIngredient(0);
        assertEquals("Ingredients list size should decrease by 1 after removal", initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);

        burger.removeIngredient(0);
        assertFalse("The removed ingredient should not be in the list", burger.ingredients.contains(mockIngredientSauce));
    }

    @Test
    public void testRemoveIngredientKeepsOtherIngredients() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);

        burger.removeIngredient(0);
        assertTrue("Other ingredients should remain in the list", burger.ingredients.contains(mockIngredientFilling));
    }

    @Test
    public void testMoveIngredientChangesOrderFirstToLast() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);
        burger.ingredients.add(mockIngredientThird);

        burger.moveIngredient(0, 2);
        assertEquals("Ingredient at index 2 should be the moved one", mockIngredientSauce, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientChangesOrderNewFirstElement() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);
        burger.ingredients.add(mockIngredientThird);

        burger.moveIngredient(0, 2);
        assertEquals("Ingredient at index 0 should be correct after move", mockIngredientFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesOrderNewSecondElement() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);
        burger.ingredients.add(mockIngredientThird);

        burger.moveIngredient(0, 2);
        assertEquals("Ingredient at index 1 should be correct after move", mockIngredientThird, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientMaintainsSize() {
        burger.ingredients.add(mockIngredientSauce);
        burger.ingredients.add(mockIngredientFilling);

        int initialSize = burger.ingredients.size();
        burger.moveIngredient(0, 1);
        assertEquals("Moving ingredient should not change the list size", initialSize, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndexThrowsException() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidIndexThrowsException() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void testGetPriceCallsBunGetPrice() {
        when(mockBun.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);

        burger.getPrice();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithNoIngredientsReturnsBunPriceOnly() {
        when(mockBun.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);

        float price = burger.getPrice();
        assertEquals("Price should be twice the bun price for a burger with no ingredients", 100f, price, 0.001f);
    }
}