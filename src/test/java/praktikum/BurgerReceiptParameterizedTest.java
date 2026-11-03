package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {
    private final Bun bun;
    private final List<Ingredient> ingredients;

    private Burger burger;

    public BurgerReceiptParameterizedTest(Bun bun, List<Ingredient> ingredients) {
        this.bun = bun;
        this.ingredients = ingredients;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {
                        new Bun("white", 0f),
                        List.of(),
                },
                {
                        new Bun("black", 0f),
                        List.of(
                                new Ingredient(IngredientType.FILLING, "filling", 0f)
                        ),
                },
                {
                        new Bun("red", 0f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "sauce", 0f),
                                new Ingredient(IngredientType.FILLING, "filling", 0f)
                        ),
                },
                {
                        new Bun("red", 0f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "sauce", 0f),
                                new Ingredient(IngredientType.FILLING, "filling", 0f),
                                new Ingredient(IngredientType.FILLING, "cutlet", 0f)
                        ),
                },
        };
    }

    @Before
    public void init() {
        burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
    }

    private String getExpectedReceipt() {
        StringBuilder receiptBuilder = new StringBuilder()
                .append(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : ingredients) {
            receiptBuilder.append(String.format("= %s %s =%n", getIngredientTypeAsString(ingredient.getType()), ingredient.getName()));
        }

        receiptBuilder
                .append(String.format("(==== %s ====)%n%n", bun.getName()))
                .append(String.format("Price: %f%n", 0f));

        return receiptBuilder.toString();
    }

    private String getIngredientTypeAsString(IngredientType ingredientType) {
        String ingredientTypeAsString = "";

        switch (ingredientType) {
            case SAUCE:
                ingredientTypeAsString = "sauce";
                break;
            case FILLING:
                ingredientTypeAsString = "filling";
                break;
        }

        return ingredientTypeAsString;
    }

    @Test
    public void getReceiptParameterizedTest() {
        String expectedReceipt = getExpectedReceipt();
        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}
