package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    @Mock
    private  Bun mockBun;
    private Burger burger;
    private AutoCloseable mocks;
    private final String bunName;
    private final float bunPrice;
    private final int ingredientCount;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(String bunName, float bunPrice, int ingredientCount, float[] ingredientPrices, float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientCount = ingredientCount;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{0} + {1} ингредиентов, цены={2}, итого={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f, 0, new float[]{}, 200f},
                {"white bun", 200f, 1, new float[]{100f}, 500f},
                {"red bun", 300f, 2, new float[]{100f,200f}, 900f},
                {"black bun", 100f, 3, new float[]{100f,200f,300f}, 800f},
                {"red bun", 300f, 2, new float[]{150f,250f}, 1000f},
                {"black bun (цена 0)", 0f, 0, new float[]{}, 0f},
                {"red bun + все ингредиенты", 300f, 6, new float[]{100f,200f,300f,100f,200f,300f}, 1800f},
        });
    }

    @Before
    public void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @After
    public void tearDown() throws Exception {
        if (mocks !=null) {
            mocks.close();
        }
    }

    @Test
    public void getPriceShouldCalculateCorrectly() {

        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockBun.getName()).thenReturn(bunName);
        burger.setBuns(mockBun);

        for (int i = 0; i < ingredientCount; i++) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(ingredientPrices[i]);
            burger.addIngredient(mockIngredient);
        }

        float actualPrice = burger.getPrice();
        assertEquals(String.format("Неверный расчет для %s: ожидалось %.2f, получено %.2f",
                        bunName, expectedPrice, actualPrice),
                expectedPrice, actualPrice, 0.001);
    }

}
