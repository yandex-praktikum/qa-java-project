package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float ingredientOnePrice;
    private final float ingredientTwoPrice;
    private final float expectedPrice;

    public BurgerParameterizedTest(float bunPrice,
                                   float ingredientOnePrice,
                                   float ingredientTwoPrice,
                                   float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientOnePrice = ingredientOnePrice;
        this.ingredientTwoPrice = ingredientTwoPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "bun={0}, ingredient1={1}, ingredient2={2}, expected={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 25f, 275f},
                {200f, 100f, 300f, 800f},
                {300f, 0f, 100f, 700f}
        });
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredientOne = mock(Ingredient.class);
        Ingredient ingredientTwo = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredientOne.getPrice()).thenReturn(ingredientOnePrice);
        when(ingredientTwo.getPrice()).thenReturn(ingredientTwoPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }
}