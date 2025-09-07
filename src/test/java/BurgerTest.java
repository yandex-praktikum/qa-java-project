import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
    }

    @Test
    public void testAddIngredient() {
        Ingredient newIngredient = mock(Ingredient.class);
        burger.addIngredient(newIngredient);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        Ingredient anotherIngredient = mock(Ingredient.class);
        burger.addIngredient(anotherIngredient);

        burger.moveIngredient(1, 0);
        assertEquals(anotherIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientKeepsOtherIngredients() {
        Ingredient anotherIngredient = mock(Ingredient.class);
        burger.addIngredient(anotherIngredient);

        burger.moveIngredient(1, 0);
        assertEquals(mockIngredient, burger.ingredients.get(1));
    }
}
