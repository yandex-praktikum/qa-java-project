import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class TestBurgerReceipt {
    public Bun bun;
    public List<Ingredient> ingredients = new ArrayList<>();
    public String expectedReceipt;

    public TestBurgerReceipt(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters (name = "Тестовые данные: {0}, {1}, {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                // Тест 1: бургер с одним ингредиентом (сыр)
                        new Bun("Classic", 5f),
                        List.of(new Ingredient(IngredientType.FILLING, "Cheese", 10f)),
                        "(==== Classic ====)\r\n= filling Cheese =\r\n(==== Classic ====)\r\n\r\nPrice: 20,000000\r\n"
                },

                // Тест 2: бургер без ингредиентов
                {
                        new Bun("Wheat", 6f),
                        Collections.emptyList(),
                        "(==== Wheat ====)\r\n(==== Wheat ====)\r\n\r\nPrice: 12,000000\r\n"
                },

                // Тест 3: бургер с двумя ингредиентами (сыр и помидор)
                {
                        new Bun("Gluten-Free", 7f),
                        Arrays.asList(
                                new Ingredient(IngredientType.FILLING, "Cheese", 10f),
                                new Ingredient(IngredientType.FILLING, "Tomato", 3f)
                        ),
                        "(==== Gluten-Free ====)\r\n= filling Cheese =\r\n= filling Tomato =\r\n(==== Gluten-Free ====)\r\n\r\nPrice: 27,000000\r\n"
                }
        };
    }

    @Test
    public void testBurgerGetReceipt() {
        Burger burger = new Burger();
        burger.bun = bun;
        burger.ingredients = ingredients;
        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}
