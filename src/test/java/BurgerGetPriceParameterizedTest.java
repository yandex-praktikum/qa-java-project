import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    private final float bunPrice;
    private final float firstIngPrice;
    private final float secondIngPrice;
    private final float expectedPrice;

    public BurgerGetPriceParameterizedTest(
            float bunPrice,
            float firstIngPrice, float secondIngPrice, float expectedPrice
    ) {
        this.bunPrice = bunPrice;
        this.firstIngPrice = firstIngPrice;
        this.secondIngPrice = secondIngPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "price of bun={0}, price of firstIngredient={1}, price of secondIngredient={2}, total={3}")
    public static Object[][] getPriceData() {
        return new Object[][]{
                {2f, 3f, 3f, 2f * 2 + 3f + 3f},
                {5f, 0f, 0f, 5f * 2},
                {2f, 4f, 0f, 2f * 2 + 4f}
        };
    }

    @Test
    public void testGetPrice() {
        Bun bun = Mockito.mock(Bun.class);
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngPrice);

        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngPrice);


        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actual = burger.getPrice();

        assertEquals(expectedPrice, actual, 0.0001f);
    }
}
