package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float firstIngredientPrice;

    @Parameterized.Parameter(2)
    public float secondIngredientPrice;

    @Parameterized.Parameter(3)
    public float expectedPrice;

    @Parameterized.Parameters(name = "bun={0}, first={1}, second={2} -> {3}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 0f, 0f, 200f},
                {50f, 10f, 40f, 150f},
                {1.5f, 2.5f, 3.0f, 8.5f}
        });
    }

    @Test
    public void getPriceShouldReturnBunDoublePlusIngredientsSum() {
        Burger burger = new Burger();

        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        Ingredient firstIngredientMock = mock(Ingredient.class);
        when(firstIngredientMock.getPrice()).thenReturn(firstIngredientPrice);

        Ingredient secondIngredientMock = mock(Ingredient.class);
        when(secondIngredientMock.getPrice()).thenReturn(secondIngredientPrice);

        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        assertEquals(expectedPrice, burger.getPrice(), 0.0001f);
    }
}

