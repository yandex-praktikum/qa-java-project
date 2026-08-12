package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.SoftAssertions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BurgerShouldTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBunField() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(sauce);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(sauce);
        softly.assertAll();
    }

    @Test
    public void removeIngredientShouldRemoveIngredientFromList() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(filling);
        softly.assertAll();
    }

    @Test
    public void moveIngredientShouldChangeIngredientsOrder() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(filling);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(sauce);
        softly.assertAll();
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithoutIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        float price = burger.getPrice();

        assertThat(price, is(200f));
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getPrice()).thenReturn(75f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float price = burger.getPrice();

        assertThat(price, is(325f));
        verify(sauce, times(1)).getPrice();
        verify(filling, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldReturnCorrectReceiptWithIngredients() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(50f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();

        String expected = String.format("(==== %s ====)%n", "black bun")
                + String.format("= %s %s =%n", "sauce", "hot sauce")
                + String.format("(==== %s ====)%n", "black bun")
                + String.format("%nPrice: %f%n", 250f);

        assertEquals(expected, receipt);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceiptWithoutIngredients() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        String expected = String.format("(==== %s ====)%n", "white bun")
                + String.format("(==== %s ====)%n", "white bun")
                + String.format("%nPrice: %f%n", 400f);

        assertEquals(expected, receipt);
    }
}