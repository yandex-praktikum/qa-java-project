package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // ========== Тесты для setBuns() ==========

    @Test
    public void setBunsShouldSetBunCorrectly() {
        burger.setBuns(bunMock);
        when(bunMock.getPrice()).thenReturn(100.0f);
        float price = burger.getPrice();
        assertEquals(200.0f, price, 0.01f);
    }

    // ========== Тесты для addIngredient() ==========

    @Test
    public void addIngredientShouldAddOneIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        when(bunMock.getPrice()).thenReturn(50.0f);
        when(ingredientMock1.getPrice()).thenReturn(30.0f);

        assertEquals(130.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void addIngredientShouldAddMultipleIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getPrice()).thenReturn(50.0f);
        when(ingredientMock1.getPrice()).thenReturn(30.0f);
        when(ingredientMock2.getPrice()).thenReturn(40.0f);

        assertEquals(170.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void addIngredientShouldIncreaseIngredientsCount() {
        burger.setBuns(bunMock);

        int initialCount = burger.ingredients.size();
        burger.addIngredient(ingredientMock1);

        assertEquals(initialCount + 1, burger.ingredients.size());
    }

    // ========== Тесты для removeIngredient() ==========

    @Test
    public void removeIngredientShouldRemoveByIndex() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(0);

        when(bunMock.getPrice()).thenReturn(50.0f);
        when(ingredientMock2.getPrice()).thenReturn(40.0f);

        assertEquals(140.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void removeIngredientShouldDecreaseCount() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        int beforeRemove = burger.ingredients.size();
        burger.removeIngredient(1);

        assertEquals(beforeRemove - 1, burger.ingredients.size());
    }

    // ========== Тесты для moveIngredient() ==========

    @Test
    public void moveIngredientShouldSwapPositions() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getName()).thenReturn("Булка");
        when(bunMock.getPrice()).thenReturn(50.0f);

        when(ingredientMock1.getName()).thenReturn("Ингредиент1");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(10.0f);

        when(ingredientMock2.getName()).thenReturn("Ингредиент2");
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getPrice()).thenReturn(20.0f);

        burger.moveIngredient(0, 1);

        String receipt = burger.getReceipt();
        int pos1 = receipt.indexOf("Ингредиент1");
        int pos2 = receipt.indexOf("Ингредиент2");

        assertTrue("Ингредиент2 должен быть раньше Ингредиент1 после перемещения", pos2 < pos1);
    }

    @Test
    public void moveIngredientShouldMaintainListSize() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        int sizeBefore = burger.ingredients.size();
        burger.moveIngredient(0, 1);

        assertEquals(sizeBefore, burger.ingredients.size());
    }

    // ========== Тесты для getPrice() ==========

    @Test
    public void getPriceShouldCountBunsTwice() {
        burger.setBuns(bunMock);
        when(bunMock.getPrice()).thenReturn(75.0f);

        assertEquals(150.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void getPriceShouldSumBunsAndIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        when(bunMock.getPrice()).thenReturn(80.0f);
        when(ingredientMock1.getPrice()).thenReturn(25.0f);

        assertEquals(185.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void getPriceShouldSumMultipleIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getPrice()).thenReturn(60.0f);
        when(ingredientMock1.getPrice()).thenReturn(20.0f);
        when(ingredientMock2.getPrice()).thenReturn(30.0f);

        // 2*60 + 20 + 30 = 170
        assertEquals(170.0f, burger.getPrice(), 0.01f);
    }

    // ========== Тесты для getReceipt() ==========

    @Test
    public void getReceiptShouldReturnCorrectFormat() {
        when(bunMock.getName()).thenReturn("Флюоресцентная булка");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("Соус традиционный");
        when(ingredientMock1.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Флюоресцентная булка ====)"));
        assertTrue(receipt.contains("= sauce Соус традиционный ="));
        assertTrue(receipt.contains("Price: 250"));
    }

    @Test
    public void getReceiptShouldHandleMultipleIngredients() {
        when(bunMock.getName()).thenReturn("Булка");
        when(bunMock.getPrice()).thenReturn(60.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn("Котлета");
        when(ingredientMock1.getPrice()).thenReturn(80.0f);

        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getName()).thenReturn("Кетчуп");
        when(ingredientMock2.getPrice()).thenReturn(20.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Котлета"));
        assertTrue(receipt.contains("Кетчуп"));
        assertTrue(receipt.contains("220"));
    }

    @Test
    public void getReceiptShouldContainBunNameTwice() {
        when(bunMock.getName()).thenReturn("Краторная булка");
        when(bunMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        int firstOccurrence = receipt.indexOf("Краторная булка");
        int secondOccurrence = receipt.indexOf("Краторная булка", firstOccurrence + 1);

        assertTrue("Булка должна появляться дважды в чеке", secondOccurrence > firstOccurrence);
    }

    @Test
    public void getReceiptShouldFormatIngredientTypeCorrectly() {
        when(bunMock.getName()).thenReturn("Булка");
        when(bunMock.getPrice()).thenReturn(50.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn("Начинка");
        when(ingredientMock1.getPrice()).thenReturn(30.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= filling Начинка ="));
    }
}
