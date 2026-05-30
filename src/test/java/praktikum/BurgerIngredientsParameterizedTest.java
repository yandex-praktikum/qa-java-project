package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BurgerIngredientsParameterizedTest {

    private final IngredientType ingredientType;
    private final String ingredientName;
    private final String expectedReceiptType;

    public BurgerIngredientsParameterizedTest(IngredientType ingredientType, String ingredientName, String expectedReceiptType) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.expectedReceiptType = expectedReceiptType;
    }

    @Parameterized.Parameters(name = "Тип ингредиента: {0}, Имя: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "chili", "= sauce chili ="},
                {IngredientType.FILLING, "cutlet", "= filling cutlet ="}
        });
    }

    @Test
    public void testReceiptContainsCorrectIngredientTypeFormat() {
        Burger burger = new Burger();

        Bun mockBun = Mockito.mock(Bun.class);
        Ingredient mockIngredient = Mockito.mock(Ingredient.class);

        Mockito.when(mockBun.getName()).thenReturn("Булочка");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);

        Mockito.when(mockIngredient.getType()).thenReturn(ingredientType);
        Mockito.when(mockIngredient.getName()).thenReturn(ingredientName);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать корректно отформатированный тип ингредиента",
                receipt.contains(expectedReceiptType));
    }
}

