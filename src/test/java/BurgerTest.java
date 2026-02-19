import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class BurgerTest {

    private Burger burger;

    // Константы для цен ингредиентов
    private static final float BUN_PRICE = 1.0f;
    private static final float KETCHUP_SAUCE_PRICE = 0.5f;
    private static final float CHEESE_FILLING_PRICE = 1.0f;

    @Before
    public void setup() {
        burger = new Burger();
    }

    private void prepareReceipt() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockBun.getName()).thenReturn("Bun");
        burger.setBuns(mockBun);

        Ingredient ketchupSauce = mock(Ingredient.class);
        when(ketchupSauce.getPrice()).thenReturn(KETCHUP_SAUCE_PRICE);
        when(ketchupSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(ketchupSauce.getName()).thenReturn("Ketchup");

        Ingredient cheeseFilling = mock(Ingredient.class);
        when(cheeseFilling.getPrice()).thenReturn(CHEESE_FILLING_PRICE);
        when(cheeseFilling.getType()).thenReturn(IngredientType.FILLING);
        when(cheeseFilling.getName()).thenReturn("Cheese");

        burger.addIngredient(ketchupSauce);
        burger.addIngredient(cheeseFilling);
    }

    @Test
    public void testSetBuns() {
        Bun mockBun = mock(Bun.class);
        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        Ingredient mockIngredient = mock(Ingredient.class);
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testReceiptContainsKetchup() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Ketchup"));
    }

    @Test
    public void testReceiptContainsCheese() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Cheese"));
    }

    @Test
    public void testReceiptContainsBunName() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Bun ====)"));
    }

    @Test
    public void testReceiptContainsPrice() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
    }

    // Удаляем testReceiptFormatting()

    @Test
    public void testReceiptHasCorrectBunFormatAtStart() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.startsWith("(==== Bun ====)"));
    }

    @Test
    public void testReceiptContainsSauce() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Ketchup"));
    }

    @Test
    public void testReceiptContainsFilling() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Cheese"));
    }

    @Test
    public void testReceiptHasCorrectBunFormatAtEnd() {
        prepareReceipt();
        String receipt = burger.getReceipt();
        assertTrue(receipt.endsWith("(==== Bun ====)"));
    }


    @Test
    public void testReceiptShowsCorrectTotalPrice() {
        prepareReceipt();
        String receipt = burger.getReceipt();

        float expectedPrice = BUN_PRICE + KETCHUP_SAUCE_PRICE + CHEESE_FILLING_PRICE;
        String expectedPriceString = String.format("Price: %.2f", expectedPrice);

        assertTrue(receipt.contains(expectedPriceString));
    }

    @Test
    public void testMoveIngredient() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredientAfterMove() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);
        burger.removeIngredient(1);
        assertFalse(burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void testRemoveIngredientValidIndex() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientValidIndexRemovesCorrectIngredient() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(firstIngredient));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientNegativeIndex() {
        burger.removeIngredient(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientIndexOutOfBounds() {
        burger.removeIngredient(0);
    }

    @Test
    public void testMoveIngredientValidIndicesPosition1() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientValidIndicesPosition0() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);
        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientNegativeSourceIndex() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.moveIngredient(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientSourceIndexOutOfBounds() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.moveIngredient(5, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientNegativeTargetIndex() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientTargetIndexOutOfBounds() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 5);
    }
}
