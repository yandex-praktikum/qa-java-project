package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BurgerTest {
    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = new Bun("Мок-булка", 500f);
        sauce = new Ingredient(IngredientType.SAUCE, "Мок-соус", 100f);
        filling = new Ingredient(IngredientType.FILLING, "Мок-начинка", 300f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
    }

    @Test
    public void setBunsSetsBunNameTest() {
        assertEquals("Мок-булка", burger.bun.getName());
    }

    @Test
    public void setBunsSetsBunPriceTest() {
        assertEquals(500f, burger.bun.getPrice(), 0.01);
    }

    @Test
    public void addIngredientAddsFirstIngredientTest() {
        assertEquals("Мок-соус", burger.ingredients.get(0).getName());
    }

    @Test
    public void addIngredientAddsSecondIngredientTest() {
        assertEquals("Мок-начинка", burger.ingredients.get(1).getName());
    }

    @Test
    public void addIngredientListSizeTest() {
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesByIndexTest() {
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesCorrectItemTest() {
        burger.removeIngredient(0);
        assertEquals("Мок-начинка", burger.ingredients.get(0).getName());
    }

    @Test
    public void moveIngredientMovesIngredientTest() {
        Ingredient extra = new Ingredient(IngredientType.SAUCE, "Мок-доп", 50f);

        burger.addIngredient(extra);
        burger.moveIngredient(2, 0);

        assertEquals("Мок-доп", burger.ingredients.get(0).getName());
    }

    @Test
    public void getPriceReturnsSumOfBunsAndIngredientsTest() {
        float expectedPrice = 500 * 2 + 100 + 300;
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    public void getReceiptContainsBunTest() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Мок-булка"));
    }

    @Test
    public void getReceiptContainsSauceTest() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.toLowerCase().contains("мок-соус"));
    }

    @Test
    public void getReceiptContainsFillingTest() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.toLowerCase().contains("мок-начинка"));
    }

    @Test
    public void getReceiptContainsPriceTest() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("1400.0"));
    }

    @Test
    public void getReceiptContainsPriceWordTest() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price"));
    }
}