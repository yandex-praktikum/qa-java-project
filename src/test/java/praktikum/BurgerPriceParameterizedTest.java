package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

//Тест для проверки корректности расчета цены бургера
@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private Burger burger;
    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedTotal;

    public BurgerPriceParameterizedTest(float bunPrice, List<Float> ingredientPrices, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotal = expectedTotal;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    //Набор данных для тестов: цена булки, список цен ингредиентов, ожидаемая сумма

    @Parameterized.Parameters(name = "bun={0}, ings={1}, expected={2}")
    public static Iterable<Object[]> priceScenarios() {
        return Arrays.asList(new Object[][]{
                // Только булки, без начинок
                {TestData.PRICE_BUN_BASIC, List.of(), 2 * TestData.PRICE_BUN_BASIC},

                //Булки + 2 начинки
                {TestData.PRICE_BUN_BASIC, Arrays.asList(TestData.PRICE_ING_SAUCY, TestData.PRICE_ING_HEARTY),
                        2 * TestData.PRICE_BUN_BASIC + TestData.PRICE_ING_SAUCY + TestData.PRICE_ING_HEARTY},

                //Бесплатная булка (0.0f), только начинки
                {0f, Arrays.asList(TestData.PRICE_ING_EXOTIC, TestData.PRICE_ING_EXOTIC, TestData.PRICE_ING_EXOTIC),
                        3 * TestData.PRICE_ING_EXOTIC},

                // Дорогая булка + 1 начинка
                {TestData.PRICE_BUN_DELUXE, List.of(TestData.PRICE_ING_EXOTIC),
                        2 * TestData.PRICE_BUN_DELUXE + TestData.PRICE_ING_EXOTIC}
        });
    }

    @Test
    public void shouldCalculateCorrectTotalPrice() {
        // Булка с заданной ценой
        Bun bun = TestData.createBun("testBun", bunPrice);
        burger.setBuns(bun);

        // Добавляем ингредиенты
        for (float price : ingredientPrices) {
            burger.addIngredient(TestData.createIngredient(IngredientType.FILLING, "testIng", price));
        }

        // Сравниваем расчетную цену с ожидаемой
        assertEquals("Рассчитанная цена должна совпадать с ожидаемой для данного набора данных",
                expectedTotal, burger.getPrice(), TestData.FLOAT_DELTA);
    }
}