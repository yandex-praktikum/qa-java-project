package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getName()).thenReturn("chili sauce");
        when(sauce.getPrice()).thenReturn(300f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(filling.getName()).thenReturn("sausage");
        when(filling.getPrice()).thenReturn(300f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);

    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesListSize() {
        burger.addIngredient(filling);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientStoresCorrectIngredient() {
        burger.addIngredient(filling);
        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredientChangesFirstElement() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);

        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesSecondElement() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);

        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expected = 100f * 2 + 300f + 300f;
        assertEquals(expected, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = String.format(
               "(==== %s ====)%n" +
               "= %s %s =%n" +
               "= %s %s =%n" +
               "(==== %s ====)%n%n" +
               "Price: %f%n",
               "black bun",
               "sauce", "chili sauce",
               "filling", "sausage",
                "black bun",
                100f * 2 + 300f + 300f
        );

        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt);
    }
}