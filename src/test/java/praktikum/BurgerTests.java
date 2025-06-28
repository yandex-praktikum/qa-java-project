package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient ingredientAdditional;

    @Before
    public void setUp() {
        burger = new Burger();
    }
    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
    @Test
    public void addIngredientGrowSizeTest() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void addIngredientIncludeIngredientTest() {
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
    }
    @Test
    public void removeIngredientReduceSizeTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientAdditional);
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void removeIngredientListTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientAdditional);
        burger.removeIngredient(1);
        assertFalse(burger.ingredients.contains(ingredientAdditional));
    }
    @Test
    public void moveIngredientListTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientAdditional);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient, burger.ingredients.get(1));
    }
    @Test
    public void moveIngredientListSizeTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientAdditional);
        burger.moveIngredient(0, 1);
        assertEquals(2, burger.ingredients.size());
    }
    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(10.0f);
        when(ingredient.getPrice()).thenReturn(5.0f);
        when(ingredientAdditional.getPrice()).thenReturn(15.0f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientAdditional);
        assertEquals(40.0f, burger.getPrice(), 0);
    }
    @Test
    public void getReceiptTest() {
        when(bun.getName()).thenReturn("randomBun");
        when(bun.getPrice()).thenReturn(10.0f);
        when(ingredient.getName()).thenReturn("randomIngredient");
        when(ingredient.getPrice()).thenReturn(5.0f);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        String expectedReceipt = "(==== randomBun ====)\n" + "= filling randomIngredient =\n" + "(==== randomBun ====)\n" + "\nPrice: 25,000000\n";
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals(expectedReceipt, burger.getReceipt().replace("\r\n", "\n"));
    }
}
