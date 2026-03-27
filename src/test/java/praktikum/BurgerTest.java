package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(second, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertSame(second, burger.ingredients.get(0));
        assertSame(third, burger.ingredients.get(1));
        assertSame(first, burger.ingredients.get(2));
    }

    @Test
    public void getPriceShouldReturnOnlyBunsPriceWhenNoIngredients() {
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        assertEquals(200f, burger.getPrice(), 0.0f);
        verify(bun, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldReturnCorrectReceiptWithoutIngredients() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);

        burger.setBuns(bun);

        String expected = String.format(
                "(==== %s ====)%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "white bun",
                "white bun",
                400f
        );

        assertEquals(expected, burger.getReceipt());

        verify(bun, times(2)).getName();
        verify(bun, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        Ingredient sauce = mock(Ingredient.class);
        Ingredient filling = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(100f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

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
                400f
        );

        assertEquals(expected, burger.getReceipt());

        verify(bun, times(2)).getName();
        verify(bun, times(1)).getPrice();
        verify(sauce, times(1)).getType();
        verify(sauce, times(1)).getName();
        verify(sauce, atLeastOnce()).getPrice();
        verify(filling, times(1)).getType();
        verify(filling, times(1)).getName();
        verify(filling, atLeastOnce()).getPrice();
    }
}