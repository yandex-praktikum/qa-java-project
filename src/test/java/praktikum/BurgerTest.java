package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient sauceIngredient;
    private Ingredient fillingIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
        mockBun = mock(Bun.class);
        sauceIngredient = mock(Ingredient.class);
        fillingIngredient = mock(Ingredient.class);
    }

    @Test
    public void testSetBunsAssignsBun() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(sauceIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(sauceIngredient);
        assertEquals(sauceIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsSize() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testAddMultipleIngredientsFirstIngredientCorrect() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        assertEquals(sauceIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsSecondIngredientCorrect() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        assertEquals(fillingIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredientReducesSize() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectOne() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.removeIngredient(0);
        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientSwapsPositions() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.moveIngredient(0, 1);
        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientToBeginningPutsIngredientAtFirstPosition() {
        Ingredient thirdIngredient = mock(Ingredient.class);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(2, 0);
        assertEquals(thirdIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientToBeginningPutsFirstIngredientAtSecondPosition() {
        Ingredient thirdIngredient = mock(Ingredient.class);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(2, 0);
        assertEquals(sauceIngredient, burger.ingredients.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testGetPriceThrowsWhenNoBun() {
        when(sauceIngredient.getPrice()).thenReturn(50f);
        burger.addIngredient(sauceIngredient);
        burger.getPrice();
    }

    @Test
    public void testGetPriceWithBunOnlyReturnsDoubleBunPrice() {
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);
        float price = burger.getPrice();
        assertEquals(200f, price, 0.01f);
    }

    @Test
    public void testGetPriceWithBunOnlyInvokesGetPriceOnBun() {
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);
        burger.getPrice();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithIngredientsAddsCorrectly() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        float price = burger.getPrice();
        assertEquals(325f, price, 0.01f);
    }

    @Test
    public void testGetPriceWithIngredientsInvokesGetPriceOnBun() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.getPrice();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithIngredientsInvokesGetPriceOnSauceIngredient() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.getPrice();
        verify(sauceIngredient, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithIngredientsInvokesGetPriceOnFillingIngredient() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.getPrice();
        verify(fillingIngredient, times(1)).getPrice();
    }

    @Test
    public void testGetReceiptIncludesFirstBunLine() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void testGetReceiptIncludesSauceLine() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= sauce hot sauce ="));
    }

    @Test
    public void testGetReceiptIncludesFillingLine() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(fillingIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= filling cutlet ="));
    }

    @Test
    public void testGetReceiptIncludesPriceLine() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 325"));
    }

    @Test
    public void testGetReceiptInvokesGetNameOnBun() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.getReceipt();
        verify(mockBun, times(2)).getName();
    }

    @Test
    public void testGetReceiptInvokesGetNameOnSauceIngredient() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.getReceipt();
        verify(sauceIngredient, times(1)).getName();
    }

    @Test
    public void testGetReceiptInvokesGetNameOnFillingIngredient() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getPrice()).thenReturn(75f);
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.getReceipt();
        verify(fillingIngredient, times(1)).getName();
    }

    @Test
    public void testGetReceiptFirstLineFormat() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);
        String[] lines = burger.getReceipt().split(System.lineSeparator());
        assertEquals("(==== white bun ====)", lines[0]);
    }

    @Test
    public void testGetReceiptSecondLineFormat() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);
        String[] lines = burger.getReceipt().split(System.lineSeparator());
        assertEquals("(==== white bun ====)", lines[1]);
    }

    @Test
    public void testGetReceiptThirdLineFormat() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);
        String[] lines = burger.getReceipt().split(System.lineSeparator());
        assertTrue(lines[2].isEmpty());
    }

    @Test
    public void testGetReceiptFourthLineFormat() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(50f);
        burger.setBuns(mockBun);
        String[] lines = burger.getReceipt().split(System.lineSeparator());
        assertTrue(lines[3].startsWith("Price:"));
    }
}



