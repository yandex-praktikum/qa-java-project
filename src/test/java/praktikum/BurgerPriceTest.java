package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final float bunPrice;
    private final float firstIngredientPrice;
    private final float secondIngredientPrice;
    private final float expectedTotal;

    private Burger burger;

    public BurgerPriceTest(float bunPrice, float firstIngredientPrice, float secondIngredientPrice, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "bunPrice={0}, firstIngredientPrice={1}, secondIngredientPrice={2} => expectedTotalPrice={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 10f, 20f, 100f * 2 + 10f + 20f},
                {200f, 0f,  5.5f, 200f * 2 + 0f + 5.5f},
                {1.1f, 2.2f, 3.3f, 1.1f * 2 + 2.2f + 3.3f},
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    private Bun bunWithPrice(float price) {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(price);
        return bun;
    }

    private Ingredient ingredientWithPrice(float price) {
        Ingredient ing = Mockito.mock(Ingredient.class);
        Mockito.when(ing.getPrice()).thenReturn(price);
        return ing;
    }

    @Test
    public void shouldCalculatePrice() {
        burger.setBuns(bunWithPrice(bunPrice));
        burger.addIngredient(ingredientWithPrice(firstIngredientPrice));
        burger.addIngredient(ingredientWithPrice(secondIngredientPrice));

        assertEquals(expectedTotal, burger.getPrice(), 0.0001f);
    }
}