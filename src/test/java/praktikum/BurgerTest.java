package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(50f);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);

        ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(200f);
        when(ingredient2.getName()).thenReturn("cutlet");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToList() {
        burger.addIngredient(ingredient1);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredientFromList() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesIngredientCorrectly() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        burger.setBuns(bun);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 450f;

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        burger.setBuns(bun);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("cutlet"));
        assertTrue(receipt.contains("Price: 450"));
    }
}