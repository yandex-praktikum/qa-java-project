package burger.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(Parameterized.class)
public class MoveIngredientTest {
    private Burger burger;
    private List<Ingredient> expected;
    private int fromIndex;
    private int toIndex;
    private List<Ingredient> initialIngredients;

    public MoveIngredientTest(List<Ingredient> initialIngredients, int fromIndex,
                              int toIndex, List<Ingredient> expected) {
        this.initialIngredients = initialIngredients;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        Ingredient sauce = new Ingredient(SAUCE, "sauce", 1);
        Ingredient cheese = new Ingredient(FILLING, "cheese", 1);
        Ingredient beef = new Ingredient(FILLING, "beef", 1);
        Ingredient salad = new Ingredient(FILLING, "salad", 1);

        return new Object[][]{
                {Arrays.asList(sauce, cheese, beef, salad), 0, 3,
                        Arrays.asList(cheese, beef, salad, sauce)},
                {Arrays.asList(sauce, cheese, beef, salad), 3, 0,
                        Arrays.asList(salad, sauce, cheese, beef)},
                {Arrays.asList(sauce, cheese, beef, salad), 1, 2,
                        Arrays.asList(sauce, beef, cheese, salad)},
                {Arrays.asList(sauce, cheese, beef, salad), 1, 1,
                        Arrays.asList(sauce, cheese, beef, salad)}
        };
    }


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void shouldMoveIngredientsCorrectlyTest() {
        for (Ingredient ingredient : initialIngredients) {
            burger.addIngredient(ingredient);
        }

        burger.moveIngredient(fromIndex, toIndex);

        assertEquals(expected, burger.ingredients);
    }
}