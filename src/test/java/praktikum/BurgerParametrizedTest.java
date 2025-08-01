package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Mock
    private Ingredient ingredientMock1;

    private Burger burger;

    private float bunPrice;
    private float ingredient1Price;
    private float ingredient2Price;
    private float expectedTotalPrice;

    public BurgerParametrizedTest(float bunPrice, float ingredient1Price, float ingredient2Price, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Parameterized.Parameters(name = "bun: {0}, ing1: {1}, ing2: {2} → total: {3}")
    public static Object[][] getData() {
        return new Object[][]{
                {100.0f, 50.0f, 30.0f, 280.0f},
                {50.0f, 0.0f, 0.0f, 100.0f},
                {0.0f, 10.0f, 20.0f, 30.0f},
                {10.5f, 20.3f, 30.7f, 71.5f},
                {200.0f, 100.0f, 100.0f, 600.0f}
        };
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock.getPrice()).thenReturn(ingredient1Price);
        when(ingredientMock1.getPrice()).thenReturn(ingredient2Price);

        burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
    }

    @Test
    public void getPriceParametrizedTest() {
        float actual = burger.getPrice();
        assertEquals("Цена бургера рассчитана неверно", expectedTotalPrice, actual, 0.5f);
    }
}
