
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest extends BaseBurgerTest {



    @Test
    public void getPriceReturnsDoubleBunPriceWhenNoIngredients() {
        double price = burger.getPrice();
        assertEquals(200.0, price, 0.001);
    }

    @Test
    public void getPriceAddsIngredientPrices() {
        Ingredient ingFirst = Mockito.mock(Ingredient.class);
        Ingredient ingSecond = Mockito.mock(Ingredient.class);

        when(ingFirst.getPrice()).thenReturn(50f);
        when(ingSecond.getPrice()).thenReturn(30f);

        burger.addIngredient(ingFirst);
        burger.addIngredient(ingSecond);

        float price = burger.getPrice();
        assertEquals(280.0f, price, 0.001f);
    }

    @Test(expected = NullPointerException.class)
    public void getPrice_throws_whenBunIsNull() {
        Burger brokenBurger = new Burger();
        brokenBurger.getPrice();
    }

    @Test
    public void removeIngredientDecreasesSize() {
        Ingredient ing = Mockito.mock(Ingredient.class);
        burger.addIngredient(ing);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_throwsForEmptyList() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientChangesOrder() {
        Ingredient ingFirst = new Ingredient(IngredientType.FILLING, "First", 10);
        Ingredient ingSecond = new Ingredient(IngredientType.FILLING, "Second", 20);

        burger.addIngredient(ingFirst);
        burger.addIngredient(ingSecond);

        burger.moveIngredient(0, 1);

        assertEquals(Arrays.asList("Second", "First"),
                burger.ingredients.stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toList()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_throwsForInvalidIndex() {
        burger.addIngredient(ingredientStub);
        burger.moveIngredient(5, 0);
    }

    @Test
    public void getReceiptContainsBunName() {
        burger.addIngredient(ingredientStub);
        String receipt = burger.getReceipt();
        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                burger.bun.getName(),
                ingredientStub.getType().toString().toLowerCase(),
                ingredientStub.getName(),
                burger.bun.getName(),
                burger.getPrice()
        );
         assertEquals(expectedReceipt, receipt);
    }

      @Test
    public void getReceipt_containsIngredientInLowercase() {
        burger.addIngredient(ingredientStub);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce Hot Sauce ="));
    }

    @Test
    public void getReceiptContainsPrice() {
        burger.addIngredient(ingredientStub);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price: 250,000000"));
    }

    @Test(expected = NullPointerException.class)
    public void getReceiptThrowsWhenBunIsNull() {
        Burger brokenBurger = new Burger();
        brokenBurger.addIngredient(ingredientStub);
        brokenBurger.getReceipt();
    }
}