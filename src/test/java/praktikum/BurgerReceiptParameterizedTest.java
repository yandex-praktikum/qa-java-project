package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {

    private static final String BUN_NAME = "space bun";
    private static final String INGREDIENT_NAME = "meteor layer";
    private static final float BUN_PRICE = 100.0f;
    private static final float INGREDIENT_PRICE = 50.0f;

    private final IngredientType ingredientType;
    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    public BurgerReceiptParameterizedTest(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters(name = "receipt for {0}")
    public static Collection<Object[]> ingredientTypes() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE},
                {IngredientType.FILLING}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn(BUN_NAME);
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        when(ingredient.getType()).thenReturn(ingredientType);
        when(ingredient.getName()).thenReturn(INGREDIENT_NAME);
        when(ingredient.getPrice()).thenReturn(INGREDIENT_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @Test
    public void getReceiptReturnsFormattedBurgerReceipt() {
        String expected = String.format("(==== %s ====)%n", BUN_NAME)
                + String.format("= %s %s =%n", ingredientType.toString().toLowerCase(), INGREDIENT_NAME)
                + String.format("(==== %s ====)%n", BUN_NAME)
                + String.format("%nPrice: %f%n", BUN_PRICE * 2 + INGREDIENT_PRICE);

        assertEquals(expected, burger.getReceipt());
    }
}
