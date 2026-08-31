import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {
    private final IngredientType ingredientType;
    private final String expectedTypeName;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;

    private Burger burger;
    public BurgerReceiptTest (IngredientType ingredientType,String expectedTypeName) {
        this.ingredientType = ingredientType;
        this.expectedTypeName = expectedTypeName;
    }

        @Parameterized.Parameters(name = "type={0}, expectedName={1}")
                public static Collection <Object[]> data() {
            return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        });
    }
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(100f);
        when(bun.getName()).thenReturn("Test Bun");

        when(ingredient.getPrice()).thenReturn(50f);
        when(ingredient.getName()).thenReturn("Test Ingredient");
        when(ingredient.getType()).thenReturn(ingredientType);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @Test
    public void getReceiptTest() {
        String receipt = burger.getReceipt();

        String expected = String.format("(==== Test Bun ====)%n" +
                        "= %s Test Ingredient =%n" +
                        "(==== Test Bun ====)%n" +
                        "%nPrice: 250,000000%n",
                expectedTypeName);
        assertEquals(""+ ingredientType,expected,receipt);
    }
}
