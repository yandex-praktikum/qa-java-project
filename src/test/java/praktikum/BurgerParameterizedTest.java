package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final IngredientType ingredientType;
    private final String expectedType;

    public BurgerParameterizedTest(
            IngredientType ingredientType,
            String expectedType) {
        this.ingredientType = ingredientType;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        };
    }

    @Test
    public void getReceiptContainsCorrectIngredientType() {

        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient.getType()).thenReturn(ingredientType);
        when(ingredient.getName()).thenReturn("test ingredient");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        Assert.assertTrue(receipt.contains(expectedType));
        Assert.assertTrue(receipt.contains("test ingredient"));
    }
}
