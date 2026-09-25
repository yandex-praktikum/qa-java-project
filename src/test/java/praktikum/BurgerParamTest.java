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
public class BurgerParamTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final int ingredientsCount;
    private final float expectedPrice;

    public BurgerParamTest(float bunPrice, float ingredientPrice, int ingredientsCount, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.ingredientsCount = ingredientsCount;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Булочка: {0}, Ингредиент: {1}, Кол-во: {2} → Итог: {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {50f,  0f,   0, 100f},
                {100f, 25f,  1, 225f},
                {80f,  20f,  2, 200f},
                {30f,  15f,  4, 120f},
                {60f,  10f, 10, 220f}
        });
    }

    @Test
    public void getPriceCalculatedCorrectly() {
        Bun bunMock = mock(Bun.class);
        Ingredient ingredientMock = mock(Ingredient.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock.getPrice()).thenReturn(ingredientPrice);

        Burger burger = new Burger();
        burger.setBuns(bunMock);
        for (int i = 0; i < ingredientsCount; i++) {
            burger.addIngredient(ingredientMock);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }
}