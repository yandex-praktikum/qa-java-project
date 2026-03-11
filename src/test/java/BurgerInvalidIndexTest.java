import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class BurgerInvalidIndexTest {

    private Burger burger;

    private static final int EXISTING_INGREDIENT_INDEX = 0;
    private static final int NON_EXISTENT_INGREDIENT_INDEX = 999;
    private static final int ANOTHER_NON_EXISTENT_INDEX = 1000;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockIngredient1.getName()).thenReturn("Ingredient 1");

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientFromNonExistentIndex() {
        burger.moveIngredient(NON_EXISTENT_INGREDIENT_INDEX, EXISTING_INGREDIENT_INDEX);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientToNonExistentIndex() {
        burger.moveIngredient(EXISTING_INGREDIENT_INDEX, NON_EXISTENT_INGREDIENT_INDEX);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientBetweenNonExistentIndexes() {
        burger.moveIngredient(NON_EXISTENT_INGREDIENT_INDEX, ANOTHER_NON_EXISTENT_INDEX);
    }
}