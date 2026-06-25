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
                {FIRST, new Object[]{SECOND, THIRD}},
                {SECOND, new Object[]{FIRST, THIRD}},
                {THIRD, new Object[]{FIRST, SECOND}}
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
        Ingredient ing0 = mock(Ingredient.class);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        burger.addIngredient(ing0);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.removeIngredient(removeIndex);

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
    public void removeIngredientShouldPlaceCorrectIngredientAtSecondPosition() {
        Burger burger = new Burger();
        Ingredient ing0 = mock(Ingredient.class);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        burger.addIngredient(ing0);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.removeIngredient(removeIndex);

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
}
