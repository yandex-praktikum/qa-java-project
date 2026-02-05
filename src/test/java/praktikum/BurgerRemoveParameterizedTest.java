package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerRemoveParameterizedTest {

    private final int initialCount;
    private final int removeIndex;

    public BurgerRemoveParameterizedTest(int initialCount, int removeIndex) {
        this.initialCount = initialCount;
        this.removeIndex = removeIndex;
    }

    @Parameterized.Parameters(name = "Initial: {0} ingredients, remove index: {1}")
    public static Object[][] getData() {
        return new Object[][] {
                {3, 0},
                {3, 1},
                {3, 2},
                {5, 0},
                {5, 2},
                {5, 4},
                {1, 0}
        };
    }

    @Test
    public void removeIngredientWorksForDifferentIndexes() {
        Burger burger = new Burger();

        for (int i = 0; i < initialCount; i++) {
            burger.addIngredient(mock(Ingredient.class));
        }
        burger.removeIngredient(removeIndex);
        assertEquals("Неверное количество после удаления",
                initialCount - 1, burger.ingredients.size());
    }
}