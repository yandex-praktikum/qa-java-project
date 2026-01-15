package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.TestData.*;

@RunWith(Parameterized.class)
public class BurgerParametrizedTests {

    private Burger burger;
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerParametrizedTests(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "{index}: {0} + {1} ingredient(s)")
    public static Collection<Object[]> data() {
        Bun blackBun = new Bun(BUN_ONE, PRICE_ONE);
        Bun whiteBun = new Bun(BUN_TWO, PRICE_TWO);
        Bun redBun = new Bun(BUN_THREE, PRICE_THREE);

        Ingredient hotSauce = new Ingredient(IngredientType.SAUCE, SAUCE_ONE, PRICE_ONE);
        Ingredient sourCream = new Ingredient(IngredientType.SAUCE, SAUCE_TWO, PRICE_TWO);
        Ingredient chiliSauce = new Ingredient(IngredientType.SAUCE, SAUCE_THREE, PRICE_THREE);

        Ingredient cutlet = new Ingredient(IngredientType.FILLING, FILLING_ONE, PRICE_ONE);
        Ingredient dinosaur = new Ingredient(IngredientType.FILLING, FILLING_TWO, PRICE_TWO);
        Ingredient sausage = new Ingredient(IngredientType.FILLING, FILLING_THREE, PRICE_THREE);

        return Arrays.asList(new Object[][]{
                {
                        blackBun,
                        List.of(),
                        String.format("(==== black bun ====)%n" +
                                "(==== black bun ====)%n%n" +
                                "Price: 200,000000%n")
                },
                {
                        whiteBun,
                        List.of(hotSauce),
                        String.format("(==== white bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "(==== white bun ====)%n%n" +
                                "Price: 500,000000%n")
                },
                {
                        redBun,
                        List.of(cutlet),
                        String.format("(==== red bun ====)%n" +
                                "= filling cutlet =%n" +
                                "(==== red bun ====)%n%n" +
                                "Price: 700,000000%n")
                },
                {
                        blackBun,
                        Arrays.asList(sourCream, dinosaur),
                        String.format("(==== black bun ====)%n" +
                                "= sauce sour cream =%n" +
                                "= filling dinosaur =%n" +
                                "(==== black bun ====)%n%n" +
                                "Price: 600,000000%n")
                },
                {
                        whiteBun,
                        Arrays.asList(hotSauce, sourCream, cutlet, dinosaur),
                        String.format("(==== white bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "= sauce sour cream =%n" +
                                "= filling cutlet =%n" +
                                "= filling dinosaur =%n" +
                                "(==== white bun ====)%n%n" +
                                "Price: 1000,000000%n")
                },
                {
                        redBun,
                        Arrays.asList(hotSauce, sourCream, chiliSauce, cutlet, dinosaur, sausage),
                        String.format("(==== red bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "= sauce sour cream =%n" +
                                "= sauce chili sauce =%n" +
                                "= filling cutlet =%n" +
                                "= filling dinosaur =%n" +
                                "= filling sausage =%n" +
                                "(==== red bun ====)%n%n" +
                                "Price: 1800,000000%n")
                },
                {
                        blackBun,
                        Arrays.asList(cutlet, hotSauce, dinosaur, sourCream),
                        String.format("(==== black bun ====)%n" +
                                "= filling cutlet =%n" +
                                "= sauce hot sauce =%n" +
                                "= filling dinosaur =%n" +
                                "= sauce sour cream =%n" +
                                "(==== black bun ====)%n%n" +
                                "Price: 800,000000%n")
                }
        });
    }

    @Test
    public void getReceipt_shouldReturnCorrectReceiptFormat() {
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}
