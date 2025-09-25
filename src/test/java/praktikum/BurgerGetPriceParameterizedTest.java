package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedTotal;

    private Burger burger;
    private Bun bunMock;
    private List<Ingredient> ingredientMocks;

    public BurgerGetPriceParameterizedTest(
            String name,
            float bunPrice,
            List<Float> ingredientPrices,
            float expectedTotal
    ) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotal = expectedTotal;
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        ingredientMocks = new ArrayList<>();
        for (Float price : ingredientPrices) {
            Ingredient ing = mock(Ingredient.class);
            when(ing.getPrice()).thenReturn(price);
            burger.addIngredient(ing);
            ingredientMocks.add(ing);
        }
    }

    @Parameterized.Parameters(name = "{index}: {0} | bun={1} | ing={2} | total={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"бургер без ингредиентов", 100f, Collections.<Float>emptyList(), 200f},
                {"бургер с двумя ингредиентами", 100f, Arrays.asList(50f, 20f), 270f},
                {"все значения нули", 0f, Arrays.asList(0f, 0f, 0f), 0f},
                {"дробные значения", 7.5f, Arrays.asList(1.25f, 2.25f), 18.5f}
        });
    }

    @Test
    public void getPriceShouldReturnExpectedTotal() {
        float total = burger.getPrice();
        assertEquals(expectedTotal, total, 1e-6f);
    }

    @Test
    public void getPriceShouldQueryBunAndEachIngredientOnce() {
        burger.getPrice();
        verify(bunMock, times(1)).getPrice();
        for (Ingredient ing : ingredientMocks) {
            verify(ing, times(1)).getPrice();
        }
    }
}
