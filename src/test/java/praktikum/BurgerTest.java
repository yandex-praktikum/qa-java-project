package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.text.DecimalFormat;

public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockSauce;
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
        mockBun = mock(Bun.class);
        mockSauce = mock(Ingredient.class);
        mockFilling = mock(Ingredient.class);

        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("ketchup");
        when(mockSauce.getPrice()).thenReturn(50f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("cutlet");
        when(mockFilling.getPrice()).thenReturn(150f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
    }

    @Test
    public void testSetBuns() {
        assertEquals("test bun", burger.bun.getName());
    }

    @Test
    public void testAddIngredientSize() {
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientName() {
        assertEquals("ketchup", burger.ingredients.get(0).getName());
    }

    @Test
    public void testRemoveIngredient() {
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals("cutlet", burger.ingredients.get(0).getName());
    }

    @Test
    public void testMoveIngredient() {
        burger.moveIngredient(0, 1);
        assertEquals("ketchup", burger.ingredients.get(1).getName());
        assertEquals("cutlet", burger.ingredients.get(0).getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(400f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        DecimalFormat df = new DecimalFormat("#0.0");
        String formattedPrice = df.format(burger.getPrice());

        String expectedReceipt = String.format(
                "(==== test bun ====)%n" +
                        "= sauce ketchup =%n" +
                        "= filling cutlet =%n" +
                        "Price: %s%n", formattedPrice);

        String actualReceipt = burger.getReceipt().replaceAll("\r\n", "\n");
        assertEquals(expectedReceipt, actualReceipt);
    }
}
