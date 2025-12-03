package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);

        when(bunMock.getName()).thenReturn("black bun");

        when(ingredientMock1.getName()).thenReturn("cutlet");
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);

        when(ingredientMock2.getName()).thenReturn("hot sauce");
        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void testGetReceipt() {
        when(bunMock.getPrice()).thenReturn(100f);
        when(ingredientMock1.getPrice()).thenReturn(20f);
        when(ingredientMock2.getPrice()).thenReturn(10f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("Price:"));
    }
}
