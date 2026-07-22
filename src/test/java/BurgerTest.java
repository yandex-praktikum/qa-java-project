import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient sauceIngredient;
    private Ingredient fillingIngredient;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = Mockito.mock(Bun.class);
        sauceIngredient = Mockito.mock(Ingredient.class);
        fillingIngredient = Mockito.mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getPrice()).thenReturn(100f);
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);

        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getPrice()).thenReturn(100f);
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void installingBunViaSetBuns() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addingIngredientListUsingAddIngredient() {
        burger.addIngredient(sauceIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removingIngredientByIndexUsingRemoveIngredient() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void gettingTheCorrectPriceBackViaGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        float expectedPrice = 100f * 2 + 100f + 100f;

        assertEquals(expectedPrice, burger.getPrice(), 0.0001);
    }

    @Test
    public void returningCorrectReceiptUsingGetReceipt(){
        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt).contains("(==== black bun ====)");
        softAssertions.assertThat(receipt).contains("= sauce hot sauce =");
        softAssertions.assertThat(receipt).contains("= filling cutlet =");
        softAssertions.assertThat(receipt).contains("(==== black bun ====)");
        softAssertions.assertThat(receipt).contains("Price:");
        softAssertions.assertThat(receipt).contains("400");
        softAssertions.assertAll();
    }

    @Test
    public void getPriceWithoutBunShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }

    @Test
    public void getReceiptContainsAllTheParts() {
        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt).contains("black bun");
        softAssertions.assertThat(receipt).contains("sauce");
        softAssertions.assertThat(receipt).contains("hot sauce");
        softAssertions.assertThat(receipt).contains("Price:");
        softAssertions.assertAll();
    }
}