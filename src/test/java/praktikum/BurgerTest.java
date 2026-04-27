package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Test
    public void setBunsSetsBunForBurger() {
        Burger burger = new Burger();

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToBurger() {
        Burger burger = new Burger();

        burger.addIngredient(firstIngredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredientByIndex() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesIngredientToNewIndex() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertSame(secondIngredient, burger.ingredients.get(0));
        assertSame(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsDoubleBunPricePlusIngredientsPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        when(bun.getPrice()).thenReturn(100.0f);
        when(firstIngredient.getPrice()).thenReturn(50.0f);
        when(secondIngredient.getPrice()).thenReturn(75.5f);

        float actualPrice = burger.getPrice();

        assertEquals(325.5f, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptReturnsBurgerReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("hot sauce");
        when(firstIngredient.getPrice()).thenReturn(50.0f);

        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getName()).thenReturn("cutlet");
        when(secondIngredient.getPrice()).thenReturn(90.0f);

        String expectedReceipt = String.format("(==== black bun ====)%n")
                + String.format("= sauce hot sauce =%n")
                + String.format("= filling cutlet =%n")
                + String.format("(==== black bun ====)%n")
                + String.format("%nPrice: %f%n", 340.0f);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}