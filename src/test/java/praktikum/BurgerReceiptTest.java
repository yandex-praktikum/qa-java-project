package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final String expectedReceipt;
    private final String bunName;
    private final float bunPrice;
    private final List<Ingredient> ingredients;

    @Parameterized.Parameters(name = "Тестовые данные: рецепт {0}, булка {1}, цена булки {2}, ингредиенты {3}")
    public static Object[][] data() {
        return new Object[][]{
                {String.format("(==== Булка ====)%n= sauce соус =%n= filling начинка =%n(==== Булка ====)%n%nPrice: %f%n", 350f),
                        "Булка", 100.0f, Arrays.asList(
                                new Ingredient(IngredientType.SAUCE, "соус", 50.0f),
                                new Ingredient(IngredientType.FILLING, "начинка", 100.0f)
                        )},
                {String.format("(==== Классическая булка ====)%n(==== Классическая булка ====)%n%nPrice: %f%n", 200f),
                        "Классическая булка", 100.0f, List.of()}
        };
    }

    public BurgerReceiptTest(String expectedReceipt, String bunName, float bunPrice, List<Ingredient> ingredients) {
        this.expectedReceipt = expectedReceipt;
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredients = ingredients;
    }

    @Test
    public void testGetReceipt_ReturnsCorrectReceipt() {
        Bun bun = new Bun(bunName, bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}