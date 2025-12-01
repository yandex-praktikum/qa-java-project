package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
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

    @Parameterized.Parameters(name = "Тип ингредиента: {0}")
    public static Collection<Object[]> data() {
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
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();

        System.out.println("Actual receipt: '" + receipt + "'");
        String expectedReceipt = "(==== Test Bun ====)\n" +
                "= " + expectedTypeString + " Test Ingredient =\n" +
                "(==== Test Bun ====)\n" +
                "\n" +
                "Price: 250,000000\n";
        assertEquals(expectedReceipt, receipt);
    }

}
