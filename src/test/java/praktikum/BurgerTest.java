package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientOne;

    @Mock
    private Ingredient ingredientTwo;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(bun.getName()).thenReturn("Булка1");
        when(bun.getPrice()).thenReturn(11.0f);

        when(ingredientOne.getName()).thenReturn("Салат");
        when(ingredientOne.getPrice()).thenReturn(35.0f);
        when(ingredientOne.getType()).thenReturn(IngredientType.FILLING);

        when(ingredientTwo.getName()).thenReturn("Сырный");
        when(ingredientTwo.getPrice()).thenReturn(23.0f);
        when(ingredientTwo.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredient() {
        burger.addIngredient(ingredientOne);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientOne, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredient() {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientTwo, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredient() {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        burger.moveIngredient(1, 0);
        assertEquals(ingredientTwo, burger.ingredients.get(0));
        assertEquals(ingredientOne, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptShouldReturnNotNull() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Булка1 ====)"));
        assertTrue(receipt.contains("= filling Салат ="));
        assertTrue(receipt.contains("Price: " + String.format("%.2f", 35.0f + 11.0f * 2)));
    }
}