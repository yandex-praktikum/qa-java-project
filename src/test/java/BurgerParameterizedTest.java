import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedTotal;

    public BurgerParameterizedTest(float bunPrice, float[] ingredientPrices, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "bun={0}, ingredients={1}, total={2}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {100f, new float[]{}, 200f},
                {80f, new float[]{50f}, 210f},
                {90f, new float[]{30f, 40f}, 250f},
                {70f, new float[]{10f, 20f, 30f}, 200f}
        });
    }

    @Test
    public void testBurgerPriceCalculation() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);

        for (float price :
                ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);

            burger.addIngredient(ingredient);
        }

        assertEquals(expectedTotal, burger.getPrice(), 0.01f);
    }
}
