package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient secondIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsTheBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsToList() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientRemovesFromList() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesOrder() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsSumOfBunAndIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(50f);
        when(secondIngredient.getPrice()).thenReturn(75f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        float expected = 100f * 2 + 50f + 75f;
        assertEquals(expected, burger.getPrice(), 0f);
    }

    @Test
    public void getPriceWithNoIngredientsReturnsBunPriceDoubled() {
        when(bun.getPrice()).thenReturn(150f);
        burger.setBuns(bun);

        assertEquals(300f, burger.getPrice(), 0f);
    }

    @Test
    public void getReceiptContainsBunName() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("black bun"));
    }

    @Test
    public void getReceiptContainsIngredientInfo() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("hot sauce"));
    }

    @Test
    public void getReceiptContainsPrice() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void getReceiptFormatStartsAndEndsWithBun() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");
        assertEquals("(==== white bun ====)", lines[0]);
        assertEquals("= filling cutlet =", lines[1]);
        assertEquals("(==== white bun ====)", lines[2]);
    }
}
