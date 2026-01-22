package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final String testName;
    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(String testName, float bunPrice,
                                        List<Float> ingredientPrices, float expectedPrice) {

        this.testName = testName;
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;

    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{

                {"Только булочка", 100.0f, List.of(), 200.0f},
                {"Булочка + 1 ингредиент", 150.0f, List.of(75.0f), 375.0f},
                {"Булочка + 2 ингредиента", 150.0f, Arrays.asList(75.0f, 50.0f), 425.0f},
                {"Булочка + 3 ингредиента", 150.0f, Arrays.asList(75.0f, 50.0f, 100.0f), 525.0f},
                {"Без булочки", 0.0f, Arrays.asList(100.0f, 200.0f), 300.0f}

        });
    }

    @Test
    public void getPrice_shouldCalculateCorrectly() {

        Burger burger = new Burger();

        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        for (float price : ingredientPrices) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(price);
            burger.addIngredient(mockIngredient);
        }

        float actualPrice = burger.getPrice();

        Assert.assertEquals(
                String.format("Тест '%s' не прошел: ожидалось %.2f, получено %.2f",
                        testName, expectedPrice, actualPrice),
                expectedPrice,
                actualPrice,
                0.001f
        );
    }
}
