package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        // Цена булок: 100 * 2 = 200, цена ингредиента: 50. Итого: 250.
        float expectedPrice = 250.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String expectedReceipt = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "(==== black bun ====)\n\n" +
                "Price: 250,000000\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
