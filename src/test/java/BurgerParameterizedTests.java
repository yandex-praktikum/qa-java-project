import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {

    private final float bunPrice;
    private final List<Float> ingredientsPrice;
    private final float expectedPrice;

    public BurgerParameterizedTests(float bunPrice, List<Float> ingredientsPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientsPrice = ingredientsPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "bunPrice={0}, ingredientPrice={1}, expectedPrice={2}")
    public static Object[][] getData() {
        return new Object[][]{
                {100f, Arrays.asList(100f), 300f},
                {200f, Arrays.asList(100f, 200f), 700f},
                {300f, Arrays.asList(100f, 200f, 300f), 1200f},
                {100f, Arrays.asList(), 200f}
        };

    }

    @Test
    public void shouldReturnCorrectPrice() {
        Burger burger = new Burger();
        Bun bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        for (float price: ingredientsPrice) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            burger.addIngredient(ingredient);
            Mockito.when(ingredient.getPrice()).thenReturn(price);
        }
        float actualPrice = burger.getPrice();
        Assert.assertEquals("Неверный расчет стоимости бургера", expectedPrice, actualPrice, 0.001f);
    }
}