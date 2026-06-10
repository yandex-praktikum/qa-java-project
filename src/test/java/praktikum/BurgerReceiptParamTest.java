package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerReceiptParamTest {

    private final IngredientType ingredientType;
    private final String expectedTypeString;

    public BurgerReceiptParamTest(IngredientType ingredientType, String expectedTypeString) {
        this.ingredientType = ingredientType;
        this.expectedTypeString = expectedTypeString;
    }

    @Parameterized.Parameters(name = "Чек для ингредиента типа: {0}")
    public static Collection<Object[]> getReceiptTestData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        });
    }

    @Test
    public void testGetReceiptFormatsTextCorrectly() {
        Burger burger = new Burger();


        Bun mockBun = Mockito.mock(Bun.class);
        Mockito.when(mockBun.getName()).thenReturn("Космическая булка");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);


        Ingredient mockIngredient = Mockito.mock(Ingredient.class);
        Mockito.when(mockIngredient.getType()).thenReturn(ingredientType);
        Mockito.when(mockIngredient.getName()).thenReturn("Лунный сыр");
        Mockito.when(mockIngredient.getPrice()).thenReturn(60.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);


        String expectedReceipt = String.format("(==== %s ====)%n", "Космическая булка") +
                String.format("= %s %s =%n", expectedTypeString, "Лунный сыр") +
                String.format("(==== %s ====)%n", "Космическая булка") +
                String.format("%nPrice: %f%n", 260.0f); // (100 * 2) + 60

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals("Итоговый шаблон чека сформирован некорректно", expectedReceipt, actualReceipt);
    }
}

