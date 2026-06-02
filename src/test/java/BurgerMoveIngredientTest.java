import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMoveIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockWhiteIngredient;

    @Mock
    private Ingredient mockBlackIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(mockWhiteIngredient);
        burger.addIngredient(mockBlackIngredient);
        System.out.println(burger.ingredients);
    }

    @Test
    public void testMoveIngredientSuccess() {
        burger.moveIngredient(0, 1);
        assertEquals(mockBlackIngredient, burger.ingredients.get(0));
        assertEquals(mockWhiteIngredient, burger.ingredients.get(1));
        System.out.println(burger.ingredients);
    }
}