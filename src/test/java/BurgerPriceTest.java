import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private static final int IDX_BUN_PRICE = 0;
    private static final int IDX_INGREDIENT_PRICE = 1;
    private static final int IDX_EXPECTED_TOTAL_PRICE = 2;

    private Burger burger;

    @Parameterized.Parameter(IDX_BUN_PRICE)
    public float bunPrice;

    @Parameterized.Parameter(IDX_INGREDIENT_PRICE)
    public float ingredientPrice;

    @Parameterized.Parameter(IDX_EXPECTED_TOTAL_PRICE)
    public float expectedTotalPrice;

    @Parameterized.Parameters(name = "Булочка цена={0}, Ингредиент цена={1}, Ожидаемая сумма={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1.5f, 0.5f, 4.0f},
                {2.0f, 1.0f, 5.0f},
                {1.0f, 2.0f, 4.0f}
        });
    }

    @Before
    public void setup() {
        burger = new Burger();
    }

    @Test
    public void testPriceCalculation() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockBun.getName()).thenReturn("TestBun");

        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
        when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient.getName()).thenReturn("Cheese");

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        assertEquals(expectedTotalPrice, burger.getPrice(), 0.01);
    }
}
