import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient cheese;

    @Mock
    private Ingredient cutlet;

    private final float bunPrice;
    private final float cheesePrice;
    private final float cutletPrice;

    public BurgerParameterizedTest(float bunPrice, float cheesePrice, float cutletPrice) {
        this.bunPrice = bunPrice;
        this.cheesePrice = cheesePrice;
        this.cutletPrice = cutletPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
            {2.5f, 1.0f, 1.5f},
            {3.0f, 0.5f, 2.0f},
            {1.5f, 2.0f, 1.0f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(bun);
    }

    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(cheese.getPrice()).thenReturn(cheesePrice);
        when(cutlet.getPrice()).thenReturn(cutletPrice);

        burger.addIngredient(cheese);
        burger.addIngredient(cutlet);

        float expected = bunPrice * 2 + cheesePrice + cutletPrice;
        assertEquals(expected, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn("Black bun");
        when(bun.getPrice()).thenReturn(bunPrice);

        when(cheese.getName()).thenReturn("Cheese");
        when(cheese.getType()).thenReturn(IngredientType.FILLING);
        when(cheese.getPrice()).thenReturn(cheesePrice);

        burger.addIngredient(cheese);

        String expectedReceipt = String.format("(==== Black bun ====)%n" +
            "= filling Cheese =%n" +
            "(==== Black bun ====)%n" +
            "%nPrice: %f%n", burger.getPrice());

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
