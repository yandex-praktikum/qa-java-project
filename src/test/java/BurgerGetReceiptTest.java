import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {

    private Burger burger;
    private String bunName;
    private float bunPrice;
    private List<Ingredient> ingredients;
    private String expectedReceipt;

    public BurgerGetReceiptTest(String bunName, float bunPrice, List<Ingredient> ingredients, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {"White Bun", 1.5f, Arrays.asList(),
                        "(==== White Bun ====)\r\n(==== White Bun ====)\r\n\r\nPrice: 3,000000\r\n"
                },

                {"White Bun", 1.5f, Arrays.asList(new Ingredient(IngredientType.SAUCE, "Ketchup", 0.3f)),
                        "(==== White Bun ====)\r\n= sauce Ketchup =\r\n(==== White Bun ====)\r\n\r\nPrice: 3,300000\r\n"
                },

                {"Black Bun", 2.0f, Arrays.asList(
                        new Ingredient(IngredientType.FILLING, "Bacon", 1.2f),
                        new Ingredient(IngredientType.SAUCE, "BBQ", 0.5f)),
                        "(==== Black Bun ====)\r\n= filling Bacon =\r\n= sauce BBQ =\r\n(==== Black Bun ====)\r\n\r\nPrice: 5,700000\r\n"
                }
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        Bun bun = new Bun(bunName, bunPrice);
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
    }

    @Test
    public void testGetReceiptReturnCorrectReceipt() {
        assertEquals(expectedReceipt, burger.getReceipt());
        System.out.println(expectedReceipt + "\n" + burger.getReceipt());
    }
}