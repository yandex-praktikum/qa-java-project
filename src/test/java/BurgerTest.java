import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredient1;


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
    public void addIngredientTest() {
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient, burger.ingredients.get(1));
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        float price = 55;
        float finalPrice = price * 2 + price;

        Mockito.when(bun.getPrice()).thenReturn(price);
        Mockito.when(ingredient.getPrice()).thenReturn(price);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(finalPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {

        Bun bun = new Bun("Булочка", 100f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Соус Барбекю", 20f);
        Ingredient patty = new Ingredient(IngredientType.FILLING, "Котлета из говядины", 75f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(patty);

        String expected = String.format(
                "(==== Булочка ====)%n" +
                        "= sauce Соус Барбекю =%n" +
                        "= filling Котлета из говядины =%n" +
                        "(==== Булочка ====)%n" +
                        "%n" +
                        "Price: 295,000000%n"
        );

        assertEquals(expected, burger.getReceipt());

    }
}
