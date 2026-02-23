package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerFunctionalTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testConstructorInitializesIngredientsList() {
        assertNotNull(burger.ingredients);
    }

    @Test
    public void testConstructorInitializesEmptyIngredientsList() {
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testConstructorInitializesNullBun() {
        assertNull(burger.bun);
    }
}
