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
public class BurgerRemovePositiveTest {

    private final int removeIndex;
    private final Object[] expectedOrder;

    public BurgerRemovePositiveTest(int removeIndex, Object[] expectedOrder) {
        this.removeIndex = removeIndex;
        this.expectedOrder = expectedOrder;
    }

    @Parameters(name = "removeIndex={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, new Object[]{1, 2}},
                {1, new Object[]{0, 2}},
                {2, new Object[]{0, 1}}
        });
    }

    @Test
    public void removeIngredientShouldDecreaseSize() {
        Burger burger = new Burger();
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));

        burger.removeIngredient(removeIndex);

        assertEquals(expectedOrder.length, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldPlaceCorrectIngredientAtFirstPosition() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.removeIngredient(removeIndex);

        int expectedIndex = (int) expectedOrder[0];
        switch (expectedIndex) {
            case 0:
                assertSame(firstIngredient, burger.ingredients.get(0));
                break;
            case 1:
                assertSame(secondIngredient, burger.ingredients.get(0));
                break;
            case 2:
                assertSame(thirdIngredient, burger.ingredients.get(0));
                break;
            default:
                fail("Неожиданный индекс: " + expectedIndex);
        }
    }

    @Test
    public void removeIngredientShouldPlaceCorrectIngredientAtSecondPosition() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.removeIngredient(removeIndex);

        int expectedIndex = (int) expectedOrder[1];
        switch (expectedIndex) {
            case 0:
                assertSame(firstIngredient, burger.ingredients.get(1));
                break;
            case 1:
                assertSame(secondIngredient, burger.ingredients.get(1));
                break;
            case 2:
                assertSame(thirdIngredient, burger.ingredients.get(1));
                break;
            default:
                fail("Неожиданный индекс: " + expectedIndex);
        }
    }
}
