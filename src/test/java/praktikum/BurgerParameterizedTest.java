package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    // Константы для ингредиентов
    private static final String BUN_NAME = "Булочка";
    private static final float BUN_PRICE = 50.0f;
    private static final float BUN_MULTIPLIER = 2.0f;

    private static final String INGREDIENT_1_NAME = "Сыр";
    private static final IngredientType INGREDIENT_1_TYPE = IngredientType.FILLING;
    private static final float INGREDIENT_1_PRICE = 40.0f;

    private static final String INGREDIENT_2_NAME = "Соус";
    private static final IngredientType INGREDIENT_2_TYPE = IngredientType.SAUCE;
    private static final float INGREDIENT_2_PRICE = 20.0f;

    private static final String INGREDIENT_3_NAME = "Котлета";
    private static final IngredientType INGREDIENT_3_TYPE = IngredientType.FILLING;
    private static final float INGREDIENT_3_PRICE = 60.0f;

    // Константы для тестовых данных
    private static final int EXPECTED_INGREDIENT_COUNT_AFTER_REMOVE = 2;
    private static final int FIRST_INGREDIENT_INDEX = 0;
    private static final int SECOND_INGREDIENT_INDEX = 1;
    private static final int THIRD_INGREDIENT_INDEX = 2;
    private static final int INITIAL_INGREDIENT_COUNT = 3;

    private final int indexToRemove;
    private final int expectedSizeAfterRemove;

    public BurgerParameterizedTest(int indexToRemove, int expectedSizeAfterRemove) {
        this.indexToRemove = indexToRemove;
        this.expectedSizeAfterRemove = expectedSizeAfterRemove;
    }

    @Parameterized.Parameters(name = "Удаление ингредиента с индекса {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {FIRST_INGREDIENT_INDEX, EXPECTED_INGREDIENT_COUNT_AFTER_REMOVE},
                {SECOND_INGREDIENT_INDEX, EXPECTED_INGREDIENT_COUNT_AFTER_REMOVE},
                {THIRD_INGREDIENT_INDEX, EXPECTED_INGREDIENT_COUNT_AFTER_REMOVE}
        });
    }

    @Before
    public void setUp() {
        Bun mockBun = mock(Bun.class);
        Ingredient mockIngredient1 = mock(Ingredient.class);
        Ingredient mockIngredient2 = mock(Ingredient.class);
        Ingredient mockIngredient3 = mock(Ingredient.class);

        burger = new Burger();

        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockBun.getName()).thenReturn(BUN_NAME);

        when(mockIngredient1.getName()).thenReturn(INGREDIENT_1_NAME);
        when(mockIngredient1.getType()).thenReturn(INGREDIENT_1_TYPE);
        when(mockIngredient1.getPrice()).thenReturn(INGREDIENT_1_PRICE);

        when(mockIngredient2.getName()).thenReturn(INGREDIENT_2_NAME);
        when(mockIngredient2.getType()).thenReturn(INGREDIENT_2_TYPE);
        when(mockIngredient2.getPrice()).thenReturn(INGREDIENT_2_PRICE);

        when(mockIngredient3.getName()).thenReturn(INGREDIENT_3_NAME);
        when(mockIngredient3.getType()).thenReturn(INGREDIENT_3_TYPE);
        when(mockIngredient3.getPrice()).thenReturn(INGREDIENT_3_PRICE);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
    }

    @Test
    public void testRemoveIngredientShouldDecreaseSizeForAnyIndex() {
        burger.removeIngredient(indexToRemove);

        assertEquals("После удаления ингредиента количество должно уменьшиться на 1",
                expectedSizeAfterRemove, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientShouldHaveCorrectInitialSize() {
        assertEquals("Начальное количество ингредиентов должно быть 3",
                INITIAL_INGREDIENT_COUNT, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientShouldChangeSizeForAnyIndex() {
        int initialSize = burger.ingredients.size();

        burger.removeIngredient(indexToRemove);

        assertNotEquals("Количество ингредиентов должно измениться после удаления",
                initialSize, burger.ingredients.size());
    }

    private float calculateExpectedPriceAfterRemoval(int removedIndex) {
        float baseBunPrice = BUN_PRICE * BUN_MULTIPLIER;
        float totalIngredientsPrice = INGREDIENT_1_PRICE + INGREDIENT_2_PRICE + INGREDIENT_3_PRICE;
        float expectedPrice = baseBunPrice + totalIngredientsPrice;

        if (removedIndex == FIRST_INGREDIENT_INDEX) {
            expectedPrice -= INGREDIENT_1_PRICE;
        } else if (removedIndex == SECOND_INGREDIENT_INDEX) {
            expectedPrice -= INGREDIENT_2_PRICE;
        } else if (removedIndex == THIRD_INGREDIENT_INDEX) {
            expectedPrice -= INGREDIENT_3_PRICE;
        }

        return expectedPrice;
    }

    @Test
    public void testGetPriceShouldDecreaseAfterRemovalForAnyIndex() {
        float priceBefore = burger.getPrice();

        burger.removeIngredient(indexToRemove);
        float priceAfter = burger.getPrice();

        assertTrue("Цена после удаления ингредиента должна быть меньше исходной",
                priceAfter < priceBefore);
    }

    @Test
    public void testGetPriceShouldCalculateCorrectPriceAfterRemovalForAnyIndex() {
        burger.removeIngredient(indexToRemove);
        float priceAfter = burger.getPrice();

        float expectedPrice = calculateExpectedPriceAfterRemoval(indexToRemove);

        assertEquals("Цена после удаления ингредиента рассчитана неверно",
                expectedPrice, priceAfter, 0.001f);
    }

    @Test
    public void testGetPriceShouldCalculateCorrectPriceForFullBurger() {
        float fullPrice = burger.getPrice();
        float expectedFullPrice = BUN_PRICE * BUN_MULTIPLIER +
                INGREDIENT_1_PRICE +
                INGREDIENT_2_PRICE +
                INGREDIENT_3_PRICE;

        assertEquals("Цена полного бургера рассчитана неверно",
                expectedFullPrice, fullPrice, 0.001f);
    }
}