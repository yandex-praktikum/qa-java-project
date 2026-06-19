package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerGetPriceParameterizedTest(
            float bunPrice,
            float ingredientPrice,
            float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {50f, 20f, 120f},
                {30f, 0f, 60f}
        });
    }

    @Test
    public void shouldCalculatePriceCorrectly() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}

