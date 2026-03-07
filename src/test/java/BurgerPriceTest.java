import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private Burger burger;

    @Mock
    private Ingredient ingredient;

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {200f, 100f, 500f},
                {300f, 0f, 600f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        burger = new Burger();
        burger.setBuns(new Bun("test bun", bunPrice));

        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.addIngredient(ingredient);
    }

    @Test
    public void getPriceTest() {
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }
}
