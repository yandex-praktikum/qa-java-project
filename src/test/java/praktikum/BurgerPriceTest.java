package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {200f, 30f, 430f},
                {150f, 70f, 370f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);

        ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void getPriceShouldCalculateCorrectly() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }
}
