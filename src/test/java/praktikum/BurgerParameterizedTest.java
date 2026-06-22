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
public class BurgerParameterizedTest {


    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private final float expectedPrice;


    public BurgerParameterizedTest(float bunPrice,
                                   float saucePrice,
                                   float fillingPrice,
                                   float expectedPrice) {

        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedPrice = expectedPrice;
    }


    @Parameterized.Parameters(
            name = "Цена булки: {0}, соус: {1}, начинка: {2}, итог: {3}"
    )
    public static Collection<Object[]> getData() {

        return Arrays.asList(new Object[][]{
                {100f, 50f, 200f, 450f},
                {200f, 100f, 300f, 800f},
                {300f, 200f, 100f, 900f}
        });
    }


    @Test
    public void getPriceReturnsCorrectPriceForDifferentData() {

        Burger burger = new Burger();


        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);


        Ingredient sauceIngredient = mock(Ingredient.class);
        when(sauceIngredient.getPrice()).thenReturn(saucePrice);


        Ingredient fillingIngredient = mock(Ingredient.class);
        when(fillingIngredient.getPrice()).thenReturn(fillingPrice);


        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);


        assertEquals(
                expectedPrice,
                burger.getPrice(),
                0.001f
        );
    }
}