import org.junit.Before;
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
public class BurgerGetPriceTest {

    private Burger burger;
    private float bunPrice;
    private float[] ingredientsPrices;
    private float expectedPrice;

    public BurgerGetPriceTest(float bunPrice, float[] ingredientsPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientsPrices = ingredientsPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2.0f, new float[]{}, 4.0f},
                {1.5f, new float[]{0.5f, 1.0f}, 1.5f * 2 + 1.5f},
                {3.0f, new float[]{2.0f, 2.0f, 2.0f}, 3.0f * 2 + 6.0f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        for (float price : ingredientsPrices) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(price);
            burger.addIngredient(mockIngredient);
        }
    }

    @Test
    public void testGetPriceReturnCorrectSum() {
        System.out.println(expectedPrice + " = " + burger.getPrice());
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}