package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final float bunPrice;
    private final int ingredientsCount;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(float bunPrice, int ingredientsCount, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientsCount = ingredientsCount;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters (name = "Case {index}: bunPrice={0}, ingredientsCount={1}, ingredientPrice={2} -> expectedPrice={3}" )
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { 100.0f, 1, 100.0f, 300.0f },
                { 300.0f, 0, 0.0f,  600.0f },
                {100.5f, 2, 50.0f, 301.0f}
        });
    }

    @Test
    public void checkBurgerPriceTest() {
        Burger burger = new Burger();

        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        for (int i = 0; i < ingredientsCount; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
            burger.addIngredient(ingredient);
        }

        Assert.assertEquals("Цена рассчитана неверно", expectedPrice, burger.getPrice(), 0.001f);
    }
}