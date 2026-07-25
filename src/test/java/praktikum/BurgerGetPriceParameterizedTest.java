package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {
    private Burger burger;
    private Bun bun;

    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;

    public BurgerGetPriceParameterizedTest(float bunPrice, List<Float> ingredientPrices, float finalPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = finalPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {100f, Arrays.asList(), 200f},  //пустой список
                {100f, Arrays.asList(50f), 250f},  //1 эл в списке
                {200f, Arrays.asList(20f, 30f, 40f), 490f}, //несколько эл-в в списке
                {200f, Arrays.asList(600f, 850f), 1850f}, //высокие цены
                {0f, Arrays.asList(30f, 50f), 80f}, //бесплатная булка
                {100f, Arrays.asList(0f), 200f}, //бесплатные ингридиенты
                {50.5f, Arrays.asList(20.3f, 60.8f), 182.1f} //проверка floating
        };
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        burger = new Burger();
        bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (Float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    }
