import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BurgerTests {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Test
    public void shouldSetBun() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void shouldSetIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void shouldRemoveIngredientByIndex(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void shouldMoveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient, burger.ingredients.get(0));
        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void shouldCalculatePriceWithMock() {
        Burger burger = new Burger();

        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(400F);

        burger.setBuns(bun);

        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getPrice()).thenReturn(50F);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        float price = burger.getPrice();

        assertEquals(400 * 2 + 50 + 50, price);
        Mockito.verify(bun, Mockito.times(1)).getPrice();
    }

    @Test
    public void shouldReturnCorrectReceipt() {
        Burger burger = new Burger();

        Bun bun = new Bun("Булка", 100);
        burger.setBuns(bun);

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Соус", 50);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Булка"));
        assertTrue(receipt.contains("Price"));
    }

}
