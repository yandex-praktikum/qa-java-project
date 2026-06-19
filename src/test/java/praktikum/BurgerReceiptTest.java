package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerReceiptTest {

    @Test
    public void shouldReturnCorrectReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getPrice()).thenReturn(50f);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expected = String.format(
                "(==== black bun ====)%n" +
                        "= filling cutlet =%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n",
                250f
        );

        assertEquals(expected, burger.getReceipt());
    }
}