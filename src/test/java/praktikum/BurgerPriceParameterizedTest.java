package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private Burger burger;
    private Bun bunMock;
    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(List<Ingredient> ingredients, float expectedPrice) {
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Ингредиенты: {0}, ожидаемая цена: {1}")
    public static Collection<Object[]> data() {
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(100f);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getPrice()).thenReturn(50f);
        Ingredient i2 = mock(Ingredient.class);
        when(i2.getPrice()).thenReturn(30f);

        return Arrays.asList(
                new Object[][]{
                        {List.of(i1), 100f * 2 + 50f},
                        {List.of(i1, i2), 100f * 2 + 50f + 30f},
                        {List.of(), 100f * 2}
                }
        );
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);
        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}