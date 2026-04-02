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
public class BurgerPriceParameterizedTest {

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float[] ingredientPrices;

    @Parameterized.Parameter(2)
    public float expected;

    @Parameterized.Parameters(name = "bun={0}, ingredients={1} => price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0f, new float[]{}, 0f},
                {100f, new float[]{}, 200f},
                {100f, new float[]{50f}, 250f},
                {200f, new float[]{100f, 200f, 300f}, 1000f},
        });
    }

    @Test
    public void getPriceCalculatesBunDoublePlusIngredientsSum() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);

        for (float ingredientPrice : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(ingredientPrice);
            burger.addIngredient(ingredient);
        }

        assertEquals(expected, burger.getPrice(), 0.0001f);
    }
}
