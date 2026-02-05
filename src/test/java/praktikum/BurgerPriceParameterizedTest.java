package praktikum;

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
public class BurgerPriceParameterizedTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedTotal;

    public BurgerPriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "Bun: {0}, Ingredient: {1} -> Total: {2}")
    public static Object[][] getData() {
        return new Object[][] {
                {100f, 50f, 250f},
                {200f, 0f, 400f},
                {50.5f, 20.5f, 121.5f},
                {0f, 0f, 0f}
        };
    }

    @Test
    public void getPriceCalculatesCorrectly() {
        MockitoAnnotations.openMocks(this);

        Burger burger = new Burger();
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actualPrice = burger.getPrice();
        assertEquals("Цена рассчитана неверно", expectedTotal, actualPrice, 0.01);
    }
}