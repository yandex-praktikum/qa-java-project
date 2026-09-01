package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientParameterizedTest {

    @Parameterized.Parameter(0)
    public int fromIndex;

    @Parameterized.Parameter(1)
    public int toIndex;

    @Parameterized.Parameter(2)
    public int expectedPositionOfFirstIngredient;

    @Parameterized.Parameters(name = "Move from {0} to {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 1, 1},
                {1, 0, 0},
                {0, 2, 2},
                {2, 0, 0},
        });
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(fromIndex, toIndex);

        if (fromIndex == 0) {
            assertEquals(firstIngredient, burger.ingredients.get(expectedPositionOfFirstIngredient));
        }
    }
}

