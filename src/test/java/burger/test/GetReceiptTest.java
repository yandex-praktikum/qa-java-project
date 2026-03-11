package burger.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(Parameterized.class)
public class GetReceiptTest {
    private Burger burger;
    private Bun bun;
    private List<Ingredient> initialIngredients;
    private String expectedReceipt;

    public GetReceiptTest(Bun bun, List<Ingredient> initialIngredients, String expectedReceipt) {
        this.bun = bun;
        this.initialIngredients = initialIngredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void setUp() {
        burger = new Burger();

    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        Ingredient sauce = new Ingredient(SAUCE, "ketchup", 10);
        Ingredient beef = new Ingredient(FILLING, "beef", 15);
        Bun bun = new Bun("bulka", 15);

        return new Object[][]{
                {bun, Arrays.asList(sauce), ("(==== bulka ====)" + " = sauce ketchup = " + "(==== bulka ====)" + " Price: 40,000000")},
                {bun, Arrays.asList(beef), ("(==== bulka ====)" + " = filling beef = " + "(==== bulka ====)" + " Price: 45,000000")},
                {bun, Arrays.asList(sauce, beef), ("(==== bulka ====)" + " = sauce ketchup = " + " = filling beef = " + "(==== bulka ====)" + " Price: 55,000000")},
                {bun, null, ("(==== bulka ====) " + " (==== bulka ====)" + " Price: 30,000000")},
        };
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        if (initialIngredients != null) {
            for (Ingredient ingredient : initialIngredients) {
                burger.addIngredient(ingredient);
            }
        }

        String receipt = burger.getReceipt().replaceAll("\\s+", " ").trim();
        expectedReceipt = expectedReceipt.replaceAll("\\s+", " ").trim();
        assertEquals(expectedReceipt, receipt);
    }
}
