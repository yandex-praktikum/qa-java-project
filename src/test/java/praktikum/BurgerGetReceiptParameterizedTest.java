package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParameterizedTest {

    private final String bunName;
    private final float bunPrice;
    private final String[] ingredientNames;
    private final IngredientType[] ingredientTypes;
    private final float[] ingredientPrices;
    private final String expectedReceipt;

    public BurgerGetReceiptParameterizedTest(String bunName,
                                             float bunPrice,
                                             String[] ingredientNames,
                                             IngredientType[] ingredientTypes,
                                             float[] ingredientPrices,
                                             String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientNames = ingredientNames;
        this.ingredientTypes = ingredientTypes;
        this.ingredientPrices = ingredientPrices;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "Bun: {0}, Price: {1}, Ingredients: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "black bun", 100f,
                        new String[]{"hot sauce"}, new IngredientType[]{IngredientType.SAUCE},
                        new float[]{100f},
                        "(==== black bun ====)\n" +
                                "= sauce hot sauce =\n" +
                                "(==== black bun ====)\n" +
                                "\nPrice: 300,000000\n"
                },
                {
                        "white bun", 200f,
                        new String[]{"sour cream", "cutlet"}, new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                        new float[]{200f, 100f},
                        "(==== white bun ====)\n" +
                                "= sauce sour cream =\n" +
                                "= filling cutlet =\n" +
                                "(==== white bun ====)\n" +
                                "\nPrice: 700,000000\n"
                },
                {
                        "red bun", 300f,
                        new String[]{"chili sauce", "dinosaur", "sausage"}, new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING, IngredientType.FILLING},
                        new float[]{300f, 200f, 300f},
                        "(==== red bun ====)\n" +
                                "= sauce chili sauce =\n" +
                                "= filling dinosaur =\n" +
                                "= filling sausage =\n" +
                                "(==== red bun ====)\n" +
                                "\nPrice: 1400,000000\n"
                }
        });
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (int i = 0; i < ingredientNames.length; i++) {
            Ingredient ing = mock(Ingredient.class);
            when(ing.getName()).thenReturn(ingredientNames[i]);
            when(ing.getType()).thenReturn(ingredientTypes[i]);
            when(ing.getPrice()).thenReturn(ingredientPrices[i]);
            burger.addIngredient(ing);
        }

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}

