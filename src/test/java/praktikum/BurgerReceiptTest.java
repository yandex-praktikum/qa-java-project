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
    private final String description;
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerReceiptTest(String description, Bun bun, List<Ingredient> ingredients, String expectedReceipt){
        this.description = description;
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                        "Только булочка (black bun)",
                        new Bun("black bun", 100),
                        List.of(),
                        String.format("(==== black bun ====)%n(==== black bun ====)%n%nPrice: 200,000000%n")
                },
                {
                        "Булочка (white bun) + соус (hot sauce)",
                        new Bun("white bun", 200),
                        List.of(new Ingredient(IngredientType.SAUCE, "hot sauce", 50)),
                        String.format("(==== white bun ====)%n= sauce hot sauce =%n(==== white bun ====)%n%nPrice: 450,000000%n")
                },
                {
                        "Булочка (red bun) + соус (sour cream) + начинка (cutlet)",
                        new Bun("red bun", 300),
                        Arrays.asList(
                                new Ingredient(IngredientType.SAUCE, "sour cream", 20),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100)
                        ),
                        String.format("(==== red bun ====)%n= sauce sour cream =%n= filling cutlet =%n(==== red bun ====)%n%nPrice: 720,000000%n")
                }
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getReceiptReturnsCorrectFormat() {
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
