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

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    private Burger burger;

    private Ingredient cheese;
    private Ingredient beef;
    private Ingredient sauce;

    @Before
    public void setUp() {
        cheese = new Ingredient(IngredientType.FILLING, "Cheese", 40.0f);
        beef = new Ingredient(IngredientType.FILLING, "Beef Patty", 150.0f);
        sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 20.0f);

        burger = new Burger();


        Mockito.when(bun.getPrice()).thenReturn(60.0f);
        Mockito.when(bun.getName()).thenReturn("Classic Bun");
    }


    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }


    @Test
    public void addIngredientTest() {
        assertEquals(0, burger.ingredients.size());

        burger.addIngredient(cheese);

        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(cheese));
        assertEquals(cheese, burger.ingredients.get(0));
    }


    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(beef);

        float expectedPrice = (60.0f * 2) + 40.0f + 150.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }


    @Test
    public void removeIngredientTest() {
        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(beef);
        burger.addIngredient(sauce);

        assertEquals(3, burger.ingredients.size());

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(beef));
        assertTrue(burger.ingredients.contains(sauce));
        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientInvalidIndexTest() {
        burger.removeIngredient(0);
    }
    @Test
    public void moveIngredientTest() {
        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(beef);
        burger.addIngredient(sauce);
        burger.moveIngredient(1, 0);
        assertEquals(beef, burger.ingredients.get(0));
        assertEquals(cheese, burger.ingredients.get(1));
        assertEquals(sauce, burger.ingredients.get(2));
    }
    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Classic Bun ===="));
        assertTrue(receipt.contains("= filling Cheese ="));
        assertTrue(receipt.contains("= sauce Ketchup ="));
        assertTrue(receipt.contains("Price: "));
    }
}