package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Юнит-тесты для класса {@link Burger}.
 * Используются моки для Bun и Ingredient, параметризация для проверки расчёта цены.
 * Все магические числа вынесены в константы с понятными именами.
 * Каждый тест проверяет только одно условие (один assert на тест).
 */
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    // ================== Константы для тестовых данных ==================
    private static final float BUN_PRICE_150 = 150.0f;
    private static final float BUN_PRICE_200 = 200.0f;
    private static final float BUN_PRICE_100 = 100.0f;
    private static final float BUN_PRICE_300 = 300.0f;

    private static final float SAUCE_PRICE = 50.0f;
    private static final float FILLING_PRICE = 70.0f;

    private static final String BUN_NAME_BLACK = "black bun";
    private static final String BUN_NAME_RED = "red bun";

    private static final String SAUCE_NAME_HOT = "hot sauce";
    private static final String FILLING_NAME_CUTLET = "cutlet";

    private static final int INDEX_FIRST = 0;
    private static final int INDEX_SECOND = 1;
    private static final int INDEX_THIRD = 2;
    private static final int INDEX_INVALID = 5;

    private static final int EXPECTED_LIST_SIZE_ONE = 1;
    private static final int EXPECTED_LIST_SIZE_TWO = 2;

    private static final float DELTA = 0.001f;

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceIngredient;

    @Mock
    private Ingredient fillingIngredient;

    @Mock
    private Ingredient extraIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    // ================== setBuns ==================
    @Test
    public void setBunsShouldStoreBun() {
        burger.setBuns(bunMock);
        assertEquals("Bun should be set correctly", bunMock, burger.bun);
    }

    // ================== addIngredient ==================
    @Test
    public void addIngredientShouldIncreaseListSize() {
        burger.addIngredient(sauceIngredient);
        assertEquals("List size should be 1 after adding one ingredient",
                EXPECTED_LIST_SIZE_ONE, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldContainAddedIngredient() {
        burger.addIngredient(sauceIngredient);
        assertTrue("Ingredient should be in list", burger.ingredients.contains(sauceIngredient));
    }

    @Test
    public void addIngredientShouldStoreFirstIngredientAtCorrectPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        assertSame("First ingredient should be sauceIngredient",
                sauceIngredient, burger.ingredients.get(INDEX_FIRST));
    }

    @Test
    public void addIngredientShouldStoreSecondIngredientAtCorrectPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        assertSame("Second ingredient should be fillingIngredient",
                fillingIngredient, burger.ingredients.get(INDEX_SECOND));
    }

    // ================== removeIngredient ==================
    @Test
    public void removeIngredientShouldReduceListSize() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.removeIngredient(INDEX_FIRST);
        assertEquals("List size should be 1 after removing one ingredient",
                EXPECTED_LIST_SIZE_ONE, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveCorrectElement() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.removeIngredient(INDEX_FIRST);
        assertSame("Remaining ingredient should be fillingIngredient",
                fillingIngredient, burger.ingredients.get(INDEX_FIRST));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(INDEX_FIRST);
    }

    // ================== moveIngredient ==================
    @Test
    public void moveIngredientShouldPlaceElementAtNewIndexWhenMovingForward() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(extraIngredient);
        burger.moveIngredient(INDEX_FIRST, INDEX_THIRD);
        assertSame("Element moved to index 2 should be sauceIngredient",
                sauceIngredient, burger.ingredients.get(INDEX_THIRD));
    }

    @Test
    public void moveIngredientShouldShiftFirstElementWhenMovingForward() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(extraIngredient);
        burger.moveIngredient(INDEX_FIRST, INDEX_THIRD);
        assertSame("Element at index 0 becomes fillingIngredient",
                fillingIngredient, burger.ingredients.get(INDEX_FIRST));
    }

    @Test
    public void moveIngredientShouldShiftSecondElementWhenMovingForward() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(extraIngredient);
        burger.moveIngredient(INDEX_FIRST, INDEX_THIRD);
        assertSame("Element at index 1 becomes extraIngredient",
                extraIngredient, burger.ingredients.get(INDEX_SECOND));
    }

    @Test
    public void moveIngredientShouldPlaceElementAtNewIndexWhenMovingBackward() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(extraIngredient);
        burger.moveIngredient(INDEX_THIRD, INDEX_FIRST);
        assertSame("Element moved to index 0 should be extraIngredient",
                extraIngredient, burger.ingredients.get(INDEX_FIRST));
    }

    @Test
    public void moveIngredientShouldShiftFirstElementWhenMovingBackward() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(extraIngredient);
        burger.moveIngredient(INDEX_THIRD, INDEX_FIRST);
        assertSame("Element at index 1 should be sauceIngredient",
                sauceIngredient, burger.ingredients.get(INDEX_SECOND));
    }

    @Test
    public void moveIngredientShouldShiftSecondElementWhenMovingBackward() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(extraIngredient);
        burger.moveIngredient(INDEX_THIRD, INDEX_FIRST);
        assertSame("Element at index 2 should be fillingIngredient",
                fillingIngredient, burger.ingredients.get(INDEX_THIRD));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.addIngredient(sauceIngredient);
        burger.moveIngredient(INDEX_INVALID, INDEX_FIRST);
    }

    // ================== getPrice ==================
    @Test
    public void getPriceShouldReturnDoubleBunPriceWhenNoIngredients() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_150);
        burger.setBuns(bunMock);
        float expected = BUN_PRICE_150 * EXPECTED_LIST_SIZE_TWO;
        assertEquals("Price should be 2 * bun price", expected, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceShouldIncludeBunAndIngredientsSum() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_200);
        burger.setBuns(bunMock);
        when(sauceIngredient.getPrice()).thenReturn(SAUCE_PRICE);
        when(fillingIngredient.getPrice()).thenReturn(FILLING_PRICE);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        float expected = BUN_PRICE_200 * EXPECTED_LIST_SIZE_TWO + SAUCE_PRICE + FILLING_PRICE;
        assertEquals("Price should include bun and ingredients", expected, burger.getPrice(), DELTA);
    }

    // Параметризованный тест для getPrice()
    @RunWith(Parameterized.class)
    public static class BurgerPriceParameterizedTest {

        @Parameterized.Parameter(0)
        public float bunPrice;

        @Parameterized.Parameter(1)
        public float[] ingredientPrices;

        @Parameterized.Parameter(2)
        public float expectedTotal;

        @Parameterized.Parameters(name = "Bun: {0}, ingredients: {1} => total: {2}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {100.0f, new float[]{50.0f, 70.0f}, 320.0f},
                    {200.0f, new float[]{100.0f}, 500.0f},
                    {150.0f, new float[]{}, 300.0f},
                    {175.5f, new float[]{25.3f, 30.2f, 10.5f}, 417.5f}
            });
        }

        @Test
        public void getPriceShouldReturnCorrectSumForMultipleScenarios() {
            Burger burger = new Burger();
            Bun bun = mock(Bun.class);
            when(bun.getPrice()).thenReturn(bunPrice);
            burger.setBuns(bun);

            for (float price : ingredientPrices) {
                Ingredient ing = mock(Ingredient.class);
                when(ing.getPrice()).thenReturn(price);
                burger.addIngredient(ing);
            }

            assertEquals(expectedTotal, burger.getPrice(), DELTA);
        }
    }

    // ================== getReceipt ==================
    @Test
    public void getReceiptShouldReturnFormattedStringWithIngredients() {
        when(bunMock.getName()).thenReturn(BUN_NAME_BLACK);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getName()).thenReturn(SAUCE_NAME_HOT);
        when(sauceIngredient.getPrice()).thenReturn(SAUCE_PRICE);

        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getName()).thenReturn(FILLING_NAME_CUTLET);
        when(fillingIngredient.getPrice()).thenReturn(FILLING_PRICE);

        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        String receipt = burger.getReceipt();

        String expected = String.format("(==== black bun ====)%n" +
                "= sauce hot sauce =%n" +
                "= filling cutlet =%n" +
                "(==== black bun ====)%n" +
                "%nPrice: %f%n", 320.0f);
        assertEquals(expected, receipt);
    }

    @Test
    public void getReceiptShouldFormatCorrectlyWithoutIngredients() {
        when(bunMock.getName()).thenReturn(BUN_NAME_RED);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_300);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        String expected = String.format("(==== red bun ====)%n" +
                "(==== red bun ====)%n" +
                "%nPrice: %f%n", 600.0f);
        assertEquals(expected, receipt);
    }
}