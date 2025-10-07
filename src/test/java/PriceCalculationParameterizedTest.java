import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class PriceCalculationParameterizedTest {

    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private final float expectedTotalPrice;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauceIngredient;
    @Mock
    private Ingredient fillingIngredient;

    private Burger burger;

    public PriceCalculationParameterizedTest(float bunPrice, float saucePrice,
                                             float fillingPrice, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "Булка: {0}, Соус: {1}, Начинка: {2}, Итог: {3}")
    public static Object[][] data() {
        return new Object[][]{
                {100.0f, 0.0f, 0.0f, 200.0f},
                {100.0f, 50.0f, 0.0f, 250.0f},
                {0.0f, 25.0f, 35.0f, 60.0f},
                {80.0f, 0.0f, 40.0f, 200.0f},
                {45.5f, 15.5f, 25.5f, 132.0f}
        };
    }

    @Test
    public void testGetPriceWithDifferentPriceCombinations() {

        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauceIngredient.getPrice()).thenReturn(saucePrice);
        when(fillingIngredient.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);


        float actualPrice = burger.getPrice();

        assertEquals("Цена должна: " + expectedTotalPrice,
                expectedTotalPrice, actualPrice, 0.001f);
    }
}
