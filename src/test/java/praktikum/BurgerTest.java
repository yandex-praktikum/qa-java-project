package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    @Before
    public void initBurger() {
        burger = new Burger();
        burger.setBuns(bun); // Устанавливаем булку перед каждым тестом
    }

    @Test
    public void bunShouldAffectPriceCorrectly() {
        when(bun.getPrice()).thenReturn(100.00f);
        assertEquals(200.00f, burger.getPrice(), 0.0);
    }

    @Test
    public void shouldAddIngredientsToBurger() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Assert.assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void shouldRemoveIngredientByIndex() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void shouldSwapIngredientsInList() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);

        Assert.assertEquals(ingredient1, burger.ingredients.get(1));
        Assert.assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void totalPriceShouldBeCalculatedCorrectly() {
        when(bun.getPrice()).thenReturn(100.00f);
        when(ingredient1.getPrice()).thenReturn(50.00f);
        when(ingredient2.getPrice()).thenReturn(30.00f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.0);
    }

    @Test
    public void receiptShouldIncludeAllParts() {
        when(bun.getName()).thenReturn("Bun_name");
        Mockito.when(bun.getPrice()).thenReturn(100.00f);

        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient1.getName()).thenReturn("Ingredient1_name");
        Mockito.when(ingredient1.getPrice()).thenReturn(50.00f);

        burger.addIngredient(ingredient1);
        burger.getPrice();

        String actualReceipt = burger.getReceipt();

        Assert.assertTrue(actualReceipt.contains("(==== Bun_name ====)"));
        Assert.assertTrue(actualReceipt.contains("= filling Ingredient1_name ="));
        Assert.assertTrue(actualReceipt.contains("Price: 250,000000"));
    }
}
