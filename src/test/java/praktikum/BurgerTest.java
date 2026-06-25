package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private static final int BUNS_COUNT = 2;
    private static final int SINGLE_INGREDIENT = 1;
    private static final float DELTA = 0.001f;

    private static final float BUN_PRICE = 100.0f;
    private static final float BUN_PRICE_HIGH = 150.0f;
    private static final float BUN_PRICE_WHITE = 200.0f;
    private static final float INGREDIENT_PRICE_SAUCE = 50.0f;
    private static final float INGREDIENT_PRICE_CUTLET = 75.0f;
    private static final float INGREDIENT_PRICE_HOT_SAUCE = 100.0f;
    private static final float INGREDIENT_PRICE_CHICKEN = 100.0f;

    private static final String BUN_NAME_BLACK = "black bun";
    private static final String BUN_NAME_WHITE = "white bun";
    private static final String INGREDIENT_NAME_HOT_SAUCE = "hot sauce";
    private static final String INGREDIENT_NAME_CUTLET = "cutlet";

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    // ============ ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ============

    /**
     * Создает бургер с мок-ингредиентами для тестирования
     */
    private Burger createBurgerWithMocks() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        return burger;
    }

    /**
     * Создает бургер с реальными объектами для тестирования
     */
    private Burger createBurgerWithRealObjects() {
        Bun realBun = new Bun(BUN_NAME_BLACK, BUN_PRICE);
        Ingredient realIngredient1 = new Ingredient(
                IngredientType.SAUCE,
                INGREDIENT_NAME_HOT_SAUCE,
                INGREDIENT_PRICE_SAUCE
        );
        Ingredient realIngredient2 = new Ingredient(
                IngredientType.FILLING,
                INGREDIENT_NAME_CUTLET,
                INGREDIENT_PRICE_CUTLET
        );

        Burger burger = new Burger();
        burger.setBuns(realBun);
        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);
        return burger;
    }

    /**
     * Создает бургер только с булочкой (без ингредиентов)
     */
    private Burger createBurgerWithBunOnly() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        return burger;
    }

    /**
     * Настраивает моки для бургера с ингредиентами
     */
    private void setupMocksForBurgerWithIngredients() {
        when(mockBun.getName()).thenReturn(BUN_NAME_BLACK);
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn(INGREDIENT_NAME_HOT_SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(INGREDIENT_PRICE_HOT_SAUCE);
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn(INGREDIENT_NAME_CUTLET);
        when(mockIngredient2.getPrice()).thenReturn(INGREDIENT_PRICE_CHICKEN);
    }

    /**
     * Настраивает моки для бургера без ингредиентов
     */
    private void setupMocksForBurgerWithoutIngredients() {
        when(mockBun.getName()).thenReturn(BUN_NAME_WHITE);
        when(mockBun.getPrice()).thenReturn(BUN_PRICE_WHITE);
    }

    /**
     * Вычисляет ожидаемую цену бургера с ингредиентами
     */
    private float calculateExpectedPriceWithIngredients(float ing1Price, float ing2Price) {
        return BUN_PRICE * BUNS_COUNT + ing1Price + ing2Price;
    }

    // ============ ТЕСТЫ ============

    // --- Тесты для setBuns ---
    @Test
    public void setBunsShouldSetBun() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    // --- Тесты для addIngredient ---
    @Test
    public void addIngredientShouldIncreaseSize() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        assertEquals(SINGLE_INGREDIENT, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }

    // --- Тесты для getPrice ---
    @Test
    public void getPriceShouldCalculateCorrectlyWithIngredients() {
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockIngredient1.getPrice()).thenReturn(INGREDIENT_PRICE_HOT_SAUCE);
        when(mockIngredient2.getPrice()).thenReturn(INGREDIENT_PRICE_CUTLET);

        Burger burger = createBurgerWithMocks();

        float expectedPrice = calculateExpectedPriceWithIngredients(
                INGREDIENT_PRICE_HOT_SAUCE,
                INGREDIENT_PRICE_CUTLET
        );

        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceShouldCallBunGetPriceOnce() {
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockIngredient1.getPrice()).thenReturn(INGREDIENT_PRICE_HOT_SAUCE);
        when(mockIngredient2.getPrice()).thenReturn(INGREDIENT_PRICE_CUTLET);

        Burger burger = createBurgerWithMocks();
        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getPriceShouldCallIngredientGetPriceOnce() {
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockIngredient1.getPrice()).thenReturn(INGREDIENT_PRICE_HOT_SAUCE);
        when(mockIngredient2.getPrice()).thenReturn(INGREDIENT_PRICE_CUTLET);

        Burger burger = createBurgerWithMocks();
        burger.getPrice();

        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void getPriceShouldCalculateCorrectlyWithoutIngredients() {
        when(mockBun.getPrice()).thenReturn(BUN_PRICE_HIGH);

        Burger burger = createBurgerWithBunOnly();

        float expectedPrice = BUN_PRICE_HIGH * BUNS_COUNT;
        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceShouldCalculateCorrectlyWithRealObjects() {
        Burger burger = createBurgerWithRealObjects();

        float expectedPrice = calculateExpectedPriceWithIngredients(
                INGREDIENT_PRICE_SAUCE,
                INGREDIENT_PRICE_CUTLET
        );

        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    // --- Тесты для getReceipt ---
    @Test
    public void getReceiptShouldContainBunWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("(==== " + BUN_NAME_BLACK + " ====)"));
    }

    @Test
    public void getReceiptShouldContainSauceWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("= sauce " + INGREDIENT_NAME_HOT_SAUCE + " ="));
    }

    @Test
    public void getReceiptShouldContainFillingWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("= filling " + INGREDIENT_NAME_CUTLET + " ="));
    }

    @Test
    public void getReceiptShouldContainPriceWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        String actualReceipt = burger.getReceipt();

        float expectedPrice = calculateExpectedPriceWithIngredients(
                INGREDIENT_PRICE_HOT_SAUCE,
                INGREDIENT_PRICE_CHICKEN
        );
        String expectedPriceString = String.valueOf((int) expectedPrice);

        assertTrue(actualReceipt.contains("Price:"));
        assertTrue(actualReceipt.contains(expectedPriceString));
    }

    @Test
    public void getReceiptShouldCallBunGetNameTwiceWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        burger.getReceipt();

        verify(mockBun, times(2)).getName();
    }

    @Test
    public void getReceiptShouldCallBunGetPriceOnceWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        burger.getReceipt();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldCallIngredientMethodsWithIngredients() {
        setupMocksForBurgerWithIngredients();
        Burger burger = createBurgerWithMocks();

        burger.getReceipt();

        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldContainBunWithoutIngredients() {
        setupMocksForBurgerWithoutIngredients();
        Burger burger = createBurgerWithBunOnly();

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("(==== " + BUN_NAME_WHITE + " ====)"));
    }

    @Test
    public void getReceiptShouldContainPriceWithoutIngredients() {
        setupMocksForBurgerWithoutIngredients();
        Burger burger = createBurgerWithBunOnly();

        String actualReceipt = burger.getReceipt();

        float expectedPrice = BUN_PRICE_WHITE * BUNS_COUNT;
        String expectedPriceString = String.valueOf((int) expectedPrice);

        assertTrue(actualReceipt.contains("Price:"));
        assertTrue(actualReceipt.contains(expectedPriceString));
    }

    @Test
    public void getReceiptShouldCallBunMethodsWithoutIngredients() {
        setupMocksForBurgerWithoutIngredients();
        Burger burger = createBurgerWithBunOnly();

        burger.getReceipt();

        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldContainBunWithRealObjects() {
        Burger burger = createBurgerWithRealObjects();

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== " + BUN_NAME_BLACK + " ====)"));
    }

    @Test
    public void getReceiptShouldContainSauceWithRealObjects() {
        Burger burger = createBurgerWithRealObjects();

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce " + INGREDIENT_NAME_HOT_SAUCE + " ="));
    }

    @Test
    public void getReceiptShouldContainFillingWithRealObjects() {
        Burger burger = createBurgerWithRealObjects();

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= filling " + INGREDIENT_NAME_CUTLET + " ="));
    }

    @Test
    public void getReceiptShouldContainCorrectPriceWithRealObjects() {
        Burger burger = createBurgerWithRealObjects();

        String receipt = burger.getReceipt();

        float expectedPrice = calculateExpectedPriceWithIngredients(
                INGREDIENT_PRICE_SAUCE,
                INGREDIENT_PRICE_CUTLET
        );
        String expectedPriceString = String.valueOf((int) expectedPrice);

        assertTrue(receipt.contains("Price:"));
        assertTrue(receipt.contains(expectedPriceString));
    }
}