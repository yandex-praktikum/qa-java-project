package praktikum;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest extends BaseTest {

    private List<Ingredient> ingredients;
    private String expectedReceipt;

    @Parameters(name = "Чек для бургера с булочкой '{0}' и ингредиентами {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Пустой бургер (только булочка)
                {"black bun", 100.0f, new ArrayList<>(),
                        "(==== black bun ====)\n" +
                                "(==== black bun ====)\n\n" +
                                "Price: 200.000000\n"},
                // Бургер с одним соусом
                {"white bun", 200.0f, Arrays.asList(createIngredientMock(IngredientType.SAUCE, "hot sauce", 100.0f)),
                        "(==== white bun ====)\n" +
                                "= sauce hot sauce =\n" +
                                "(==== white bun ====)\n\n" +
                                "Price: 500.000000\n"}
        });

    }

    private static Ingredient createIngredientMock(IngredientType type, String name, float price) {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    public BurgerReceiptParameterizedTest(String bunName, float bunPrice, List<Ingredient> ingredients, String expectedReceipt) {
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
        this.bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        this.burger = new Burger();
    }

    @Override
    @Before
    public void setUp() {
        burger.setBuns(bunMock);
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                burger.addIngredient(ingredient);
            }
        }
    }

    @Test
    public void testGetReceipt() {
        String actualReceipt = burger.getReceipt();
        // Форматируем цену в фактическом чеке с точкой
        String formattedActual = actualReceipt.replace(',', '.');

        // Удаляем все символы возврата каретки (\r)
        String normalizedActual = formattedActual.replace("\r", "");

        System.out.println("ОЖИДАЕМЫЙ ЧЕК:\n" + expectedReceipt);
        System.out.println("ФАКТИЧЕСКИЙ ЧЕК:\n" + normalizedActual);
        assertEquals(expectedReceipt, normalizedActual);
    }
}

