package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    public BurgerPriceTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {200f, 100f, 500f},
                {50f, 25f, 125f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        burger = new Burger();

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @Test
    public void getPriceShouldCalculateCorrectly() {
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}