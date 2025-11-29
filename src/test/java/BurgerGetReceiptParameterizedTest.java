import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParameterizedTest {

    private Burger burger;
    private final String testName;

    private final String expectedReceipt;
    private final List<Ingredient> ingredients;

    public BurgerGetReceiptParameterizedTest(String testName, String expectedReceipt, List<Ingredient> ingredients) {
        this.testName = testName;
        this.expectedReceipt = expectedReceipt;
        this.ingredients = ingredients;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Пустой бургер только булки",
                        "(==== BunName ====)" + lineSeparator() +
                                "(==== BunName ====)" + lineSeparator() + lineSeparator() +
                                "Price: 100,000000" + lineSeparator(),
                        List.of()
                },
                {"булки и соус",
                        "(==== BunName ====)" + lineSeparator() +
                                "= sauce sauce1 =" + lineSeparator() +
                                "(==== BunName ====)" + lineSeparator() + lineSeparator() +
                                "Price: 110,000000" + lineSeparator(),
                        List.of(createIngredient(IngredientType.SAUCE, "sauce1", 10.0f))
                },
                {"булки соус и начинка",
                        "(==== BunName ====)" + lineSeparator() +
                                "= filling fill1 =" + lineSeparator() +
                                "= sauce sauce2 =" + lineSeparator() +
                                "(==== BunName ====)" + lineSeparator() + lineSeparator() +
                                "Price: 180,000000" + lineSeparator(),
                        List.of(
                                createIngredient(IngredientType.FILLING, "fill1", 70.0f),
                                createIngredient(IngredientType.SAUCE, "sauce2", 10.0f)
                        )
                }
        });
    }

    private static Ingredient createIngredient(
            IngredientType type,
            String name,
            float price
    ) {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getType()).thenReturn(type);
        Mockito.when(ingredient.getName()).thenReturn(name);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Before
    public void setUp() {
        // Создаём мок булки локально
        Bun mockBun = Mockito.mock(Bun.class);
        Mockito.when(mockBun.getName()).thenReturn("BunName");
        Mockito.when(mockBun.getPrice()).thenReturn(50.0f);

        burger = new Burger();
        burger.setBuns(mockBun);

        // Добавляем ингредиенты из параметров теста
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
    }

    @Test
    public void getReceiptTest() {
        String actualReceipt = burger.getReceipt();
        assertEquals("Test case: " + testName, expectedReceipt, actualReceipt);
    }


}
