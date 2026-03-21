package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerMovingIngredientParameterizedTest {

    private Burger burger;
    private final int index;
    private final int newIndex;

    public BurgerMovingIngredientParameterizedTest(int index, int newIndex) {
        this.index = index;
        this.newIndex = newIndex;
    }

    @Parameterized.Parameters(name = "С {0} на {1}")
    public static Object[][] data() {
        return new Object[][]{
                {0, 2},  // с первого на третье место
                {2, 0},  // с третьего на первое
                {1, 1}   // оставить на месте
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(mock(Ingredient.class)); // ингр 0
        burger.addIngredient(mock(Ingredient.class)); // ингр 1
        burger.addIngredient(mock(Ingredient.class)); // ингр 2
    }

    @Test
    public void testMoveIngredient() {
        Ingredient toMove = burger.ingredients.get(index); //запоминаем, что мы двигаем(разное каждый раз)

        burger.moveIngredient(index, newIndex); //собственно, двигаем

        assertEquals(toMove, burger.ingredients.get(newIndex)); //чекаем, чтобы то, что мы сдвинули (toMove) был на новом месте
    }
}
