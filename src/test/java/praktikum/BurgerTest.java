package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest extends BaseTest {

    private static final int INVALID_INDEX = 0;
    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final int EXPECTED_SIZE_AFTER_ADD = 1;
    private static final int EXPECTED_SIZE_AFTER_REMOVE = 0;

    @Test
    public void SetBunsTest() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(bunMock.getName()).thenReturn("test bun");

        burger.setBuns(bunMock);

        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void IncreasesSizeAddedIngredientTest() {
        when(ingredientMock.getPrice()).thenReturn(50.0f);

        burger.addIngredient(ingredientMock);

        assertEquals(EXPECTED_SIZE_AFTER_ADD, burger.ingredients.size());
    }

    @Test
    public void ContainsAddedIngredientTest() {
        when(ingredientMock.getPrice()).thenReturn(50.0f);

        burger.addIngredient(ingredientMock);

        assertTrue(burger.ingredients.contains(ingredientMock));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void RemoveIngredientWithInvalidIndexTest() {
        burger.removeIngredient(INVALID_INDEX);
    }

    @Test
    public void RemoveIngredientDecreasesSizeTest() {
        when(ingredientMock.getPrice()).thenReturn(50.0f);
        burger.addIngredient(ingredientMock);

        burger.removeIngredient(FIRST_POSITION);

        assertEquals(EXPECTED_SIZE_AFTER_REMOVE, burger.ingredients.size());
    }

    @Test
    public void RemoveIngredientRemovesCorrectIngredientTest() {
        when(ingredientMock.getPrice()).thenReturn(50.0f);
        burger.addIngredient(ingredientMock);

        burger.removeIngredient(FIRST_POSITION);

        assertFalse(burger.ingredients.contains(ingredientMock));
    }

    @Test
    public void MoveIngredientMovesIngredientToNewPositionTest() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(FIRST_POSITION, SECOND_POSITION);

        assertEquals(ingredient2, burger.ingredients.get(FIRST_POSITION));
    }

    @Test
    public void MoveIngredientOriginalPositionIsFilledWithOtherIngredientTest() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(FIRST_POSITION, SECOND_POSITION);

        assertEquals(ingredient1, burger.ingredients.get(SECOND_POSITION));
    }
}
