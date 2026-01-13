package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BurgerTest {

    private Burger burger;
    private Bun bun;

    @BeforeEach
    void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("White bun");
        when(bun.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
    }

    @Test
    void addIngredientIncreasesIngredientsSize() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void addIngredientAddsCorrectIngredient() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);

        assertSame(ingredient, burger.ingredients.get(0));
    }


    @Test
    void removeIngredientRemovesIngredientByIndex() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    void moveIngredientMovesSecondIngredientToFirstPosition() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(second, burger.ingredients.get(0));
    }

    @Test
    void moveIngredientMovesFirstIngredientToSecondPosition() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(first, burger.ingredients.get(1));
    }


    @Test
    void getPriceReturnsCorrectTotalPrice() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        when(ingredient1.getPrice()).thenReturn(30.0f);
        when(ingredient2.getPrice()).thenReturn(20.0f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float price = burger.getPrice();

        assertEquals(150.0f, price);
    }


    @Test
    void setBunsSetsBunCorrectly() {
        Burger burger = new Burger();
        Bun bun = new Bun("Test bun", 40f);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    void getPriceWithoutIngredientsReturnsOnlyBunPrice() {
        Burger burger = new Burger();
        Bun bun = new Bun("Plain bun", 30f);

        burger.setBuns(bun);

        assertEquals(60f, burger.getPrice());
    }

    @Test
    void getReceiptReturnsCorrectReceipt() {
        Ingredient ingredient = mock(Ingredient.class);

        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("Ketchup");
        when(ingredient.getPrice()).thenReturn(10.0f);

        burger.addIngredient(ingredient);

        String expectedReceipt =
                "(==== White bun ====)\n" +
                        "= sauce Ketchup =\n" +
                        "(==== White bun ====)\n" +
                        "\n" +
                        "Price: 110.000000\n";

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    void removeIngredientFromMiddleDecreasesIngredientsSize() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("Bun", 10f));

        Ingredient first = new Ingredient(IngredientType.SAUCE, "Sauce", 5f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "Meat", 15f);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void removeIngredientFromMiddleKeepsCorrectIngredient() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("Bun", 10f));

        Ingredient first = new Ingredient(IngredientType.SAUCE, "Sauce", 5f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "Meat", 15f);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.removeIngredient(0);

        assertEquals(second, burger.ingredients.get(0));
    }


}