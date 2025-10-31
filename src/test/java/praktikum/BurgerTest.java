package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
    }

    @Test
    public void addIngredient_appendsToList() {
        Ingredient ing = mock(Ingredient.class);
        burger.addIngredient(ing);
        assertEquals(1, burger.ingredients.size());
        assertSame(ing, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient_removesByIndex() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertSame(b, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredient_movesElementToNewIndex() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);
        burger.addIngredient(a); // 0
        burger.addIngredient(b); // 1
        burger.addIngredient(c); // 2
        burger.moveIngredient(2, 0);
        assertSame(c, burger.ingredients.get(0));
        assertSame(a, burger.ingredients.get(1));
        assertSame(b, burger.ingredients.get(2));
    }

    @Test
    public void getPrice_sumsBunTwiceAndIngredients() {
        Ingredient i1 = mock(Ingredient.class);
        when(i1.getPrice()).thenReturn(50f);
        Ingredient i2 = mock(Ingredient.class);
        when(i2.getPrice()).thenReturn(25.5f);
        burger.addIngredient(i1);
        burger.addIngredient(i2);
        float expected = 100f * 2 + 50f + 25.5f;
        assertEquals(expected, burger.getPrice(), 0.0001f);
    }

    @Test
    public void getReceipt_printsFormattedReceipt() {
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(100f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(200f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        String expected = String.format(
                "(==== %s ====)%n" +
                "= %s %s =%n" +
                "= %s %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n",
                "black bun",
                "sauce", "hot sauce",
                "filling", "cutlet",
                "black bun",
                100f * 2 + 100f + 200f
        );

        assertEquals(expected, receipt);

        verify(bun, atLeastOnce()).getName();
        verify(bun, atLeastOnce()).getPrice();
        verify(sauce, atLeastOnce()).getType();
        verify(sauce, atLeastOnce()).getName();
        verify(filling, atLeastOnce()).getType();
        verify(filling, atLeastOnce()).getName();
    }
}


