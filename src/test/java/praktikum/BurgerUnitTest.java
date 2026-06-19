package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class BurgerUnitTest {

    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockSauce;
    @Mock
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    @Test
    public void addSingleIngredientShouldIncreaseSize() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addSingleIngredientShouldContainAddedIngredient() {
        burger.addIngredient(mockSauce);
        assertSame(mockSauce, burger.ingredients.get(0));
    }

    @Test
    public void addMultipleIngredientsShouldIncreaseSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldDecreaseSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldKeepCorrectRemainingElement() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertSame(mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldChangeOrder() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.addIngredient(mockSauce);
        burger.moveIngredient(1, 2);
        assertSame(mockSauce, burger.ingredients.get(0));
        assertSame(mockSauce, burger.ingredients.get(1));
        assertSame(mockFilling, burger.ingredients.get(2));
    }
}