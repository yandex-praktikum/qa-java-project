package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(100f);

        ingredient1 = mock(Ingredient.class);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getPrice()).thenReturn(50f);

        ingredient2 = mock(Ingredient.class);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("cutlet");
        when(ingredient2.getPrice()).thenReturn(200f);
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);

        // В проекте Stellar Burgers bun обычно public поле в Burger
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredient() {
        burger.addIngredient(ingredient1);

        // В проекте Stellar Burgers ingredients обычно public поле (List) в Burger
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldChangeIngredientsOrder() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(2, ingredients.size());
        assertEquals(ingredient2, ingredients.get(0));
        assertEquals(ingredient1, ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        float actualPrice = burger.getPrice();

        // 100*2 + 50 = 250
        assertEquals(250f, actualPrice, 0.01f);
    }

    @Test
    public void getReceiptShouldContainBunIngredientAndTotalPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("white bun"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("250.000000"));
    }
}