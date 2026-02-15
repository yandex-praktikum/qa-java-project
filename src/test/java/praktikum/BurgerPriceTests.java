package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceTests {

    private final float bunPrice;
    private final float ing1Price;
    private final float ing2Price;
    private final float expectedTotal;

    private Burger burger;

    public BurgerPriceTests(float bunPrice, float ing1Price, float ing2Price, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ing1Price = ing1Price;
        this.ing2Price = ing2Price;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "bun={0}, ing1={1}, ing2={2} => total={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 10f, 20f, 100f * 2 + 10f + 20f},
                {200f, 0f,  5.5f, 200f * 2 + 0f + 5.5f},
                {1.1f, 2.2f, 3.3f, 1.1f * 2 + 2.2f + 3.3f},
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    private Bun bunMock(float price) {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(price);
        return bun;
    }

    private Ingredient ingredientMock(float price) {
        Ingredient ing = Mockito.mock(Ingredient.class);
        Mockito.when(ing.getPrice()).thenReturn(price);
        return ing;
    }

    @Test
    public void shouldCalculatePrice() {
        burger.setBuns(bunMock(bunPrice));
        burger.addIngredient(ingredientMock(ing1Price));
        burger.addIngredient(ingredientMock(ing2Price));

        assertEquals(expectedTotal, burger.getPrice(), 0.0001f);
    }
}
