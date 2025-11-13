import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient sauce;

    @Mock
    Ingredient filling;

    Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(sauce.getName()).thenReturn("sour cream");
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getPrice()).thenReturn(200f);
        Mockito.when(filling.getName()).thenReturn("sausage");
        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getPrice()).thenReturn(300f);
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(sauce);
        assertTrue(burger.ingredients.contains(sauce));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(filling));
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);
        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        float expectedPrice = burger.getPrice();
        assertEquals(expectedPrice, 700, 1);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        String actualReceipt = burger.getReceipt();
        String expectedReceipt = "(==== " + bun.getName() + " ====)\r\n" +
                "= " + sauce.getType().toString().toLowerCase() + " " + sauce.getName() + " =\r\n" +
                "= " + filling.getType().toString().toLowerCase() + " " + filling.getName() + " =\r\n" +
                "(==== " + bun.getName() + " ====)\r\n" + "\r\n" + "Price: " + String.format("%f%n", burger.getPrice());
        assertEquals(expectedReceipt, actualReceipt);
    }
}