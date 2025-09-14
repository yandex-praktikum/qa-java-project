package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static praktikum.TestConstants.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientSauceMock;

    @Mock
    private Ingredient ingredientFillingMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // ====== ТЕСТЫ ДОБАВЛЕНИЯ ИНГРЕДИЕНТОВ ======

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(ingredientSauceMock);
        assertEquals(SIZE_ONE, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientStoresCorrectIngredient() {
        burger.addIngredient(ingredientSauceMock);
        assertEquals(ingredientSauceMock, burger.ingredients.get(INDEX_ZERO));
    }

    @Test
    public void testAddMultipleIngredientsSize() {
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        assertEquals(SIZE_TWO, burger.ingredients.size());
    }

    @Test
    public void testInitialIngredientsNotNull() {
        assertNotNull(burger.ingredients);
    }

    @Test
    public void testInitialIngredientsSize() {
        assertEquals(SIZE_ZERO, burger.ingredients.size());
    }

    // ====== ТЕСТЫ УДАЛЕНИЯ ИНГРЕДИЕНТОВ ======

    @Test
    public void testRemoveIngredientSize() {
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        burger.removeIngredient(INDEX_ZERO);

        assertEquals(SIZE_ONE, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemainingIngredient() {
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        burger.removeIngredient(INDEX_ZERO);

        assertEquals(ingredientFillingMock, burger.ingredients.get(INDEX_ZERO));
    }

    @Test
    public void testRemoveSingleIngredient() {
        burger.addIngredient(ingredientSauceMock);

        burger.removeIngredient(INDEX_ZERO);

        assertEquals(SIZE_ZERO, burger.ingredients.size());
    }

    // ====== ТЕСТЫ ПЕРЕМЕЩЕНИЯ ИНГРЕДИЕНТОВ ======

    @Test
    public void testMoveIngredientSize() {
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        burger.moveIngredient(INDEX_ZERO, INDEX_ONE);

        assertEquals(SIZE_TWO, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientInTwoElementListFirstPosition() {
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        burger.moveIngredient(INDEX_ZERO, INDEX_ONE);

        assertEquals(ingredientFillingMock, burger.ingredients.get(INDEX_ZERO));
    }

    @Test
    public void testMoveIngredientInTwoElementListSecondPosition() {
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        burger.moveIngredient(INDEX_ZERO, INDEX_ONE);

        assertEquals(ingredientSauceMock, burger.ingredients.get(INDEX_ONE));
    }

    // ====== ТЕСТЫ РАСЧЕТА СТОИМОСТИ ======

    @Test
    public void testGetPriceOnlyBun() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        assertEquals(EXPECTED_PRICE_200, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithOneIngredient() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_50);
        when(ingredientSauceMock.getPrice()).thenReturn(INGREDIENT_PRICE_25);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientSauceMock);

        assertEquals(EXPECTED_PRICE_125, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientSauceMock.getPrice()).thenReturn(INGREDIENT_PRICE_50);
        when(ingredientFillingMock.getPrice()).thenReturn(INGREDIENT_PRICE_30);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientSauceMock);
        burger.addIngredient(ingredientFillingMock);

        assertEquals(EXPECTED_PRICE_280, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithZeroPrices() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_ZERO);
        when(ingredientSauceMock.getPrice()).thenReturn(INGREDIENT_PRICE_ZERO);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientSauceMock);

        assertEquals(EXPECTED_PRICE_ZERO, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceCalculatesCorrectBunPrice() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_75);
        burger.setBuns(bunMock);

        // Цена булочки должна умножаться на 2 (верхняя и нижняя части)
        assertEquals(150.0f, burger.getPrice(), DELTA);
    }

    // ====== ТЕСТЫ ПОЛУЧЕНИЯ ЧЕКА ======

    @Test
    public void testGetReceiptEmptyBurgerNotNull() {
        when(bunMock.getName()).thenReturn(BUN_NAME_TEST);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
    }

    @Test
    public void testGetReceiptEmptyBurgerContainsBun() {
        when(bunMock.getName()).thenReturn(BUN_NAME_TEST);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(RECEIPT_BUN_PREFIX + BUN_NAME_TEST + RECEIPT_BUN_SUFFIX));
    }

    @Test
    public void testGetReceiptEmptyBurgerContainsPrice() {
        when(bunMock.getName()).thenReturn(BUN_NAME_TEST);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_200_STR));
    }

    @Test
    public void testGetReceiptWithSauceIngredientContainsSauce() {
        when(bunMock.getName()).thenReturn(BUN_NAME_FLUOR);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_150);
        when(ingredientSauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientSauceMock.getName()).thenReturn(SAUCE_SPICY);
        when(ingredientSauceMock.getPrice()).thenReturn(INGREDIENT_PRICE_25);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientSauceMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(RECEIPT_SAUCE_PREFIX + SAUCE_SPICY + RECEIPT_INGREDIENT_SUFFIX));
    }

    @Test
    public void testGetReceiptWithFillingIngredientContainsFilling() {
        when(bunMock.getName()).thenReturn(BUN_NAME_CRATER);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientSauceMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientSauceMock.getName()).thenReturn(FILLING_PROTOSTOMIA);
        when(ingredientSauceMock.getPrice()).thenReturn(INGREDIENT_PRICE_75);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientSauceMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(RECEIPT_FILLING_PREFIX + FILLING_PROTOSTOMIA + RECEIPT_INGREDIENT_SUFFIX));
    }

    @Test
    public void testGetReceiptFormattingFirstLine() {
        when(bunMock.getName()).thenReturn(BUN_NAME_GENERIC);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientSauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientSauceMock.getName()).thenReturn(SAUCE_TEST);
        when(ingredientSauceMock.getPrice()).thenReturn(INGREDIENT_PRICE_50);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientSauceMock);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");

        assertEquals("(==== " + BUN_NAME_GENERIC + " ====)", lines[INDEX_ZERO].trim());
    }

    // ====== ТЕСТЫ УСТАНОВКИ БУЛОЧЕК ======

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testSetBunsReplacesPreviousBunCorrectBun() {
        Bun firstBunMock = bunMock;
        Bun secondBunMock = mock(Bun.class);

        burger.setBuns(firstBunMock);
        burger.setBuns(secondBunMock);

        assertEquals(secondBunMock, burger.bun);
    }

    @Test
    public void testInitialBunState() {
        assertNull(burger.bun);
    }
}