package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerReceiptParametrizedTest {

    private final IngredientType type;
    private final String expectedTypeInReceipt;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    public BurgerReceiptParametrizedTest(IngredientType type, String expectedTypeInReceipt) {
        this.type = type;
        this.expectedTypeInReceipt = expectedTypeInReceipt;
    }

    @Parameterized.Parameters(name = "тип ингредиента = {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getReceiptShouldContainLowerCasedIngredientType() {
        when(bun.getName()).thenReturn("red bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn("test ingredient");
        when(ingredient.getPrice()).thenReturn(10f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(String.format("= %s test ingredient =", expectedTypeInReceipt)));
    }
}
