import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerMoveSizeParameterizedTest extends BaseBurgerTest {

    private final int size;

    public BurgerMoveSizeParameterizedTest(int size) {
        this.size = size;
    }


    @Parameterized.Parameters(name = "Размер: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2}, {3}, {4}, {5}
        });
    }


    @Test
    public void testMovePreservesSize() {

        for (int i = 0; i < size; i++) {
            Ingredient ing = Mockito.mock(Ingredient.class);
            Mockito.when(ing.getPrice()).thenReturn(10f);
            burger.addIngredient(ing);
        }

        int sizeBefore = burger.ingredients.size();
        burger.moveIngredient(0, size - 1);

        assertEquals(sizeBefore, burger.ingredients.size());
    }
}