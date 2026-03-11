import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceCalculationTest {

    private final int bunPrice;
    private final int ingredientPrice1;
    private final int ingredientPrice2;
    private final float expectedTotal;
    private final String testDescription;

    public BurgerPriceCalculationTest(String testDescription, int bunPrice, int ingredientPrice1,
                                      int ingredientPrice2, float expectedTotal) {
        this.testDescription = testDescription;
        this.bunPrice = bunPrice;
        this.ingredientPrice1 = ingredientPrice1;
        this.ingredientPrice2 = ingredientPrice2;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Бургер со стандартными ценами", 100, 50, 75, 325},
                {"Бургер с дорогой булочкой и бесплатным ингредиентом", 200, 0, 100, 500},
                {"Бургер с дешевыми компонентами", 50, 25, 25, 150},
                {"Бесплатный бургер", 0, 0, 0, 0},
                {"Бургер с отрицательной ценой ингредиента", 100, -20, 50, 230},
        });
    }

    @Test
    public void testPriceCalculationWithDifferentPrices() {
        // Создаем моки
        Bun mockBun = mock(Bun.class);
        Ingredient mockIngredient1 = mock(Ingredient.class);
        Ingredient mockIngredient2 = mock(Ingredient.class);

        // Настраиваем моки
        when(mockBun.getPrice()).thenReturn((float) bunPrice);
        when(mockIngredient1.getPrice()).thenReturn((float) ingredientPrice1);
        when(mockIngredient2.getPrice()).thenReturn((float) ingredientPrice2);

        // Создаем и настраиваем бургер
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Проверяем расчет цены
        float actualPrice = burger.getPrice();
        assertEquals("Неверный расчет цены для теста: " + testDescription,
                expectedTotal, actualPrice, 0.01);
    }
}