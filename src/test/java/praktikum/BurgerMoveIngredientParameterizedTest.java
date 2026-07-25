package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientParameterizedTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private Burger burger;

    private final int index;
    private final int newIndex;
    private final int[] expectedOrder;

    public BurgerMoveIngredientParameterizedTest(int index, int newIndex, int[] expectedOrder) {
        this.index = index;
        this.newIndex = newIndex;
        this.expectedOrder = expectedOrder;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {0, 2, new int[] {1, 2, 0}},
                {2, 0, new int[] {2, 0, 1}},
                {1, 0, new int[] {1, 0, 2}},
                {1, 1, new int[] {0, 1, 2}},
        };
    }

    @Test
    public void burgerMoveIngredientsPositiveTest() {
        ingredient1 = Mockito.mock(Ingredient.class);
        ingredient2 = Mockito.mock(Ingredient.class);
        ingredient3 = Mockito.mock(Ingredient.class);

        burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        Ingredient[] original = {ingredient1, ingredient2, ingredient3};
        burger.moveIngredient(index, newIndex);

        assertEquals(3, burger.ingredients.size());

        for (int i = 0; i < expectedOrder.length; i++) {
            assertEquals(original[expectedOrder[i]], burger.ingredients.get(i));
        }
    }
}
