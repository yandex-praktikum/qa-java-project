package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerFullCoverageTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    @Mock
    private Ingredient ingredientMock3;

    @Before
    public void setUp() {
        burger = new Burger();


        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("Hot Sauce");
        when(ingredientMock1.getPrice()).thenReturn(50.0f);

        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn("Cheese");
        when(ingredientMock2.getPrice()).thenReturn(80.0f);

        when(ingredientMock3.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock3.getName()).thenReturn("Garlic Sauce");
        when(ingredientMock3.getPrice()).thenReturn(30.0f);
    }

    @Test
    public void testConstructor() {
        assertNotNull(burger.ingredients);
        assertTrue(burger.ingredients.isEmpty());
        assertNull(burger.bun);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
        assertEquals(ingredientMock2, burger.ingredients.get(1));
        assertEquals(ingredientMock3, burger.ingredients.get(2));
    }

    @Test
    public void testRemoveIngredientValidIndex() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
        assertEquals(ingredientMock3, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientFromStartToEnd() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock3, burger.ingredients.get(1));
        assertEquals(ingredientMock1, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientFromEndToStart() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(2, 0);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredientMock3, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
        assertEquals(ingredientMock2, burger.ingredients.get(2));
    }
    @Test
    public void testMoveIngredientToSamePosition() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(0, 0);

        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
        assertEquals(ingredientMock2, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientAdjacent() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(0, 1);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
        assertEquals(ingredientMock3, burger.ingredients.get(2));
    }

    @Test
    public void testGetPriceWithBunOnly() {
        burger.setBuns(bunMock);

        float price = burger.getPrice();
        assertEquals(200.0f, price, 0.001f); // 100 * 2 = 200
    }

    @Test
    public void testGetPriceWithOneIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        float price = burger.getPrice();
        assertEquals(250.0f, price, 0.001f); // (100 * 2) + 50 = 250
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        float price = burger.getPrice();
        assertEquals(360.0f, price, 0.001f); // (100 * 2) + 50 + 80 + 30 = 360
    }

    @Test
    public void testGetReceiptEmpty() {
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("(==== Test Bun ====)"));
        assertTrue(receipt.contains("Price: 200.000000"));


        String[] lines = receipt.split("\n");
        assertTrue(lines.length >= 3); // Минимум 3 строки
    }

    @Test
    public void testGetReceiptWithOneIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("(==== Test Bun ====)"));
        assertTrue(receipt.contains("= sauce Hot Sauce ="));
        assertTrue(receipt.contains("Price: 250.000000"));
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("(==== Test Bun ====)"));
        assertTrue(receipt.contains("= sauce Hot Sauce ="));
        assertTrue(receipt.contains("= filling Cheese ="));
        assertTrue(receipt.contains("= sauce Garlic Sauce ="));
        assertTrue(receipt.contains("Price: 360.000000"));
    }

    @Test
    public void testGetReceiptIngredientTypeLowerCase() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock2); // FILLING тип

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("filling"));
        assertFalse(receipt.contains("FILLING"));
    }

    @Test
    public void testClearAllIngredients() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        assertEquals(2, burger.ingredients.size());

        burger.removeIngredient(0);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }
    @Test
    public void testPriceCalculationAfterMovingIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1); // 50
        burger.addIngredient(ingredientMock2); // 80

        float priceBefore = burger.getPrice();
        assertEquals(330.0f, priceBefore, 0.001f); // (100*2) + 50 + 80 = 330

        burger.moveIngredient(0, 1);

        float priceAfter = burger.getPrice();
        assertEquals(330.0f, priceAfter, 0.001f); // Цена не должна меняться после перемещения
    }

    @Test
    public void testReceiptAfterMovingIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receiptBefore = burger.getReceipt();

        burger.moveIngredient(0, 1);

        String receiptAfter = burger.getReceipt();

        assertNotEquals(receiptBefore, receiptAfter);
        assertTrue(receiptBefore.contains("Hot Sauce"));
        assertTrue(receiptBefore.contains("Cheese"));
        assertTrue(receiptAfter.contains("Hot Sauce"));
        assertTrue(receiptAfter.contains("Cheese"));
    }
}