package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParamTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceParamTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тест цены: булка({0}) + ингредиент({1}) = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},   // (100*2) + 50
                {200f, 100f, 500f},  // (200*2) + 100
                {0f, 50f, 50f},      // (0*2) + 50
                {300.5f, 10.5f, 611.5f} // (300.5*2) + 10.5
        });
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals("Расчет стоимости бургера неверен", expectedPrice, burger.getPrice(), 0.001f);
    }
}