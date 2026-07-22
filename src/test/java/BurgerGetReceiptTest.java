import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerGetReceiptTest(
            Bun bun,
            List<Ingredient> ingredients,
            String expectedReceipt
    ) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        createBun("black bun", 100f),
                        Arrays.asList(
                                createIngredient(
                                        IngredientType.SAUCE,
                                        "sour cream",
                                        200f
                                ),
                                createIngredient(
                                        IngredientType.FILLING,
                                        "cutlet",
                                        100f
                                ),
                                createIngredient(
                                        IngredientType.FILLING,
                                        "dinosaur",
                                        200f
                                )
                        ),
                        createExpectedReceipt(
                                "black bun",
                                "700,000000",
                                "= sauce sour cream =",
                                "= filling cutlet =",
                                "= filling dinosaur ="
                        )
                },
                {
                        createBun("white bun", 1f),
                        Arrays.asList(
                                createIngredient(
                                        IngredientType.FILLING,
                                        "cutlet",
                                        0.5f
                                ),
                                createIngredient(
                                        IngredientType.SAUCE,
                                        "chili sauce",
                                        4f
                                ),
                                createIngredient(
                                        IngredientType.FILLING,
                                        "sausage",
                                        1f
                                )
                        ),
                        createExpectedReceipt(
                                "white bun",
                                "7,500000",
                                "= filling cutlet =",
                                "= sauce chili sauce =",
                                "= filling sausage ="
                        )
                },
                {
                        createBun("red bun", 1f),
                        Arrays.asList(
                                createIngredient(
                                        IngredientType.SAUCE,
                                        "hot sauce",
                                        5.5f
                                ),
                                createIngredient(
                                        IngredientType.FILLING,
                                        "sausage",
                                        14.5f
                                ),
                                createIngredient(
                                        IngredientType.FILLING,
                                        "dinosaur",
                                        200f
                                )
                        ),
                        createExpectedReceipt(
                                "red bun",
                                "222,000000",
                                "= sauce hot sauce =",
                                "= filling sausage =",
                                "= filling dinosaur ="
                        )
                }
        };
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(expectedReceipt, actualReceipt);
    }

    private static Bun createBun(String name, float price) {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn(name);
        when(bun.getPrice()).thenReturn(price);
        return bun;
    }

    private static Ingredient createIngredient(
            IngredientType type,
            String name,
            float price
    ) {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    private static String createExpectedReceipt(
            String bunName,
            String price,
            String... ingredientLines
    ) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("(==== ")
                .append(bunName)
                .append(" ====)")
                .append(LINE_SEPARATOR);

        for (String ingredientLine : ingredientLines) {
            receipt.append(ingredientLine)
                    .append(LINE_SEPARATOR);
        }

        receipt.append("(==== ")
                .append(bunName)
                .append(" ====)")
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR)
                .append("Price: ")
                .append(price)
                .append(LINE_SEPARATOR);

        return receipt.toString();
    }
}