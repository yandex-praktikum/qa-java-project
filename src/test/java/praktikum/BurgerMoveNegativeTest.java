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
                {-1, 1},   // fromIndex отрицательный
                {3, 1},    // fromIndex равен размеру
                {5, 1},    // fromIndex больше размера
                {1, -1},   // toIndex отрицательный
                {1, 3},    // toIndex больше размера (после удаления размер станет 2, поэтому toIndex=3 вызовет исключение)
                {1, 10},    // toIndex слишком большой
                {-1, -1},   // оба индекса отрицательные
                {10, 10},   // оба индекса слишком большие
                {3, 3}      // оба индекса равны размеру
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
