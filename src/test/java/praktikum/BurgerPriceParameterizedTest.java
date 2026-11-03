package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {
    private final float bunPrice;
    private final List<Float> saucePrices;
    private final List<Float> fillingPrices;
    private final float expectedPrice;

    private Burger burger;

    public BurgerPriceParameterizedTest(float bunPrice, List<Float> saucePrices, List<Float> fillingPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.saucePrices = saucePrices;
        this.fillingPrices = fillingPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {100f, List.of(100f), List.of(200f), 500f},
                {200f, List.of(), List.of(), 400f},
                {100f, List.of(), List.of(200f, 100f, 100f), 600f},
                {300f, List.of(200f, 250f, 50f), List.of(), 1100f},
                {100f, List.of(100f, 200f), List.of(200f, 300f), 1000f},
        };
    }

    @Before
    public void init() {
        Bun bun = new Bun("bun", bunPrice);

        burger = new Burger();
        burger.setBuns(bun);

        for (float saucePrice : saucePrices) {
            Ingredient sauce = new Ingredient(IngredientType.SAUCE, "sauce", saucePrice);
            burger.addIngredient(sauce);
        }

        for (float fillingPrice : fillingPrices) {
            Ingredient filling = new Ingredient(IngredientType.FILLING, "filling", fillingPrice);
            burger.addIngredient(filling);
        }
    }

    @Test
    public void getPriceParameterizedTest() {
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}
