package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest extends BaseTest {

    private final IngredientType ingredientType;
    private final String expectedTypeString;

    public BurgerParameterizedTest(IngredientType ingredientType, String expectedTypeString) {
        this.ingredientType = ingredientType;
        this.expectedTypeString = expectedTypeString;
    }

    @Parameterized.Parameters(name = "Тип: {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        });
    }
    @Before
    public void setUp() {
        super.setUp();
        when(mockIngredient.getType()).thenReturn(ingredientType);
        when(mockIngredient.getName()).thenReturn("Test Ingredient");
        when(mockIngredient.getPrice()).thenReturn(50f);
    }

    // Чек для разных типов ингредиентов: булочка, ингредиент и цена
    @Test
    public void testReceiptContainsCorrectTypeString() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        String expectedFragment =
                String.format("= %s %s =", expectedTypeString, "Test Ingredient");
        assertTrue("В чеке должна быть строка с правильным типом ингредиента",
                receipt.contains(expectedFragment));

    }


}
