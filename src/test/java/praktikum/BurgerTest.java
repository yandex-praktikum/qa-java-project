package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient ingredientSecond;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSavesBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsToList() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesFromList() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientSecond);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientSecond, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesPosition() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientSecond, burger.ingredients.get(0));
        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsSumOfBunAndIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(50f);
        when(ingredientSecond.getPrice()).thenReturn(75f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientSecond);

        assertEquals(325f, burger.getPrice(), 0.001f);
    }

    @Test
    public void getPriceWithNoIngredientsReturnsBunPriceDoubled() {
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        assertEquals(200f, burger.getPrice(), 0.001f);
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
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("cutlet"));
    }

    @Test
    public void getReceiptContainsPrice() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
    }
}
