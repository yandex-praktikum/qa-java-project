import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.*;

public class BurgerTest {

    private Bun bun;

    private Burger burger;

    private Ingredient ingredient;

    private Ingredient ingredientTwo;

    @BeforeEach
    void setUp() {
        burger = new Burger();

        bun = new Bun("Булочка", 100.2f);

        ingredient = new Ingredient(FILLING, "Бекон", 200.5f);

        ingredientTwo = new Ingredient(SAUCE, "Кетчуп", 50.2f);

        burger.ingredients = new ArrayList<>();
    }

    @Test
    void setBunShouldSetBun() {
        burger.setBuns(bun);
        assertEquals("Булочка", burger.bun.getName());
    }

    @Test
    void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    void removeIngredientShouldRemoveIngredientFromList() {
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    void moveIngredientShouldMoveIngredientInList() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);

        burger.moveIngredient(0, 1);

        assertAll(
                () -> assertEquals(ingredient, burger.ingredients.get(1)),
                () -> assertEquals(ingredientTwo, burger.ingredients.get(0))
        );
    }

    @Test
    void getPriceShouldReturnPrice() {
        burger.ingredients.add(ingredient);
        burger.ingredients.add(ingredientTwo);
        burger.setBuns(bun);

        float bunPrice = burger.bun.getPrice();
        float ingredientsPrice = burger.ingredients.get(0).getPrice() + burger.ingredients.get(1).getPrice();

        float expectedPrice = (bunPrice * 2) + ingredientsPrice;

        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    void getReceiptShouldReturnReceipt() {
        burger.ingredients.add(ingredient);
        burger.setBuns(bun);

        String bunName = burger.bun.getName();
        String ingredientType = burger.ingredients.get(0).getType().toString().toLowerCase();
        String ingredientName = burger.ingredients.get(0).getName();
        float burgerPrice = burger.getPrice();

        String expectedReceipt = String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n", bunName, ingredientType, ingredientName, bunName, burgerPrice);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
