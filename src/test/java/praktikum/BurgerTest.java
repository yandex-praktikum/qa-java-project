package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToList() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void removeIngredientRemovesIngredientFromList() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesOrder() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceCalculatesTotalPriceCorrectly() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient1.getPrice()).thenReturn(50.0f);
        Mockito.when(ingredient2.getPrice()).thenReturn(30.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = (100.0f * 2) + 50.0f + 30.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptReturnsCorrectReceiptFormat() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);

        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String expectedReceipt = String.format(
                "(==== black bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n", 250.0f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
