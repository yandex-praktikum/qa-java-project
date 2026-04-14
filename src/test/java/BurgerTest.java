import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockSauce;
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
        mockBun = mock(Bun.class);
        mockSauce = mock(Ingredient.class);
        mockFilling = mock(Ingredient.class);

        when(mockBun.getName()).thenReturn("Булочка");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getPrice()).thenReturn(30f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);

        when(mockFilling.getName()).thenReturn("cutlet");
        when(mockFilling.getPrice()).thenReturn(50f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsSetsCorrectBun() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesSize() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectIngredient() {
        burger.addIngredient(mockSauce);
        assertEquals(mockSauce, burger.ingredients.get(0));
    }

    @Test
    public void addIngredientAddsTwoIngredients() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientDecreasesSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesCorrectElement() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertEquals(mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesFirstToSecond() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        assertEquals(mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesSecondToFirst() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        assertEquals(mockSauce, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptContainsBunName() {
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Булочка"));
    }

    @Test
    public void getReceiptContainsSauceName() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("hot sauce"));
    }

    @Test
    public void getReceiptContainsSauceType() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("sauce"));
    }

    @Test
    public void getReceiptContainsFillingName() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockFilling);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("cutlet"));
    }

    @Test
    public void getReceiptContainsFillingType() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockFilling);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("filling"));
    }

    @Test
    public void getReceiptContainsPrice() {
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void getReceiptWithMultipleIngredientContainsSauce() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("hot sauce"));
    }

    @Test
    public void getReceiptWithMultipleIngredientContainsFilling() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("cutlet"));
    }
}