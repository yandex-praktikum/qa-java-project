package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

@RunWith(Parameterized.class)
public class BurgerRemoveIngredientParameterizedTest {

    private Ingredient[] ingredients;
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
        ingredients = new Ingredient[] {
                Mockito.mock(Ingredient.class),
                Mockito.mock(Ingredient.class),
                Mockito.mock(Ingredient.class)
        };

        burger = new Burger();
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        Ingredient removedIngredient = burger.ingredients.get(index);
        burger.removeIngredient(index);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(expectedRemainingSize);
        softly.assertThat(burger.ingredients.contains(removedIngredient)).isFalse();
        softly.assertAll();
    }

}
