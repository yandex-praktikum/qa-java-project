import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientTest {

    private Ingredient cheese;
    private Ingredient tomato;
    private Ingredient sauce;

    private final int fromIndex;
    private final int toIndex;
    private final int[] expectedOrder;

    public BurgerMoveIngredientTest(int fromIndex, int toIndex, int[] expectedOrder) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.expectedOrder = expectedOrder;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, 0, new int[]{2, 0, 1}},
                {0, 2, new int[]{1, 2, 0}},
                {1, 1, new int[]{0, 1, 2}}
        });
    }

    @Before
    public void setUp() {
        cheese = mock(Ingredient.class);
        tomato = mock(Ingredient.class);
        sauce = mock(Ingredient.class);
    }

    @Test
    public void moveIngredient_movesIngredientCorrectly() {
        Burger burger = new Burger();
        burger.addIngredient(cheese);
        burger.addIngredient(tomato);
        burger.addIngredient(sauce);

        burger.moveIngredient(fromIndex, toIndex);

        Ingredient[] source = {cheese, tomato, sauce};

        for (int i = 0; i < expectedOrder.length; i++) {
            assertSame(source[expectedOrder[i]], burger.ingredients.get(i));
        }
    }
}

