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
    public void moveIngredientShouldKeepSize() {
        Burger burger = new Burger();
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));

        burger.moveIngredient(fromIndex, toIndex);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldPlaceCorrectIngredientAtFirstPosition() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(fromIndex, toIndex);

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
    public void moveIngredientShouldPlaceCorrectIngredientAtSecondPosition() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(fromIndex, toIndex);

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

    @Test
    public void moveIngredientShouldPlaceCorrectIngredientAtThirdPosition() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(fromIndex, toIndex);

        int expectedIndex = (int) expectedOrder[2];
        switch (expectedIndex) {
            case 0:
                assertSame(firstIngredient, burger.ingredients.get(2));
                break;
            case 1:
                assertSame(secondIngredient, burger.ingredients.get(2));
                break;
            case 2:
                assertSame(thirdIngredient, burger.ingredients.get(2));
                break;
            default:
                fail("Неожиданный индекс: " + expectedIndex);
        }
    }
}
