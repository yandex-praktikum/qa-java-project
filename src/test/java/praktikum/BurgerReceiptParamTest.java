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
public class BurgerReceiptParamTest {

    private final String bunName;
    private final String ingredientName;
    private final IngredientType ingredientType;
    private final String expectedFragment;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    private Burger burger;

    public BurgerReceiptParamTest(String bunName, String ingredientName, IngredientType ingredientType, String expectedFragment) {
        this.bunName = bunName;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.expectedFragment = expectedFragment;
    }

    @Parameterized.Parameters(name = "Булка={0}, Ингредиент={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Краторная булка", "Соус Spicy-X", IngredientType.SAUCE, "= sauce Соус Spicy-X ="},
                {"Флюоресцентная булка", "Мясо бессмертных моллюсков", IngredientType.FILLING, "= filling Мясо бессмертных моллюсков ="},
                {"Булка R2-D3", "Сыр с астероидной плесенью", IngredientType.FILLING, "= filling Сыр с астероидной плесенью ="}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testGetReceiptContainsExpectedFragment() {
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        // ОР: чек должен содержать указанный фрагмент согласно документации
        assertTrue("Чек должен содержать фрагмент: " + expectedFragment, receipt.contains(expectedFragment));
    }
}
