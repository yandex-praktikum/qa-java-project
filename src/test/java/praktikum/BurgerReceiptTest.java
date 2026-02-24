package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerReceiptTest {
    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Cardboard bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("Space Sauce");
        when(sauce.getPrice()).thenReturn(300f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("Beef");
        when(filling.getPrice()).thenReturn(500f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String actual = burger.getReceipt();
        String expected = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%n" +
                        "Price: %f%n",
                "Cardboard bun",
                "sauce", "Space Sauce",
                "filling", "Beef",
                "Cardboard bun",
                1000f);

        assertEquals(expected, actual);
    }
}
