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
    private final float priceFirst;
    private final float priceSecond;
    private final float priceThird;
    private final float expectedTotal;

    public BurgerGetPriceParameterizedTest(float priceFirst, float priceSecond, float priceThird, float expectedTotal) {
        this.priceFirst = priceFirst;
        this.priceSecond = priceSecond;
        this.priceThird = priceThird;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {50f, 75f, 25f, 350f},
                {10f, 20f, 30f, 260f},
                {0f, 0f, 0f, 200f},
                {100f, 100f, 100f, 500f}
        });
    }

    @Test
    public void getPriceShouldCalculateCorrectPriceWithDifferentIngredients() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(100f);

        Burger testBurger = new Burger();
        testBurger.setBuns(mockBun);

        Ingredient mockIngFirst = mock(Ingredient.class);
        Ingredient mockIngSecond = mock(Ingredient.class);
        Ingredient mockIngThird = mock(Ingredient.class);

        when(mockIngFirst.getPrice()).thenReturn(priceFirst);
        when(mockIngSecond.getPrice()).thenReturn(priceSecond);
        when(mockIngThird.getPrice()).thenReturn(priceThird);

        testBurger.addIngredient(mockIngFirst);
        testBurger.addIngredient(mockIngSecond);
        testBurger.addIngredient(mockIngThird);
        float result = testBurger.getPrice();

        assertEquals("Цена бургера должна быть " + expectedTotal, expectedTotal, result, 0.001f);
    }
}
