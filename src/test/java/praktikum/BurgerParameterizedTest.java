package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerParameterizedTest {
    @Mock
    private Bun mockbun;

    @Mock
    private Ingredient mockIngredient;

    private final IngredientType ingredientType;
    private final String expectedTypeString;

    private AutoCloseable closeable;
    private Burger burger;

    public BurgerParameterizedTest(IngredientType ingredientType, String expectedTypeString){
        this.ingredientType = ingredientType;
        this.expectedTypeString = expectedTypeString;

    }

    @Before
    public void setUp() {
        burger = new Burger();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters(name = "Ingredient type: {0}, expected string: {1}")
    public static Object[][] getTestData(){
        return new Object[][] {
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        };
    }

    @Test
    public void testGetReceiptWithDifferentIngredientType(){
        burger.setBuns(mockbun);
        burger.addIngredient(mockIngredient);

        when(mockbun.getName()).thenReturn("Test Bun");
        when(mockbun.getPrice()).thenReturn(100.0f);
        when(mockIngredient.getType()).thenReturn(ingredientType);
        when(mockIngredient.getName()).thenReturn("Test Ingredient");
        when(mockIngredient.getPrice()).thenReturn(50.0f);

        String receipt = burger.getReceipt();
        String expectedReceipt = "(==== Test Bun ====)\n" +
                "= " + expectedTypeString + " Test Ingredient =\n" +
                "(==== Test Bun ====)\n\n" +
                "Price: 250,000000\n";
        assertEquals("Receipt should have correct format for ingredient type: " + expectedTypeString,
                expectedReceipt, receipt.replace("\r\n", "\n"));
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

}
