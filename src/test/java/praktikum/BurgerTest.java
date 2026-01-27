package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        ingredient = mock(Ingredient.class);

        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(50f);
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("white bun"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("250.0")); // 100 * 2 + 50
    }
}