package BurgerTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;

public class GetPriceTest {
    private float actual;
    private float expected;
    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient beef;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        sauce = Mockito.mock(Ingredient.class);
        beef = Mockito.mock(Ingredient.class);

        Mockito.when(bun.getPrice()).thenReturn(50F);
        Mockito.when(sauce.getPrice()).thenReturn(100F);
        Mockito.when(beef.getPrice()).thenReturn(100F);
    }

    @Test
    public void shouldGetPriceWithBunsAndTwoIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(beef);

        expected = 300F;
        actual = burger.getPrice();
        assertEquals(expected, actual, 0.01);

    }

    @Test
    public void shouldGetPriceWithBunsAndOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        expected = 200F;
        actual = burger.getPrice();
        assertEquals(expected, actual, 0.01);

    }

    @Test
    public void shouldGetPriceWithBunsAndWithoutIngredients() {
        burger.setBuns(bun);

        expected = 100F;
        actual = burger.getPrice();
        assertEquals(expected, actual, 0.01);

    }

    @Test
    public void cantGetPriceWithoutBuns() {
        try {
            burger.getPrice();
            fail("Expected NPE");
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }

    }
}
