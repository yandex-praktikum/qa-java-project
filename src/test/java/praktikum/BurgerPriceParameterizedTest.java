package praktikum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;


@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }



    @Parameterized.Parameters(name = "Итоговая цена: " + "{2}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {988.0f, 90.0f, 2066.0f},
                {1255.0f, 3000.0f, 5510.0f},
                {988.0f, 4400.0f, 6376.0f}

        };
    }

    @Test
    public void shouldCalculatePrice(){
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }
}
