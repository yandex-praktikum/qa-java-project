package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Bun: {0}, Ingredients: {1}, Expected: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100, new float[]{}, 200},
                {150, new float[]{50}, 350},
                {200, new float[]{100, 150}, 650}
        });
    }

    @Test
    public void shouldCalculateCorrectPrice() {
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0);
    }
}
