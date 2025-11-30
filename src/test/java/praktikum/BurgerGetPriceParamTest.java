package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceParamTest {

    private Burger burger;

    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;


    public BurgerGetPriceParamTest(float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Булочка {0}, Ингридиенты {1}, Стоимость {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, Collections.<Float>emptyList(), 200f},
                {100f, Arrays.asList(50f), 250f},
                {107f, Arrays.asList(50f, 100f), 364f},
                {99.9f, Arrays.asList(50f, 10.2f), 260f},
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (Float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }
    }

    @After
    public void tearDown(){
        burger = null;
    }

    @Test
    public void testGetPrice(){
        float actualPrice = burger.getPrice();
        assertEquals("Цена не совпала", expectedPrice, actualPrice, 0.01f);
    }
}
