package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void shouldSetBunsCorrectly() {
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void shouldAddIngredient() {
        Burger burger = new Burger();
        Ingredient ingredientMock = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void shouldRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredientMock = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldMoveIngredient() {
        Burger burger = new Burger();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.moveIngredient(0, 1);
        assertSame(second, burger.ingredients.get(0));
        assertSame(first, burger.ingredients.get(1));
    }

    @Test
    public void shouldGenerateCorrectReceipt() {
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Black Bun");
        burger.setBuns(bunMock);

        Ingredient sauceMock = mock(Ingredient.class);
        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("Spicy Sauce");

        Ingredient fillingMock = mock(Ingredient.class);
        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("Beef Patty");

        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        when(bunMock.getPrice()).thenReturn(200f);
        when(sauceMock.getPrice()).thenReturn(100f);
        when(fillingMock.getPrice()).thenReturn(150f);

        String receipt = burger.getReceipt();

        // Нормализация разделителя десятичных дробей
        receipt = receipt.replace(',', '.');

        // Нормализация перевода строк
        receipt = receipt.replace("\r\n", "\n");

        String expected = "(==== Black Bun ====)\n" +
                "= sauce Spicy Sauce =\n" +
                "= filling Beef Patty =\n" +
                "(==== Black Bun ====)\n\n" +
                "Price: 650.000000\n";

        assertEquals(expected, receipt);
    }
}
