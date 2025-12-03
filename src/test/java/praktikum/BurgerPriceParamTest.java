package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParamTest {

    private final float bunPrice;
    private final float ing1Price;
    private final float ing2Price;

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    public BurgerPriceParamTest(float bunPrice, float ing1Price, float ing2Price) {
        this.bunPrice = bunPrice;
        this.ing1Price = ing1Price;
        this.ing2Price = ing2Price;
    }

    @Parameterized.Parameters(name = "bun={0}, ing1={1}, ing2={2}")
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {100f, 20f, 40f},
                {50f, 5f, 5f},
                {1.5f, 0.5f, 0.7f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(bunMock.getName()).thenReturn("white bun");

        when(ingredientMock1.getPrice()).thenReturn(ing1Price);
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn("dinosaur");

        when(ingredientMock2.getPrice()).thenReturn(ing2Price);
        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getName()).thenReturn("sour cream");
    }

    @Test
    public void testGetPriceParameterized() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expected = bunPrice * 2 + ing1Price + ing2Price;
        assertEquals(expected, burger.getPrice(), 0.0001);
    }
}
