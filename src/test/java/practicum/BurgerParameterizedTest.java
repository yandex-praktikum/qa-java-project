package practicum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import ru.practicum.Ingredient;
import ru.practicum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Параметризованный тестовый класс для проверки различных комбинаций ингредиентов в Burger.
 */
@RunWith(Parameterized.class)
public class BurgerParameterizedTest extends BurgerBaseTest {

    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerParameterizedTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    /**
     * Параметры для параметризованного тестирования различных типов ингредиентов.
     */
    @Parameterized.Parameters(name = "Ингредиент: {1} ({0}), Цена: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 50.0f},
                {IngredientType.SAUCE, "mustard", 30.0f},
                {IngredientType.FILLING, "cheese", 80.0f},
                {IngredientType.FILLING, "cutlet", 100.0f},
                {null, "invalid ingredient", 0.0f}
        });
    }

    /**
     * Дополнительная настройка для параметризованных тестов.
     */
    @Before
    @Override
    public void setUp() {
        super.setUp();
        // Дополнительная настройка для параметризованного теста
    }

    /**
     * Тест проверяет расчет цены для различных типов ингредиентов.
     */
    @Test
    public void testPriceWithVariousIngredients() {
        if (ingredientType != null) {
            Ingredient testIngredient = Mockito.mock(Ingredient.class);
            Mockito.when(testIngredient.getPrice()).thenReturn(ingredientPrice);
            Mockito.when(testIngredient.getName()).thenReturn(ingredientName);
            Mockito.when(testIngredient.getType()).thenReturn(ingredientType);

            burger.setBuns(bun);
            burger.addIngredient(testIngredient);

            float expectedPrice = bun.getPrice() * 2 + ingredientPrice;
            assertThat("Цена должна быть корректной для ингредиента: " + ingredientName,
                    burger.getPrice(), equalTo(expectedPrice));
        }
    }

    /**
     * Тест проверяет формат рецепта для различных типов ингредиентов.
     */
    @Test
    public void testReceiptFormatWithVariousIngredients() {
        if (ingredientType != null) {
            Ingredient testIngredient = Mockito.mock(Ingredient.class);
            Mockito.when(testIngredient.getPrice()).thenReturn(ingredientPrice);
            Mockito.when(testIngredient.getName()).thenReturn(ingredientName);
            Mockito.when(testIngredient.getType()).thenReturn(ingredientType);

            burger.setBuns(bun);
            burger.addIngredient(testIngredient);

            String receipt = burger.getReceipt();
            String expectedLine = "= " + ingredientType.name().toLowerCase() + " " + ingredientName + " =";
            assertThat("Рецепт должен содержать строку с ингредиентом: " + ingredientName,
                    receipt.contains(expectedLine), equalTo(true));
        }
    }
}