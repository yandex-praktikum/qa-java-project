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
    private final float fillingPrice;
    private final float bunPrice;
    private final float saucePrice;

    public BurgerPriceCalculationTests(float expectedPrice, float fillingPrice, float saucePrice, float bunPrice) {
        this.bunPrice = bunPrice;
        this.expectedPrice = expectedPrice;
        this.fillingPrice = fillingPrice;
        this.saucePrice = saucePrice;

    }

    @Mock

    private Bun bunMock;

    @Mock

    private Ingredient fillingIngredientMock;

    @Mock

    private Ingredient sauceIngredientMock;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters(name = "expected={0}, filling={1}, sauce={2}, bun={3}")
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
        when(fillingIngredientMock.getPrice()).thenReturn(fillingPrice);
        when(sauceIngredientMock.getPrice()).thenReturn(saucePrice);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(sauceIngredientMock);

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);


    }

}
