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
public class BurgerMoveNegativeTest {

    private final int fromIndex;
    private final int toIndex;

    public BurgerMoveNegativeTest(int fromIndex, int toIndex) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Parameters(name = "from={0}, to={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {INVALID_INDEX, SECOND},      // fromIndex отрицательный
                {SIZE, SECOND},               // fromIndex равен размеру
                {LARGE_INDEX, SECOND},        // fromIndex больше размера
                {SECOND, INVALID_INDEX},      // toIndex отрицательный
                {SECOND, SIZE},               // toIndex больше размера
                {SECOND, LARGE_INDEX},        // toIndex слишком большой
                {INVALID_INDEX, INVALID_INDEX}, // оба отрицательные
                {LARGE_INDEX, LARGE_INDEX},   // оба слишком большие
                {SIZE, SIZE}                  // оба равны размеру
        });
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionForInvalidIndexes() {
        Burger burger = new Burger();
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));

        burger.moveIngredient(fromIndex, toIndex);
    }
}
