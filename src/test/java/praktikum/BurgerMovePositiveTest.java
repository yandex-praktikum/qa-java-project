package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerMovePositiveTest {

    private final int fromIndex;
    private final int toIndex;
    private final Object[] expectedOrder;

    public BurgerMovePositiveTest(int fromIndex, int toIndex, Object[] expectedOrder) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.expectedOrder = expectedOrder;
    }

    @Parameters(name = "from={0}, to={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 2, new Object[]{1, 2, 0}},
                {2, 0, new Object[]{2, 0, 1}},
                {1, 0, new Object[]{1, 0, 2}},
                {0, 1, new Object[]{1, 0, 2}},
                {0, 0, new Object[]{0, 1, 2}},
                {2, 2, new Object[]{0, 1, 2}},
                {2, 1, new Object[]{0, 2, 1}}
        });
    }

    @Test
    public void moveIngredientShouldMoveCorrectly() {
        Burger burger = new Burger();
        Ingredient ing0 = mock(Ingredient.class);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        burger.addIngredient(ing0);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(fromIndex, toIndex);

        assertEquals(3, burger.ingredients.size());
        for (int i = 0; i < expectedOrder.length; i++) {
            int expectedIndex = (int) expectedOrder[i];
            switch (expectedIndex) {
                case 0:
                    assertSame(ing0, burger.ingredients.get(i));
                    break;
                case 1:
                    assertSame(ing1, burger.ingredients.get(i));
                    break;
                case 2:
                    assertSame(ing2, burger.ingredients.get(i));
                    break;
                default:
                    fail("Неожиданный индекс: " + expectedIndex);
            }
        }
    }
}
