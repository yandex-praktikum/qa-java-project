package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final String bunName;
    private final float bunPrice;
    private final String ingredientName;
    private final IngredientType ingredientType;
    private final float ingredientPrice;
    private final String expectedPrice;

    public BurgerReceiptTest(
            String bunName,
            float bunPrice,
            String ingredientName,
            IngredientType ingredientType,
            float ingredientPrice,
            String expectedPrice
    ) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Receipt for {0} and {2}")
    public static Collection<Object[]> getReceiptData() {
        return Arrays.asList(new Object[][]{
                {
                        "black bun",
                        100f,
                        "cutlet",
                        IngredientType.FILLING,
                        100f,
                        "300"
                },
                {
                        "white bun",
                        200f,
                        "hot sauce",
                        IngredientType.SAUCE,
                        50f,
                        "450"
                }
        });
    }

    @Test
    public void getReceiptShouldReturnExpectedReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        when(ingredient.getName()).thenReturn(ingredientName);
        when(ingredient.getType()).thenReturn(ingredientType);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        String regex = ".*" + bunName + ".*" + ingredientType.toString().toLowerCase() + ".*" + ingredientName + ".*Price: " + expectedPrice + ".*";


        String singleLineReceipt = receipt.replace("\n", " ").replace("\r", " ");

         assertTrue("Чек сформирован некорректно. Полученный чек: " + receipt, singleLineReceipt.matches(regex));
    }
}
