package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private static final float BUN_PRICE = 100f;
    private static final float SAUCE_PRICE = 50f;
    private static final float FILLING_PRICE = 200f;
    private static final float TOTAL_PRICE = 450f;

    private Burger burger;
    private Bun bun;
    private Ingredient sauceIngredient;
    private Ingredient fillingIngredient;

    @Before
    public void setUp() {

        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(BUN_PRICE);

        sauceIngredient = mock(Ingredient.class);
        when(sauceIngredient.getPrice()).thenReturn(SAUCE_PRICE);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);

        fillingIngredient = mock(Ingredient.class);
        when(fillingIngredient.getPrice()).thenReturn(FILLING_PRICE);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
    }


    @Test
    public void setBunsSetsBunCorrectly() {

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }


    @Test
    public void addIngredientAddsIngredientToList() {

        burger.addIngredient(sauceIngredient);

        assertEquals(sauceIngredient, burger.ingredients.get(0));
    }


    @Test
    public void removeIngredientRemovesIngredientFromList() {

        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.removeIngredient(0);

        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }


    @Test
    public void moveIngredientMovesIngredientCorrectly() {

        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }


    @Test
    public void getPriceReturnsCorrectPrice() {

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        assertEquals(TOTAL_PRICE, burger.getPrice(), 0.001f);
    }


    @Test
    public void getReceiptContainsBunName() {

        burger.setBuns(bun);

        assertTrue(
                burger.getReceipt().contains("black bun")
        );
    }


    @Test
    public void getReceiptContainsIngredientsNames() {

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("hot sauce"));
    }


    @Test
    public void getReceiptContainsPrice() {

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        assertTrue(
                burger.getReceipt().contains("Price: 450")
        );
    }
}