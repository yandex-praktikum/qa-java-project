package praktikum;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BurgerTest {

    @Test
    public void testSetBunsSetsBun() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100.0f);

        burger.setBuns(bun);

        assertNotNull(burger.bun);
    }

    @Test
    public void testSetBunsSetsCorrectBunName() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100.0f);

        burger.setBuns(bun);

        assertEquals("black bun", burger.bun.getName());
    }

    @Test
    public void testSetBunsSetsCorrectBunPrice() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100.0f);

        burger.setBuns(bun);

        assertEquals(100.0f, burger.bun.getPrice(), 0.01f);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 15.0f);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 15.0f);

        burger.addIngredient(ingredient);

        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 15.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 50.0f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 15.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 50.0f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void shouldMoveIngredientFromFirstToLastPosition() {
        Burger burger = new Burger();
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 15.0f);
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, "cutlet", 50.0f);
        Ingredient cream = new Ingredient(IngredientType.SAUCE, "sour cream", 20.0f);

        burger.addIngredient(sauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(cream);
        burger.moveIngredient(0, 2);

        List<Ingredient> expected = Arrays.asList(cutlet, cream, sauce);
        assertEquals(expected, burger.ingredients);
    }
}
