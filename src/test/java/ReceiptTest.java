import org.junit.Before;
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
public class ReceiptTest {

    private Burger burger;

    private final String nameBunParam;
    private final String nameSauceParam;
    private final String nameFillingParam;
    private final float priceBun;
    private final float priceSauce;
    private final float priceFilling;
    private final String expectedReceiptContent;

    public ReceiptTest(String nameBunParam, String nameSauceParam, String nameFillingParam, float priceBun, float priceSauce, float priceFilling, String receiptContent) {
        this.nameBunParam = nameBunParam;
        this.nameSauceParam = nameSauceParam;
        this.nameFillingParam = nameFillingParam;
        this.priceBun = priceBun;
        this.priceSauce = priceSauce;
        this.priceFilling = priceFilling;
        this.expectedReceiptContent = receiptContent;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {
                        "black bun", "hot sauce", "cutlet", 100.0f, 100.0f, 100.0f,
                        String.format(
                                "(==== %s ====)%n" +
                                        "= sauce %s =%n" +
                                        "= filling %s =%n" +
                                        "(==== %s ====)%n" +
                                        "%n" +
                                        "Price: %f%n",
                                "black bun", "hot sauce", "cutlet", "black bun", 400.0f
                        )
                },
                {
                        "white bun", "sour cream", "dinosaur", 200.0f, 200.0f, 200.0f,
                        String.format(
                                "(==== %s ====)%n" +
                                        "= sauce %s =%n" +
                                        "= filling %s =%n" +
                                        "(==== %s ====)%n" +
                                        "%n" +
                                        "Price: %f%n",
                                "white bun", "sour cream", "dinosaur", "white bun", 800.0f
                        )
                },
                {
                        "red bun", "chili sauce", "sausage", 300.0f, 300.0f, 300.0f,
                        String.format(
                                "(==== %s ====)%n" +
                                        "= sauce %s =%n" +
                                        "= filling %s =%n" +
                                        "(==== %s ====)%n" +
                                        "%n" +
                                        "Price: %f%n",
                                "red bun", "chili sauce", "sausage", "red bun", 1200.0f
                        )
                },
        };
    }

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void getReceiptTest() {
        Bun bun = Mockito.mock(Bun.class);
        Ingredient sauce = Mockito.mock(Ingredient.class);
        Ingredient filling = Mockito.mock(Ingredient.class);

        Mockito.when(bun.getName()).thenReturn(nameBunParam);
        Mockito.when(bun.getPrice()).thenReturn(priceBun);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn(nameSauceParam);
        Mockito.when(sauce.getPrice()).thenReturn(priceSauce);

        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getName()).thenReturn(nameFillingParam);
        Mockito.when(filling.getPrice()).thenReturn(priceFilling);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(expectedReceiptContent, burger.getReceipt());
    }
}