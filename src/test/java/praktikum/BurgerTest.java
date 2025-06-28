package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();
        Locale.setDefault(Locale.US);

        // Делаем все заглушки разрешенными (lenient)
        lenient().when(bun.getName()).thenReturn("black bun");
        lenient().when(bun.getPrice()).thenReturn(100f);

        lenient().when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(sauce.getName()).thenReturn("sour cream");
        lenient().when(sauce.getPrice()).thenReturn(200f);

        lenient().when(filling.getType()).thenReturn(IngredientType.FILLING);
        lenient().when(filling.getName()).thenReturn("dinosaur");
        lenient().when(filling.getPrice()).thenReturn(300f);
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);
        assertNotNull(burger.bun);
    }

    @Test
    public void setBunsShouldSetCorrectBunName() {
        burger.setBuns(bun);
        assertEquals("black bun", burger.bun.getName());
    }

    @Test
    public void addIngredientShouldIncreaseListSize() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(sauce);
        assertEquals("sour cream", burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredientShouldDecreaseListSize() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientShouldChangePosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);

        List<Ingredient> ingredients = burger.ingredients;
        assertEquals("dinosaur", ingredients.get(0).getName());
        assertEquals("sour cream", ingredients.get(1).getName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.addIngredient(sauce);
        burger.moveIngredient(0, 2);
    }

    @Test
    public void getPriceShouldCalculateCorrectPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expected = 100 * 2 + 200 + 300;
        assertEquals(expected, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptShouldContainCorrectBunName() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void getReceiptShouldContainCorrectIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= sauce sour cream ="));
    }

    @Test
    public void getReceiptShouldContainCorrectTotalPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 400.000000"));
    }

    @Test
    public void getPriceWithOnlyBunShouldCalculateCorrectPrice() {
        burger.setBuns(bun);
        float expected = 100 * 2;
        assertEquals(expected, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptWithOnlyBunShouldContainBun() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void getReceiptWithOnlyBunShouldNotContainIngredients() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertFalse(receipt.contains("= sauce"));
        assertFalse(receipt.contains("= filling"));
    }

    @Test
    public void getReceiptWithOnlyBunShouldContainCorrectPrice() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 200.000000"));
    }

    @Test
    public void getReceiptWithOneIngredientShouldContainCorrectStructure() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        String expected = String.format(
                "(==== black bun ====)%n" +
                        "= sauce sour cream =%n" +
                        "(==== black bun ====)%n%n" +
                        "Price: 400.000000%n"
        );
        assertEquals(expected, burger.getReceipt());
    }
}