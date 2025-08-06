package praktikum;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private Burger burger;
    private Bun bun;
    private List<Ingredient> ingredients;
    private String expectedReceipt;

    public BurgerReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt){
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object [][] getTestData() {
        Bun bun = new Bun("black bun", 100);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "hot souce", 50);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "cutlet", 70);

        return new Object[][]{
                {bun, Arrays.asList(sauce),
                        "(==== black bun ====)\n= sauce hot sauce =\n(==== black bun ====)\n\nPrice: 250.000000\n"},
                {bun, Arrays.asList(filling),
                        "(==== black bun ====)\n= filling cutlet =\n(==== black bun ====)\n\nPrice: 270.000000\n"}
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        assertEquals(expectedReceipt, burger.getReceipt());
    }

}
