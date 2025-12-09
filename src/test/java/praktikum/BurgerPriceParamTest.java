package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParamTest {

    private final float bunPrice;
    private final float ingredient1Price;
    private final float ingredient2Price;
    private final float expectedTotal;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    private Burger burger;

    public BurgerPriceParamTest(float bunPrice, float ingredient1Price, float ingredient2Price, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "Булка={0}, Ингр1={1}, Ингр2={2} -> Итого={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100.0f, 50.0f, 30.0f, 280.0f},   // 2*100 + 50 + 30 = 280
                {80.0f, 0.0f, 20.0f, 180.0f},     // 2*80 + 0 + 20 = 180
                {50.0f, 25.0f, 25.0f, 150.0f},    // 2*50 + 25 + 25 = 150
                {120.0f, 60.0f, 40.0f, 340.0f}    // 2*120 + 60 + 40 = 340
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testGetPriceWithVariousCombinations() {
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock1.getPrice()).thenReturn(ingredient1Price);
        when(ingredientMock2.getPrice()).thenReturn(ingredient2Price);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        assertEquals(expectedTotal, burger.getPrice(), 0.01f);
    }
}
