package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Bun bun;
    private Ingredient ingredient;
    private Burger burger;

    @BeforeEach
    public void setUp() {
        bun = Mockito.mock(Bun.class);
        ingredient = Mockito.mock(Ingredient.class);
        burger = new Burger();
    }

    @Test
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToList() {
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientRemovesIngredientFromList() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void getPriceReturnsTwoBunsPricesPlusIngredientsPrice() {
        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient.getPrice()).thenReturn(50.0f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals(250.0f, burger.getPrice());
    }

    @Test
    public void getReceiptContainsBunNameAndIngredientInfo() {
        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient.getName()).thenReturn("Cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getPrice()).thenReturn(50.0f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Black Bun"));
        assertTrue(receipt.contains("Cutlet"));
        assertTrue(receipt.contains("filling"));
    }
}
