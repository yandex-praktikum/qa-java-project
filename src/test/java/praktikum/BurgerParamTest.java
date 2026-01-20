package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static praktikum.TestConstants.*;

@RunWith(Parameterized.class)
public class BurgerParamTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    // Параметры для КАЖДОЙ комбинации
    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingType;
    private final String ingName;
    private final float ingPrice;

    public BurgerParamTest(String bunName, float bunPrice,
                           IngredientType ingType, String ingName, float ingPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingType = ingType;
        this.ingName = ingName;
        this.ingPrice = ingPrice;
    }

    // ВСЕ 3 булочки
    @Parameterized.Parameters(name = "Булочка: {0}, Ингредиент: {3}")
    public static Collection<Object[]> getAllCombinations() {
        return Arrays.asList(new Object[][]{
                // BLACK BUN со всеми ингредиентами
                {BLACK_BUN_NAME, BLACK_BUN_PRICE, IngredientType.SAUCE, HOT_SAUCE_NAME, HOT_SAUCE_PRICE},
                {BLACK_BUN_NAME, BLACK_BUN_PRICE, IngredientType.SAUCE, SOUR_CREAM_NAME, SOUR_CREAM_PRICE},
                {BLACK_BUN_NAME, BLACK_BUN_PRICE, IngredientType.SAUCE, CHILI_SAUCE_NAME, CHILI_SAUCE_PRICE},
                {BLACK_BUN_NAME, BLACK_BUN_PRICE, IngredientType.FILLING, CUTLET_NAME, CUTLET_PRICE},
                {BLACK_BUN_NAME, BLACK_BUN_PRICE, IngredientType.FILLING, DINOSAUR_NAME, DINOSAUR_PRICE},
                {BLACK_BUN_NAME, BLACK_BUN_PRICE, IngredientType.FILLING, SAUSAGE_NAME, SAUSAGE_PRICE},

                // WHITE BUN со всеми ингредиентами
                {WHITE_BUN_NAME, WHITE_BUN_PRICE, IngredientType.SAUCE, HOT_SAUCE_NAME, HOT_SAUCE_PRICE},
                {WHITE_BUN_NAME, WHITE_BUN_PRICE, IngredientType.SAUCE, SOUR_CREAM_NAME, SOUR_CREAM_PRICE},
                {WHITE_BUN_NAME, WHITE_BUN_PRICE, IngredientType.SAUCE, CHILI_SAUCE_NAME, CHILI_SAUCE_PRICE},
                {WHITE_BUN_NAME, WHITE_BUN_PRICE, IngredientType.FILLING, CUTLET_NAME, CUTLET_PRICE},
                {WHITE_BUN_NAME, WHITE_BUN_PRICE, IngredientType.FILLING, DINOSAUR_NAME, DINOSAUR_PRICE},
                {WHITE_BUN_NAME, WHITE_BUN_PRICE, IngredientType.FILLING, SAUSAGE_NAME, SAUSAGE_PRICE},

                // RED BUN со всеми ингредиентами
                {RED_BUN_NAME, RED_BUN_PRICE, IngredientType.SAUCE, HOT_SAUCE_NAME, HOT_SAUCE_PRICE},
                {RED_BUN_NAME, RED_BUN_PRICE, IngredientType.SAUCE, SOUR_CREAM_NAME, SOUR_CREAM_PRICE},
                {RED_BUN_NAME, RED_BUN_PRICE, IngredientType.SAUCE, CHILI_SAUCE_NAME, CHILI_SAUCE_PRICE},
                {RED_BUN_NAME, RED_BUN_PRICE, IngredientType.FILLING, CUTLET_NAME, CUTLET_PRICE},
                {RED_BUN_NAME, RED_BUN_PRICE, IngredientType.FILLING, DINOSAUR_NAME, DINOSAUR_PRICE},
                {RED_BUN_NAME, RED_BUN_PRICE, IngredientType.FILLING, SAUSAGE_NAME, SAUSAGE_PRICE}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getType()).thenReturn(ingType);
        when(ingredient.getName()).thenReturn(ingName);
        when(ingredient.getPrice()).thenReturn(ingPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @Test
    public void testGetPriceForAllCombinations() {
        float expected = bunPrice * 2 + ingPrice;
        assertEquals(String.format("Цена для %s с %s должна быть %.2f", bunName, ingName, expected),
                expected, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceiptForAllCombinations() {
        String typeString = ingType.toString().toLowerCase();
        String expected = String.format(RECEIPT_STRUCTURE,
                bunName, typeString, ingName, bunName, bunPrice * 2 + ingPrice);

        assertEquals(String.format("Чек для %s с %s должен быть корректным", bunName, ingName),
                expected, burger.getReceipt());
    }
}