package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerCompleteTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.LENIENT);

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    @Before
    public void setUp() {
        burger = new Burger();

        when(bunMock.getName()).thenReturn("Complete Bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("Complete Sauce");
        when(sauceMock.getPrice()).thenReturn(50.0f);

        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("Complete Filling");
        when(fillingMock.getPrice()).thenReturn(75.0f);
    }

    @Test
    public void testSetBunsUpdatesBunField() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesListSize() {
        burger.addIngredient(sauceMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(sauceMock);
        assertEquals(sauceMock, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsIncreasesSize() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testAddMultipleIngredientsPreservesOrderFirstElement() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        assertEquals(sauceMock, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsPreservesOrderSecondElement() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        assertEquals(fillingMock, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectElement() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.removeIngredient(0);
        assertEquals(fillingMock, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientFromStartToEndFirstElement() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.moveIngredient(0, 1);
        assertEquals(fillingMock, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientFromStartToEndSecondElement() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.moveIngredient(0, 1);
        assertEquals(sauceMock, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithBunOnly() {
        burger.setBuns(bunMock);
        float price = burger.getPrice();
        assertEquals(200.0f, price, 0.001f);
    }

    @Test
    public void testGetPriceWithOneIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        float price = burger.getPrice();
        assertEquals(250.0f, price, 0.001f);
    }
    @Test
    public void testGetPriceWithMultipleIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        float price = burger.getPrice();
        assertEquals(325.0f, price, 0.001f);
    }

    @Test
    public void testGetReceiptStartsWithBun() {
        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.startsWith("(==== Complete Bun ====)"));
    }

    @Test
    public void testGetReceiptContainsBun() {
        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Complete Bun ====)"));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 200.000000"));
    }

    @Test
    public void testGetReceiptWithOneIngredientContainsBun() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Complete Bun ====)"));
    }

    @Test
    public void testGetReceiptWithOneIngredientContainsSauce() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= sauce Complete Sauce ="));
    }

    @Test
    public void testGetReceiptWithOneIngredientContainsPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 250.000000"));
    }

    @Test
    public void testGetReceiptWithMultipleIngredientsContainsBun() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Complete Bun ====)"));
    }

    @Test
    public void testGetReceiptWithMultipleIngredientsContainsSauce() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= sauce Complete Sauce ="));
    }

    @Test
    public void testGetReceiptWithMultipleIngredientsContainsFilling() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= filling Complete Filling ="));
    }

    @Test
    public void testGetReceiptWithMultipleIngredientsContainsPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 325.000000"));
    }

    @Test
    public void testGetReceiptContainsLowerCaseType() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("filling"));
    }

    @Test
    public void testGetReceiptDoesNotContainUpperCaseType() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertFalse(receipt.contains("FILLING"));
    }

    @Test
    public void testPriceDoesNotChangeAfterMovingIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        float priceBefore = burger.getPrice();
        burger.moveIngredient(0, 1);
        float priceAfter = burger.getPrice();
        assertEquals(priceBefore, priceAfter, 0.001f);
    }
}