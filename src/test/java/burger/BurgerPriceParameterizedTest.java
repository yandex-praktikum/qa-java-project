package burger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/* Параметризованный тест цены. MockitoJUnitRunner не совместим с Parameterized,
поэтому моки создаются через Mockito.mock() в тесте
 */
@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final String scenario;
    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(String scenario, float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
        this.scenario = scenario;
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    //Наборы параметров: цена булки, список цен ингредиентов, ожидаемый итог
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[] {
                        "без ингредиентов — булка x2",
                        50.0f,
                        List.of(),
                        50.0f * 2
                },
                new Object[] {
                        "один ингредиент — булка x2 + начинка",
                        60.0f,
                        List.of(30.0f),
                        60.0f * 2 + 30.0f
                },
                new Object[] {
                        "несколько ингредиентов — начинка и соус складываются",
                        70.0f,
                        List.of(90.0f, 20.0f),
                        70.0f * 2 + 90.0f + 20.0f
                },
                new Object[] {
                        "дробные цены — копейки не теряются и не округляются",
                        25.5f,
                        List.of(10.5f, 8.25f),
                        25.5f * 2 + 10.5f + 8.25f
                },
                new Object[] {
                        "два ингредиента с одинаковой ценой — каждая позиция считается отдельно",
                        40.0f,
                        List.of(15.0f, 15.0f),
                        40.0f * 2 + 15.0f + 15.0f
                }
        );
    }

    @Test
    public void getPriceShouldCalculateBunTwicePlusIngredientsTest() {

        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(mockBun);

        for (float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        //Дельта 0.001f: страховка от накопления погрешности float
        Assert.assertEquals(scenario + ": цена должна быть булка x2 + сумма ингредиентов",
                expectedPrice, burger.getPrice(), 0.001f);
    }
}
