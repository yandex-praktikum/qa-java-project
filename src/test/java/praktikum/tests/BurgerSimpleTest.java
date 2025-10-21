package praktikum.tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class BurgerSimpleTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);

        burger.setBuns(bun);

        assertEquals("Bun should be set correctly", bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        SoftAssertions softly = new SoftAssertions();
        when(firstIngredient.getName()).thenReturn("hot sauce");
        when(firstIngredient.getPrice()).thenReturn(50.0f);
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);

        burger.addIngredient(firstIngredient);

        softly.assertThat(burger.ingredients).hasSize(1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(firstIngredient);
        softly.assertAll();
    }

    @Test
    public void testRemoveIngredient() {
        burger.ingredients.add(firstIngredient);
        burger.removeIngredient(0);
        assertTrue("Ingredients list should be empty after removal", burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.removeIngredient(0); // Should throw IndexOutOfBoundsException
    }

    @Test
    public void testMoveIngredient() {
        SoftAssertions softly = new SoftAssertions();
        when(firstIngredient.getName()).thenReturn("sauce");
        when(secondIngredient.getName()).thenReturn("filling");

        burger.ingredients.add(firstIngredient);
        burger.ingredients.add(secondIngredient);

        burger.moveIngredient(0, 1);

        softly.assertThat(burger.ingredients.get(0)).isEqualTo(secondIngredient);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(firstIngredient);
        softly.assertAll();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.moveIngredient(0, 0); // Should throw IndexOutOfBoundsException
    }

    @Test(expected = NullPointerException.class)
    public void testGetPriceWithNullBun() {
        burger.setBuns(null);
        burger.getPrice(); // Should throw NullPointerException
    }

    @Test(expected = NullPointerException.class)
    public void testGetReceiptWithNullBun() {
        burger.setBuns(null);
        burger.getReceipt(); // Should throw NullPointerException
    }
}