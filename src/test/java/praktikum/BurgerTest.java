package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredient1 = mock(Ingredient.class);
        ingredient2 = mock(Ingredient.class);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithBunsAndIngredients() {
        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getPrice()).thenReturn(30.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(280.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetPriceOnlyBuns() {
        when(bun.getPrice()).thenReturn(150.0f);
        burger.setBuns(bun);
        assertEquals(300.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptNotNull() {
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(100.0f);

        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.length() > 0);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("black bun"));
    }
}
