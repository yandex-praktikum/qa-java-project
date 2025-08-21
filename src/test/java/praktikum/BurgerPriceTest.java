package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final Float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;

    @Mock
    private Bun mockBun;

    private Burger burger;

    public BurgerPriceTest(Float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "Стоимость булочки: {0}, Наличие ингредиентов: {1}, Итоговая цена: {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {100f, Arrays.asList(50f, 30f), 280f},
                {150f, new ArrayList<>(), 300f},
                {0f, Arrays.asList(0f), 0f}
        };
    }

    @Test
    public void getPriceTest() {
        if (bunPrice != null) {
            when(mockBun.getPrice()).thenReturn(bunPrice);
            burger.setBuns(mockBun);
        }

        for (Float price : ingredientPrices) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}
