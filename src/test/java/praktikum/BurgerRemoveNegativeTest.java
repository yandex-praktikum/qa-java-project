package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static praktikum.TestConstants.*;

@RunWith(Parameterized.class)
public class BurgerRemoveNegativeTest {

    private final int removeIndex;

    public BurgerRemoveNegativeTest(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    @Parameters(name = "removeIndex={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {INVALID_INDEX},
                {SIZE},
                {LARGE_INDEX}
        });
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionForInvalidIndex() {
        Burger burger = new Burger();
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));

        burger.removeIngredient(removeIndex);
    }
}
