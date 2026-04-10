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

public class BurgerPriceCalculationTests {

    private final float expectedPrice;
    private final float ingredient1Price;
    private final float bunPrice;
    private final float ingredient2Price;

    public BurgerPriceCalculationTests(float expectedPrice, float ingredient1Price, float ingredient2Price, float bunPrice) {
        this.bunPrice = bunPrice;
        this.expectedPrice = expectedPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;

    }

    @Mock

    private Bun bunMock;

    @Mock

    private Ingredient ingredientMock1;

    @Mock

    private Ingredient ingredientMock2;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {400, 100, 100, 100},
                {800, 200, 200, 200},
                {1200, 300, 300, 300}
        });
    }

    @Test

    public void shouldCalculatePriceCorrectly() {
        Burger burger = new Burger();

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock1.getPrice()).thenReturn(ingredient1Price);
        when(ingredientMock2.getPrice()).thenReturn(ingredient2Price);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);


    }

}
