package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {

    private final Burger burger = new Burger();
    private final String bunName;
    private final float bunPrice;
    private final String[] ingredientNames;
    private final IngredientType[] ingredientTypes;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerReceiptParameterizedTest(String description, String bunName, float bunPrice,
                                          String[] ingredientNames, IngredientType[] ingredientTypes,
                                          float[] ingredientPrices, float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientNames = ingredientNames;
        this.ingredientTypes = ingredientTypes;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> getReceiptTestData() {
        return Arrays.asList(new Object[][] {
                {
                        "No ingredients",
                        "black bun", 100.0f,
                        new String[0], new IngredientType[0], new float[0],
                        200.0f  // 100 * 2
                },
                {
                        "One filling",
                        "black bun", 100.0f,
                        new String[]{"cutlet"}, new IngredientType[]{IngredientType.FILLING}, new float[]{100.0f},
                        300.0f  // 200 + 100
                },
                {
                        "Two fillings",
                        "black bun", 100.0f,
                        new String[]{"cutlet", "dinosaur"},
                        new IngredientType[]{IngredientType.FILLING, IngredientType.FILLING},
                        new float[]{100.0f, 200.0f},
                        500.0f  // 200 + 100 + 200
                },
                {
                        "One sauce",
                        "black bun", 100.0f,
                        new String[]{"hot sauce"},
                        new IngredientType[]{IngredientType.SAUCE},
                        new float[]{100.0f},
                        300.0f  // 200 + 100
                }
        });
    }

    @Test
    public void testReceiptWithDifferentIngredients() {
        // Создаем мок булки
        Bun bun = new Bun(bunName, bunPrice);
        burger.setBuns(bun);

        // Создаем и добавляем ингредиенты
        for (int i = 0; i < ingredientNames.length; i++) {
            Ingredient ingredient = new Ingredient(
                    ingredientTypes[i],
                    ingredientNames[i],
                    ingredientPrices[i]
            );
            burger.addIngredient(ingredient);
        }

        String receipt = burger.getReceipt();

        // Проверяем, что чек содержит название булки
        assertTrue("Receipt should contain bun name", receipt.contains(bunName));

        // Проверяем, что чек содержит метку цены
        assertTrue("Receipt should contain Price label", receipt.contains("Price:"));

        // Проверяем наличие ингредиентов в чеке
        for (String ingredientName : ingredientNames) {
            assertTrue("Receipt should contain " + ingredientName,
                    receipt.contains(ingredientName));
        }

        // Проверяем, что цена правильная
        assertEquals("Price should be correct",
                expectedPrice, burger.getPrice(), 0.001f);
    }
}