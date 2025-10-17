package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {
    private Burger burger;
    private float bunPrice;
    private float expectedPrice;
    private int ingredientCount;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauce;

    @Mock
    private Ingredient mockIngredientBun;

    @Mock
    private Ingredient mockIngredientFilling;

    public BurgerGetPriceParameterizedTest(float bunPrice, float expectedPrice, int ingredientCount) {
        this.bunPrice = bunPrice;
        this.expectedPrice = expectedPrice;
        this.ingredientCount = ingredientCount;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);

        Mockito.when(mockIngredientSauce.getPrice()).thenReturn(50f);
        Mockito.when(mockIngredientBun.getPrice()).thenReturn(50f);
        Mockito.when(mockIngredientFilling.getPrice()).thenReturn(50f);
    }

    @Parameterized.Parameters(name = "Цена булочки {0}, Ожидаемая итоговая цена {1}, Количество ингредиентов {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 200f, 0},
                {100f, 250f, 1},
                {100f, 300f, 2},
                {150f, 350f, 1},
                {200f, 550f, 3}
        });
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun);

        if (ingredientCount >= 1) burger.addIngredient(mockIngredientSauce);
        if (ingredientCount >= 2) burger.addIngredient(mockIngredientBun);
        if (ingredientCount >= 3) burger.addIngredient(mockIngredientFilling);

        assertEquals("Неверный расчет цены для булочки" + bunPrice + " и" + ingredientCount + " ингредиентов", expectedPrice, burger.getPrice(), 0.01f);
    }
}
