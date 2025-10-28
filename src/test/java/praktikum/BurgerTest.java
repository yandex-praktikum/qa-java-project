package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredient = mock(Ingredient.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getPrice()).thenReturn(200f);
    }

    @Test
    public void testSetBunsSetsCorrectBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredientAddsToList() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientRemovesCorrectly() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testRemoveIngredientWithInvalidIndexDoesNothing() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(5);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientValidIndexesChangesOrder() {
        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);
        burger.addIngredient(i1);
        burger.addIngredient(i2);
        burger.moveIngredient(0, 1);
        assertEquals(i1, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientWithNewIndexEqualSizeMovesToEnd() {
        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);
        burger.addIngredient(i1);
        burger.addIngredient(i2);
        burger.moveIngredient(0, burger.ingredients.size());
        assertEquals(i1, burger.ingredients.get(burger.ingredients.size() - 1));
    }

    @Test
    public void testMoveIngredientWithSameIndexDoesNothing() {
        Ingredient i1 = mock(Ingredient.class);
        burger.addIngredient(i1);
        burger.moveIngredient(0, 0);
        assertEquals(i1, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientWithInvalidIndexDoesNothing() {
        burger.addIngredient(ingredient);
        burger.moveIngredient(5, 0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientWithInvalidNewIndexDoesNothing() {
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 5);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientEmptyListDoesNothing() {
        burger.moveIngredient(0, 1);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testGetPriceWithBunAndIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expected = 100f * 2 + 200f;
        assertEquals(expected, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetPriceWithoutBun() {
        burger.addIngredient(ingredient);
        assertEquals(200f, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetPriceWithNullIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(null);
        assertEquals(200f, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetReceiptWithBunAndIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void testGetReceiptWithoutBun() {
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= filling cutlet ="));
    }

    @Test
    public void testGetReceiptWithNullIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(null);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("=  ="));
    }

    @Test
    public void testGetReceiptWithNoIngredientsAndNoBun() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(====  ====)"));
        assertTrue(receipt.contains("Price:"));
    }
}