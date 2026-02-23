package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockIngredient1;
    private Ingredient mockIngredient2;
    private Ingredient mockIngredient3;

    @Before
    public void setup() {
        mockBun = new Bun("test bun", 50.0f);
        mockIngredient1 = new Ingredient(IngredientType.SAUCE, "ketchup", 10.0f);
        mockIngredient2 = new Ingredient(IngredientType.FILLING, "cheese", 20.0f);
        mockIngredient3 = new Ingredient(IngredientType.FILLING, "lettuce", 15.0f);

        burger = new Burger();
        burger.setBuns(mockBun);
    }

    @Test
    public void shouldSetBunAndCalculateTotalPrice() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        float expectedPrice = mockBun.getPrice() * 2 + mockIngredient1.getPrice() + mockIngredient2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void shouldRemoveIngredientByValidIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldNotRemoveIngredientWhenIndexIsOutOfBounds() {
        burger.removeIngredient(-1);
    }

    @Test
    public void shouldMoveIngredientToNewPosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        Ingredient originalFirst = burger.ingredients.get(0);
        burger.moveIngredient(0, 2);
        assertSame(originalFirst, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowErrorOnInvalidIndexes() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.moveIngredient(-1, 0);
    }

    @Test
    public void shouldPrintCorrectReceiptWithAllIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(mockBun.getName()));
        assertTrue(receipt.contains(mockIngredient1.getName()));
        assertTrue(receipt.contains(mockIngredient2.getName()));
        assertTrue(receipt.contains(mockIngredient3.getName()));
    }

    @Test
    public void shouldHaveZeroPriceWithoutIngredients() {
        assertEquals(mockBun.getPrice() * 2, burger.getPrice(), 0.001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowErrorWhenMovingNonexistentIngredient() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void shouldHandleZeroPricedIngredients() {
        Ingredient freeSauce = new Ingredient(IngredientType.SAUCE, "free sauce", 0.0f);
        burger.addIngredient(freeSauce);
        assertEquals(mockBun.getPrice() * 2, burger.getPrice(), 0.001);
    }

    @Test
    public void shouldPreserveOrderAfterRemoval() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.removeIngredient(1);
        assertSame(mockIngredient1, burger.ingredients.get(0));
        assertSame(mockIngredient3, burger.ingredients.get(1));
    }

    @Test
    public void shouldAllowAddingDuplicateIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient1);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void shouldGenerateCorrectReceiptWithoutIngredients() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(mockBun.getName()));
    }

    @Test
    public void testMultipleBunsInBurger() {
        Burger burger = new Burger();
        Bun bun1 = new Bun("Wheat Bun", 100.0f);
        Bun bun2 = new Bun("Sesame Bun", 150.0f);
        burger.setBuns(bun1);
        burger.setBuns(bun2);
        assertEquals(bun2, burger.bun);
    }

    @Test
    public void testRemoveAllIngredients() {
        Burger burger = new Burger();
        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "Mayonnaise", 10.0f);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "Chicken Patty", 20.0f);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.removeIngredient(0);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientOutOfRange() {
        Burger burger = new Burger();
        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "Ketchup", 10.0f);
        burger.addIngredient(ing1);
        burger.moveIngredient(0, 10);
    }

    @Test
    public void testBunWithNullName() {
        Burger burger = new Burger();
        Bun bun = new Bun(null, 100.0f);
        burger.setBuns(bun);
        assertNull(burger.bun.getName());
    }
}