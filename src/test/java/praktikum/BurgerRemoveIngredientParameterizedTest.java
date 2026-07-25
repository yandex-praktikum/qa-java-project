package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class BurgerRemoveIngredientParameterizedTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private Burger burger;

    private final int index;
    private final int expectedRemainingSize;

    public BurgerRemoveIngredientParameterizedTest(int index, int expectedRemainingSize) {
        this.index = index;
        this.expectedRemainingSize = expectedRemainingSize;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {0, 2},
                {1, 2},
                {2, 2},
        };
    }

    @Test
    public void burgerRemoveIngredientsPositiveTest() {
        ingredient1 = Mockito.mock(Ingredient.class);
        ingredient2 = Mockito.mock(Ingredient.class);
        ingredient3 = Mockito.mock(Ingredient.class);

        burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        Ingredient removedIngredient = burger.ingredients.get(index);
        burger.removeIngredient(index);

        assertEquals(expectedRemainingSize, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(removedIngredient));
    }

}
