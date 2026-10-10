package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParamTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private final float expectedPrice;

    public BurgerParamTest(float bunPrice, float saucePrice,
                           float fillingPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 100f, 200f, 500f},
                {200f, 0f, 0f, 400f},
                {50f, 50f, 50f, 200f},
                {300f, 300f, 300f, 1200f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockSauce.getPrice()).thenReturn(saucePrice);
        when(mockFilling.getPrice()).thenReturn(fillingPrice);
    }

    @Test
    public void testPriceWithDifferentIngredients() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}