package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {

    private final float bunPrice;
    private final float firstIngredientPrice;
    private final float secondIngredientPrice;
    private final float expectedBurgerPrice;

    public BurgerParametrizedTest(float bunPrice, float firstIngredientPrice,
                                  float secondIngredientPrice, float expectedBurgerPrice) {
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        this.expectedBurgerPrice = expectedBurgerPrice;
    }

    private Burger burger;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockFirstIngredient;

    @Mock
    private Ingredient mockSecondIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "цена булочки {0}, цена 1 ингредиента {1}, " +
            "цена 2 ингредиента {2}, ожидаемая цена бургера {3}")
    public static Object[][] data() {
        return new Object[][] {
                {100.0f, 200.0f, 150.0f, 550.0f},
                {100.4f, 0.0f, 150.0f, 350.8f},
                {0.0f, 200.0f, 150.0f, 350.0f},
                {0.0f, 0.0f, 0.0f, 0.0f}
        };
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);

        burger.addIngredient(mockFirstIngredient);
        Mockito.when(mockFirstIngredient.getPrice()).thenReturn(firstIngredientPrice);

        burger.addIngredient(mockSecondIngredient);
        Mockito.when(mockSecondIngredient.getPrice()).thenReturn(secondIngredientPrice);

        Assert.assertEquals("Неверный расчёт цены бургера",
                expectedBurgerPrice, burger.getPrice(), 0.001f);
    }
}
