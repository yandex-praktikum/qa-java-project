import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class BurgerIngredientOperationsTests {

    private Burger burger;

    @Mock
    private Ingredient ingredientToAdd;

    @Mock
    private Ingredient ingredientToRemove;

    @Mock
    private Ingredient remainingIngredient;

    @Mock
    private Ingredient ingredientToMove;

    @Mock
    private Ingredient middleIngredient;

    @Before
    public void setUp() {
        burger = new Burger();

    }


    @Test

    public void shouldAddIngredientSizeIncreases() {
        burger.addIngredient(ingredientToAdd);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void shouldAddIngredientCorrectElementAdded() {
        burger.addIngredient(ingredientToAdd);
        assertEquals(ingredientToAdd, burger.ingredients.get(0));

    }

    @Test

    public void shouldRemoveIngredientSizeDecreases() {
        burger.addIngredient(ingredientToRemove);
        burger.addIngredient(remainingIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void shouldRemoveIngredientCorrectElementRemains() {
        burger.addIngredient(ingredientToRemove);
        burger.addIngredient(remainingIngredient);

        burger.removeIngredient(0);

        assertEquals(remainingIngredient, burger.ingredients.get(0));

    }

    @Test
    public void shouldMoveIngredientFirstPositionChanged() {
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(middleIngredient);
        burger.addIngredient(remainingIngredient);

        burger.moveIngredient(0, 2);

        assertEquals(middleIngredient, burger.ingredients.get(0));
    }

    @Test
    public void shouldMoveIngredientMiddlePositionChanged() {
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(middleIngredient);
        burger.addIngredient(remainingIngredient);

        burger.moveIngredient(0, 2);

        assertEquals(remainingIngredient, burger.ingredients.get(1));
    }

    @Test
    public void shouldMoveIngredientLastPositionChanged() {
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(middleIngredient);
        burger.addIngredient(remainingIngredient);

        burger.moveIngredient(0, 2);
        assertEquals(ingredientToMove, burger.ingredients.get(2));


    }


}
