import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final Bun bun;
    private final Ingredient[] ingredients;
    private final float expectedPrice;
    private static final float DELTA = 0.01f;

    public BurgerPriceTest(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тест {index}: цена бургера с булочкой {0} и {1} ингредиентами = {2}")
    public static Collection<Object[]> data() {
        Bun cheapBun = mock(Bun.class);
        when(cheapBun.getPrice()).thenReturn(100f);

        Bun expensiveBun = mock(Bun.class);
        when(expensiveBun.getPrice()).thenReturn(200f);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(50f);

        Ingredient cutlet = mock(Ingredient.class);
        when(cutlet.getPrice()).thenReturn(100f);

        Ingredient cheese = mock(Ingredient.class);
        when(cheese.getPrice()).thenReturn(80f);

        Ingredient salad = mock(Ingredient.class);
        when(salad.getPrice()).thenReturn(30f);

        return Arrays.asList(new Object[][]{
                {cheapBun, new Ingredient[]{}, 200f},
                {cheapBun, new Ingredient[]{sauce}, 250f},
                {cheapBun, new Ingredient[]{cutlet, cheese}, 380f},
                {cheapBun, new Ingredient[]{sauce, cutlet, cheese, salad}, 460f},
                {expensiveBun, new Ingredient[]{}, 400f},
                {expensiveBun, new Ingredient[]{cutlet, cheese}, 580f}
        });
    }

    @Test
    public void testGetPriceWithDifferentBurgerCombinations() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        assertEquals("Цена бургера должна соответствовать ожидаемой",
                expectedPrice, burger.getPrice(), DELTA);
    }

}
