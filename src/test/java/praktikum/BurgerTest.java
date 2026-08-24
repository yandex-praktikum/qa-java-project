package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @Test
    public void setBunsSetsBun() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredient() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesIngredientToNewPosition() {
        Burger burger = new Burger();
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(0, 2);

        assertSame(secondIngredient, burger.ingredients.get(0));
        assertSame(thirdIngredient, burger.ingredients.get(1));
        assertSame(firstIngredient, burger.ingredients.get(2));
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(100.0f);
        when(firstIngredient.getPrice()).thenReturn(50.0f);
        when(secondIngredient.getPrice()).thenReturn(75.0f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actualPrice = burger.getPrice();

        assertEquals(325.0f, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format(
                "(==== black bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "(==== black bun ====)%n" +
                        "%n" +
                        "Price: %f%n",
                250.0f
        );

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}