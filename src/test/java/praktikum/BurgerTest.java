package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    private AutoCloseable closeable;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(bun.getName()).thenReturn("Космическая булка");
        when(bun.getPrice()).thenReturn(2.5f);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("Соус фирменный");
        when(sauce.getPrice()).thenReturn(1.0f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("Начинка звездная");
        when(filling.getPrice()).thenReturn(3.0f);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.getBun());
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.getIngredients().size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(sauce);
        assertEquals(sauce, burger.getIngredients().get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertEquals(0, burger.getIngredients().size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);
        assertEquals(Arrays.asList(filling, sauce), burger.getIngredients());
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        float expected = 2.5f * 2 + 1.0f + 3.0f;
        assertEquals(expected, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        String expectedReceipt = "(==== Космическая булка ====)\n" +
                "= sauce Соус фирменный =\n" +
                "= filling Начинка звездная =\n" +
                "(==== Космическая булка ====)\n" +
                "\nPrice: 9.0\n";

        System.out.println("--- ACTUAL RECEIPT ---");
        System.out.println(receipt);
        System.out.println("--- EXPECTED RECEIPT ---");
        System.out.println(expectedReceipt);

        assertEquals(expectedReceipt, receipt);
    }
}
