package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTypeTest {

    @Parameters(name = "Тип {0} выводится в чеке как \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {IngredientType.SAUCE,   "sauce"},
                {IngredientType.FILLING, "filling"}
        });
    }

    private final IngredientType type;
    private final String expectedTypeInReceipt;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    private Burger burger;
    private AutoCloseable mocks;

    public BurgerReceiptTypeTest(IngredientType type, String expectedTypeInReceipt) {
        this.type = type;
        this.expectedTypeInReceipt = expectedTypeInReceipt;
    }

    @Before
    public void setUp() throws Exception {
        mocks = MockitoAnnotations.openMocks(this);

        when(bun.getName()).thenReturn("bun");
        when(ingredient.getName()).thenReturn("test");
        when(ingredient.getType()).thenReturn(type);

        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @After
    public void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void getReceiptRendersIngredientTypeInLowerCase() {
        String receipt = burger.getReceipt();
        String expectedLine = "= " + expectedTypeInReceipt + " test =";

        assertTrue("Ожидалась строка '" + expectedLine + "' в чеке:\n" + receipt,
                receipt.contains(expectedLine));
    }
}