package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;

    private Burger burger;

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
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientFirstPositionTest() {
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientSecondPositionTest() {
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(250f, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptContainsBunNameTest() {
        prepareBurgerForReceipt();
        assertTrue(burger.getReceipt().toLowerCase().contains("black bun"));
    }

    @Test
    public void getReceiptContainsIngredientNameTest() {
        prepareBurgerForReceipt();
        assertTrue(burger.getReceipt().toLowerCase().contains("sour cream"));
    }

    @Test
    public void getReceiptContainsIngredientTypeTest() {
        prepareBurgerForReceipt();
        assertTrue(burger.getReceipt().toLowerCase().contains("sauce"));
    }

    @Test
    public void getReceiptContainsTotalPriceTest() {
        prepareBurgerForReceipt();
        assertTrue(burger.getReceipt().contains("220"));
    }

    private void prepareBurgerForReceipt() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getName()).thenReturn("sour cream");
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getPrice()).thenReturn(20f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }
}