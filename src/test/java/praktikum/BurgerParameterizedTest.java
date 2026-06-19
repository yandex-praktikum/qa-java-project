package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float ingredient1Price;
    private final float ingredient2Price;
    private final float expectedPrice;

    public BurgerParameterizedTest(float bunPrice,
                                   float ingredient1Price,
                                   float ingredient2Price,
                                   float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 200f, 450f},
                {200f, 100f, 300f, 800f},
                {300f, 200f, 100f, 900f}
        });
    }

    @Test
    public void getPriceReturnsCorrectPriceForDifferentData() {

        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);

        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(ingredient1Price);

        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(ingredient2Price);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}