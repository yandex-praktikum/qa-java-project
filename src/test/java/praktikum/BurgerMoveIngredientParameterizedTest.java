package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientParameterizedTest {

    private Ingredient[] ingredients;
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
        ingredients = new Ingredient[] {
                Mockito.mock(Ingredient.class),
                Mockito.mock(Ingredient.class),
                Mockito.mock(Ingredient.class)
        };

        burger = new Burger();
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        burger.moveIngredient(index, newIndex);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(3);
        for (int i = 0; i < expectedOrder.length; i++) {
            softly.assertThat(burger.ingredients.get(i)).isEqualTo(ingredients[expectedOrder[i]]);
        }
        softly.assertAll();
    }
}
