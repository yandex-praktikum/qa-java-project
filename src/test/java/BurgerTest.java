package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock Bun bun;
    @Mock Ingredient sauce;
    @Mock Ingredient filling;
    @Mock Ingredient third;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsAssignsBun() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientAppendsToList() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(Arrays.asList(sauce, filling), burger.ingredients);
    }

    @Test
    public void removeIngredientRemovesByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(Collections.singletonList(filling), burger.ingredients);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientInvalidIndexThrows() {
        burger.addIngredient(sauce);
        burger.removeIngredient(5);
    }

    @Test
    public void moveIngredientForward() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(third);

        burger.moveIngredient(2, 0);

        assertEquals(Arrays.asList(third, sauce, filling), burger.ingredients);
    }

    @Test
    public void moveIngredientBackward() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertEquals(Arrays.asList(filling, third, sauce), burger.ingredients);
    }

    @Test
    public void getPriceDoublesBunPriceAndSumsIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getPrice()).thenReturn(150f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        // 100*2 + 50 + 150 = 400
        assertEquals(400f, burger.getPrice(), 0.001f);
    }

    @Test
    public void getPriceCallsBunPriceOnce() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        burger.getPrice();

        verify(bun, times(1)).getPrice();
    }

    @Test
    public void getPriceWithoutIngredientsEqualsDoubleBunPrice() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertEquals(200f, burger.getPrice(), 0.001f);
    }

    @Test(expected = NullPointerException.class)
    public void getPriceWithoutBunThrowsNpe() {
        burger.getPrice();
    }

    @Test
    public void getReceiptRendersBunAndIngredientsInExpectedOrder() {
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getPrice()).thenReturn(150f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expected = String.format(
                "(==== test bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "= filling cutlet =%n" +
                        "(==== test bun ====)%n" +
                        "%nPrice: %f%n",
                400f
        );

        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void getReceiptCallsBunNameTwice() {
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        burger.getReceipt();

        verify(bun, times(2)).getName();
    }

    @Test(expected = NullPointerException.class)
    public void getReceiptWithoutBunThrowsNpe() {
        burger.getReceipt();
    }
}