package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.setBuns(mockBun);
        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("test sauce");
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("test filling");
        when(mockIngredient2.getPrice()).thenReturn(75f);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        int expectedSizeBeforeRemoving = 2;
        int expectedSizeAfterRemoving = 1;
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        int actualSizeBeforeRemoving = burger.ingredients.size();

        burger.removeIngredient(0);
        int actualSizeAfterRemoving = burger.ingredients.size();

        assertEquals(expectedSizeBeforeRemoving, actualSizeBeforeRemoving);
        assertEquals(expectedSizeAfterRemoving, actualSizeAfterRemoving);
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(2, 0);
        assertEquals(mockIngredient3, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
        assertEquals(mockIngredient2, burger.ingredients.get(2));
    }

    @Test
    public void testGetPrice() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 100f * 2 + 50f + 75f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithoutIngredients() {
        assertEquals(200f, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String expectedReceipt = "(==== test bun ====)\n" +
                "= sauce test sauce =\n" +
                "= filling test filling =\n" +
                "(==== test bun ====)\n" +
                "\n" +
                "Price: 325,000000\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    @Test
    public void testGetReceiptWithNoIngredients() {
        String expectedReceipt = "(==== test bun ====)\n" +
                "(==== test bun ====)\n" +
                "\n" +
                "Price: 200,000000\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
