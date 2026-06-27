package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

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
                {-1, 2},      // fromIndex отрицательный
                {3, 2},               // fromIndex равен размеру
                {10, 2},        // fromIndex больше размера
                {2, -1},      // toIndex отрицательный
                {2, 3},               // toIndex больше размера
                {2, 10},        // toIndex слишком большой
                {-1, -1}, // оба отрицательные
                {10, 10},   // оба слишком большие
                {3, 3}                  // оба равны размеру
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
