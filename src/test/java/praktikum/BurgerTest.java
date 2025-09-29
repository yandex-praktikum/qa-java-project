package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class BurgerTest {

    @Test
    public void testSetBuns() {
        Bun bun = new Bun("Ржаной", 5.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals("Ржаной", burger.bun.getName());
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50.0f);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals("hot sauce", burger.ingredients.get(0).getName());
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50.0f);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 50.0f);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.moveIngredient(0, 1);
        assertEquals("cutlet", burger.ingredients.get(0).getName());
        assertEquals("hot sauce", burger.ingredients.get(1).getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun("Ржаной", 2.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 1.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 2.0f));
        // 2 булки + 1 + 2 = 2*2 + 1 + 2 = 7.0
        assertEquals(7.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceiptWithBunAndIngredients() {
        Bun bun = new Bun("Белый", 2.5f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "ketchup", 1.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 2.0f));
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Белый"));
        assertTrue(receipt.contains("ketchup"));
        assertTrue(receipt.contains("cutlet"));
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void testGetReceiptWithBunOnly() {
        Bun bun = new Bun("Ржаной", 3.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Ржаной"));
        assertTrue(receipt.contains("Price:"));
    }
}