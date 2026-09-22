import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BurgerTest {
    @Test
        public void setBunsSetsBun() {

            Burger burger = new Burger();
            Bun bun = new Bun("White bun", 100);

            burger.setBuns(bun);

            assertEquals(bun, burger.bun);
    }

    @Test
        public void addIngredientAddsIngredient(){

            Burger burger = new Burger();
            Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Chili", 50);

            burger.addIngredient(ingredient);

            assertEquals(1, burger.ingredients.size());
            assertEquals(ingredient, burger.ingredients.get(0));

    }

    @Test
        public void removeIngredientRemovesByIndex(){

            Burger burger = new Burger();
            Ingredient ingredient0 = new Ingredient(IngredientType.SAUCE, "Chili", 50);
            Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "Ketchup", 30);

            burger.addIngredient(ingredient0);
            burger.addIngredient(ingredient1);
            burger.removeIngredient(0);

            assertEquals(1, burger.ingredients.size());
            assertEquals(ingredient1, burger.ingredients.get(0));

    }

    @Test
        public void moveIngredientMovesFromOneIndexToAnother(){

            Burger burger = new Burger();
            Ingredient ingredient0 = new Ingredient(IngredientType.SAUCE, "Chili", 50);
            Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "Ketchup", 30);
            Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Cheese", 100);

            burger.addIngredient(ingredient0);
            burger.addIngredient(ingredient1);
            burger.addIngredient(ingredient2);

            burger.moveIngredient(0, 1);

            assertEquals(ingredient0, burger.ingredients.get(1));
            assertEquals(ingredient1, burger.ingredients.get(0));
            assertEquals(ingredient2, burger.ingredients.get(2));
            assertEquals(3, burger.ingredients.size());

    }
    @Test
    public void getReceiptOnlyBun() {
        Burger burger = new Burger();
        Bun bun = new Bun("bun", 100);

        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== bun ====)"));
        assertTrue(receipt.contains("Price: 200"));
    }
    @Test
    public void getReceiptWithIngredients() {
        Burger burger = new Burger();
        Bun bun = new Bun("bun", 100);
        burger.setBuns(bun);

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "chili", 50);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce chili ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("Price: 350"));
    }

}
