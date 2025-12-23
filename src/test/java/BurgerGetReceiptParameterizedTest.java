
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParameterizedTest {
    private final String bunName;
    private final float bunPrice;

    private final IngredientType firstIngType;
    private final String firstIngName;
    private final float firstIngPrice;

    private final IngredientType secondIngType;
    private final String secondIngName;
    private final float secondIngPrice;

    private final float expectedPrice;

    public BurgerGetReceiptParameterizedTest(String bunName,
                                             float bunPrice,
                                             IngredientType firstIngType,
                                             String firstIngName,
                                             float firstIngPrice,
                                             IngredientType secondIngType,
                                             String secondIngName,
                                             float secondIngPrice,
                                             float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.firstIngType = firstIngType;
        this.firstIngName = firstIngName;
        this.firstIngPrice = firstIngPrice;
        this.secondIngType = secondIngType;
        this.secondIngName = secondIngName;
        this.secondIngPrice = secondIngPrice;
        this.expectedPrice = expectedPrice;
    }


    @Parameterized.Parameters(name = "{index}: receipt with bun- {0}, price={1}, " +
            "ing1 -{3} price={4}, ing2 -{6} price={7}, total={8}")
    public static Object[][] getReceiptData() {
        return new Object[][]{
                {
                        "White Bun", 100f,
                        IngredientType.SAUCE, "Ketchup", 20f,
                        IngredientType.FILLING, "Beef", 50f,
                        100f * 2 + 20f + 50f
                },
                {
                        "Black Bun", 80f,
                        IngredientType.SAUCE, "Mayo", 10f,
                        IngredientType.FILLING, "Chicken", 15f,
                        80f * 2 + 10f + 15f
                }
        };
    }

    @Test
    public void testGetReceiptContainsParts() {
        Bun bun = Mockito.mock(Bun.class);
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Mockito.when(firstIngredient.getName()).thenReturn(firstIngName);
        Mockito.when(firstIngredient.getType()).thenReturn(firstIngType);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngPrice);

        Mockito.when(secondIngredient.getName()).thenReturn(secondIngName);
        Mockito.when(secondIngredient.getType()).thenReturn(secondIngType);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String receipt = burger.getReceipt();

        String formattedPrice = String.format("%.6f", expectedPrice).replace('.', ',');
        String expectedReceipt = "(==== "+bunName+" ====)\r\n" +
                "= "+firstIngType.toString().toLowerCase()+" "+firstIngName+" =\r\n" +
                "= "+secondIngType.toString().toLowerCase()+" "+secondIngName+" =\r\n" +
                "(==== "+bunName+" ====)\r\n" +
                "\r\n" +
                "Price: "+formattedPrice+"\r\n";


        assertEquals(expectedReceipt, receipt);

    }
}

