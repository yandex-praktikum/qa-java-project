package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BurgerTest {
    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getName()).thenReturn("cutlet");
        Mockito.when(ingredient.getPrice()).thenReturn(200f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void burgerPriceIsCorrect() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Assert.assertEquals(400, burger.getPrice(), 0.01); // 100*2 + 200
    }

    @Test
    public void burgerReceiptContainsBunAndIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        Assert.assertTrue(receipt.contains("white bun"));
        Assert.assertTrue(receipt.contains("cutlet"));
    }

    @Test
    public void moveIngredientChangesOrder() {
        burger.setBuns(bun);
        Ingredient ing1 = new Ingredient(IngredientType.FILLING, "cheese", 50);
        Ingredient ing2 = new Ingredient(IngredientType.SAUCE, "sauce", 20);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(0, 1);
        Assert.assertEquals("sauce", burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredientDecreasesSize() {
        burger.setBuns(bun);
        Ingredient ing = new Ingredient(IngredientType.FILLING, "cheese", 50);
        burger.addIngredient(ing);

        burger.removeIngredient(0);
        Assert.assertTrue(burger.ingredients.isEmpty());
    }
}
