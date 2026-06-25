package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static praktikum.TestConstants.*;

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
                {FIRST, THIRD, new Object[]{SECOND, THIRD, FIRST}},
                {THIRD, FIRST, new Object[]{THIRD, FIRST, SECOND}},
                {SECOND, FIRST, new Object[]{SECOND, FIRST, THIRD}},
                {FIRST, SECOND, new Object[]{SECOND, FIRST, THIRD}},
                {FIRST, FIRST, new Object[]{FIRST, SECOND, THIRD}},
                {THIRD, THIRD, new Object[]{FIRST, SECOND, THIRD}},
                {THIRD, SECOND, new Object[]{FIRST, THIRD, SECOND}}
        });
    }

    @Test
    public void moveIngredientShouldKeepSize() {
        Burger burger = new Burger();
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));
        burger.addIngredient(mock(Ingredient.class));

        burger.moveIngredient(fromIndex, toIndex);

        assertEquals(SIZE, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldPlaceCorrectIngredientAtFirstPosition() {
        Burger burger = new Burger();
        Ingredient ing0 = mock(Ingredient.class);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        burger.addIngredient(ing0);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(fromIndex, toIndex);

        int expectedIndex = (int) expectedOrder[0];
        switch (expectedIndex) {
            case FIRST:
                assertSame(ing0, burger.ingredients.get(0));
                break;
            case SECOND:
                assertSame(ing1, burger.ingredients.get(0));
                break;
            case THIRD:
                assertSame(ing2, burger.ingredients.get(0));
                break;
            default:
                fail("Неожиданный индекс: " + expectedIndex);
        }
    }

    @Test
    public void moveIngredientShouldPlaceCorrectIngredientAtSecondPosition() {
        Burger burger = new Burger();
        Ingredient ing0 = mock(Ingredient.class);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        burger.addIngredient(ing0);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(fromIndex, toIndex);

        int expectedIndex = (int) expectedOrder[1];
        switch (expectedIndex) {
            case FIRST:
                assertSame(ing0, burger.ingredients.get(1));
                break;
            case SECOND:
                assertSame(ing1, burger.ingredients.get(1));
                break;
            case THIRD:
                assertSame(ing2, burger.ingredients.get(1));
                break;
            default:
                fail("Неожиданный индекс: " + expectedIndex);
        }
    }

    @Test
    public void moveIngredientShouldPlaceCorrectIngredientAtThirdPosition() {
        Burger burger = new Burger();
        Ingredient ing0 = mock(Ingredient.class);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        burger.addIngredient(ing0);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(fromIndex, toIndex);

        int expectedIndex = (int) expectedOrder[2];
        switch (expectedIndex) {
            case FIRST:
                assertSame(ing0, burger.ingredients.get(2));
                break;
            case SECOND:
                assertSame(ing1, burger.ingredients.get(2));
                break;
            case THIRD:
                assertSame(ing2, burger.ingredients.get(2));
                break;
            default:
                fail("Неожиданный индекс: " + expectedIndex);
        }
    }
}
