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
    public float expectedPrice;

    @Parameterized.Parameters(name = "Bun price: {0}, Ingredients: {1}, Expected: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, new float[]{}, 200f},
                {100f, new float[]{50f}, 250f},
                {100f, new float[]{50f, 75f}, 325f},
                {100f, new float[]{50f, 75f, 25f}, 350f},
                {50f, new float[]{10f, 20f, 30f}, 160f},
                {200f, new float[]{100f, 150f, 200f}, 850f},
                {0f, new float[]{0f, 0f}, 0f},
        });
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        for (float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        float actual = burger.getPrice();
        assertEquals(expectedPrice, actual, 0.01f);
    }
}

