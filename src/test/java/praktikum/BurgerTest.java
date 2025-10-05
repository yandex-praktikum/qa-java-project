package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling1;
    private Ingredient filling2;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = new Bun("black bun", 100f);
        burger.setBuns(bun);

        sauce = new Ingredient(IngredientType.SAUCE, "sour cream", 200f);
        filling1 = new Ingredient(IngredientType.FILLING, "cutlet", 100f);
        filling2 = new Ingredient(IngredientType.FILLING, "dinosaur", 200f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling1);
        burger.addIngredient(filling2);
    }

    @Test
    public void testGetReceipt() {
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce sour cream ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("= filling dinosaur ="));
        assertTrue(receipt.contains("Price: 700"));
    }

    @Test
    public void testAddIngredient() {
        Ingredient cheese = new Ingredient(IngredientType.FILLING, "cheese", 50f);
        burger.addIngredient(cheese);

        assertEquals(4, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(cheese));
    }

    @Test
    public void testRemoveIngredient() {
        burger.removeIngredient(1); // удаляем "cutlet"

        assertEquals(2, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(filling1));
    }

    @Test
    public void testMoveIngredient() {
        burger.moveIngredient(0, 2); // sauce переместится в конец

        assertEquals(filling1, burger.ingredients.get(0));
        assertEquals(filling2, burger.ingredients.get(1));
        assertEquals(sauce, burger.ingredients.get(2));
    }

    @Test
    public void testGetPrice() {
        float price = burger.getPrice();
        assertEquals(700f, price, 0.001f);
    }
}
