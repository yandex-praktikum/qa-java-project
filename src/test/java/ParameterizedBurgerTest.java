import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static constants.ExpectedReceipt.EXPECTED_RECEIPT_FOR_BLACK_BURGER;
import static constants.ExpectedReceipt.EXPECTED_RECEIPT_FOR_WHITE_BURGER;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class ParameterizedBurgerTest {
    private static Bun bun;
    private static Ingredient ingredient;
    private static String expectedReceipt;
    private Burger burger;

    public ParameterizedBurgerTest(Bun bun, Ingredient ingredient,String expectedReceipt){
        this.bun = bun;
        this.ingredient = ingredient;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {bun = new Bun("black bun", 10.0F),
                        ingredient = new Ingredient(SAUCE, "sour cream", 20.0F),
                        EXPECTED_RECEIPT_FOR_BLACK_BURGER
                },
                {bun = new Bun("white bun", 15.0F),
                        ingredient = new Ingredient(FILLING, "cutlet", 25.0F),
                        EXPECTED_RECEIPT_FOR_WHITE_BURGER
                }

        };
    }

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void testGetReceiptParametrized(){
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        System.out.println(burger.getReceipt());
        System.out.println(expectedReceipt);
        assertEquals(expectedReceipt, burger.getReceipt());
    }


}
