package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("sour cream");
        when(sauce.getPrice()).thenReturn(200f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("dinosaur");
        when(filling.getPrice()).thenReturn(300f);
    }

    @Test
    public void setBuns_shouldSetBun() {
        burger.setBuns(bun);
        assertNotNull(burger.bun);
        assertEquals("black bun", burger.bun.getName());
    }

    @Test
    public void addIngredient_shouldAddToIngredientsList() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
        assertEquals("sour cream", burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredient_shouldRemoveFromList() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_withInvalidIndex_shouldThrowException() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredient_shouldChangePosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);

        List<Ingredient> ingredients = burger.ingredients;
        assertEquals("dinosaur", ingredients.get(0).getName());
        assertEquals("sour cream", ingredients.get(1).getName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_withInvalidIndex_shouldThrowException() {
        burger.addIngredient(sauce);
        burger.moveIngredient(0, 2);
    }

    @Test
    public void getPrice_shouldCalculateCorrectPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expected = 100 * 2 + 200 + 300;
        assertEquals(expected, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceipt_shouldReturnCorrectFormat() {
        Locale.setDefault(Locale.US);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expected = String.format(
                "(==== black bun ====)%n" +
                        "= sauce sour cream =%n" +
                        "= filling dinosaur =%n" +
                        "(==== black bun ====)%n%n" +
                        "Price: 700.000000%n"
        );

        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void getPrice_withoutIngredients_shouldCalculateCorrectPrice() {
        burger.setBuns(bun);
        float expected = 100 * 2;
        assertEquals(expected, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceipt_withoutIngredients_shouldReturnCorrectFormat() {
        Locale.setDefault(Locale.US);
        burger.setBuns(bun);

        String expected = String.format(
                "(==== black bun ====)%n" +
                        "(==== black bun ====)%n%n" +
                        "Price: 200.000000%n"
        );

        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void getReceipt_withOneIngredient_shouldReturnCorrectFormat() {
        Locale.setDefault(Locale.US);
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