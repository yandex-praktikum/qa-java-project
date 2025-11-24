
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedTotal;

    public BurgerGetPriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedTotal) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters(name = "bun={0}, ingredient={1}, total={2}")
    public static Object[][] data() {
        return new Object[][]{
                {100f, 100f, 300f},
                {200f, 0f, 400f},
                {150f, 200f, 500f},
        };
    }

    @Test
    public void testPriceCalculation() {

        Burger burger = new Burger();

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        float actual = burger.getPrice();

        assertEquals(expectedTotal, actual,0.01f);
    }
}
