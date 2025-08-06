package praktikum;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private Burger burger;
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt){
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                        new Bun("black bun", 100),
                        List.of(new Ingredient(IngredientType.SAUCE, "hot sauce", 50)),
                        String.format("(==== black bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "(==== black bun ====)%n" +
                                "%n" +
                                "Price: 250,000000%n")
                },
                {
                        new Bun("white bun", 200),
                        List.of(new Ingredient(IngredientType.FILLING, "cutlet", 80)),
                        String.format("(==== white bun ====)%n" +
                                "= filling cutlet =%n" +
                                "(==== white bun ====)%n" +
                                "%n" +
                                "Price: 480,000000%n")
                }
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
