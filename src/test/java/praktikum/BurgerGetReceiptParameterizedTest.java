package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParameterizedTest {

    private final String bunName;
    private final float bunPrice;
    private final IngredientType[] ingrTypes;
    private final String[] ingrNames;
    private final float[] ingrPrices;

    public BurgerGetReceiptParameterizedTest(String bunName, float bunPrice, IngredientType[] ingrTypes, String[] ingrNames, float[] ingrPrices) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingrTypes = ingrTypes;
        this.ingrNames = ingrNames;
        this.ingrPrices = ingrPrices;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"black bun", 100f,
                        new IngredientType[]{IngredientType.SAUCE},
                        new String[]{"hot sauce"},
                        new float[]{100f}},
                {"white bun", 200f,
                        new IngredientType[]{IngredientType.FILLING},
                        new String[]{"sausage"},
                        new float[]{300f}},
                {"red bun", 300f,
                        new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                        new String[]{"sour cream", "dinosaur"},
                        new float[]{200f, 200f}}
        };
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);

        float expectedPrice = bunPrice * 2;

        for (int i = 0; i < ingrTypes.length; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getType()).thenReturn(ingrTypes[i]);
            Mockito.when(ingredient.getName()).thenReturn(ingrNames[i]);
            Mockito.when(ingredient.getPrice()).thenReturn(ingrPrices[i]);
            burger.addIngredient(ingredient);

            expectedPrice += ingrPrices[i];
        }

        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", bunName));
        for (int i = 0; i < ingrTypes.length; i++) {
            expectedReceipt.append(String.format("= %s %s =%n", ingrTypes[i].toString().toLowerCase(), ingrNames[i]));
        }
        expectedReceipt.append(String.format("(==== %s ====)%n", bunName));
        expectedReceipt.append(String.format("%nPrice: %f%n", expectedPrice));

        String actual = burger.getReceipt();

        assertEquals(expectedReceipt.toString(), actual);
    }
}
