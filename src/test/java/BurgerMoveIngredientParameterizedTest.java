import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientParameterizedTest {

    private final int moveFromIndex;
    private final int moveToIndex;

    private Burger burger;
    private Bun bun;
    private Ingredient sauceIngredient;
    private Ingredient fillingIngredient;

    public BurgerMoveIngredientParameterizedTest(int moveFromIndex, int moveToIndex) {
        this.moveFromIndex = moveFromIndex;
        this.moveToIndex = moveToIndex;
    }

    @Parameterized.Parameters(name = "{index}: moveFrom={0}, moveTo={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 1},
                {1, 0}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = Mockito.mock(Bun.class);
        sauceIngredient = Mockito.mock(Ingredient.class);
        fillingIngredient = Mockito.mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getPrice()).thenReturn(100f);
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);

        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getPrice()).thenReturn(100f);
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void movingIngredientToNewPositionUsingMoveIngredient() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.moveIngredient(moveFromIndex, moveToIndex);

        assertEquals(2, burger.ingredients.size());
    }
}
