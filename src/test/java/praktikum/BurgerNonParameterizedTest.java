package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerNonParameterizedTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceIngredientMock;

    @Mock
    private Ingredient fillingIngredientMock;

    @Mock
    private Ingredient chiliIngredientMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsSetsBun() {
        burger.setBuns(bunMock);
        assertNotNull(burger.bun);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientIncreasesListSize() {
        burger.addIngredient(sauceIngredientMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectIngredient() {
        burger.addIngredient(sauceIngredientMock);
        assertEquals(sauceIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientDecreasesListSize() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesCorrectElement() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.removeIngredient(0);
        assertEquals(fillingIngredientMock, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexThrowsException() {
        burger.addIngredient(sauceIngredientMock);
        burger.removeIngredient(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientFromEmptyListThrowsException() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientChangesFirstPosition() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(chiliIngredientMock);
        burger.moveIngredient(0, 2);
        assertEquals(fillingIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesSecondPosition() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(chiliIngredientMock);
        burger.moveIngredient(0, 2);
        assertEquals(chiliIngredientMock, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientChangesThirdPosition() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(chiliIngredientMock);
        burger.moveIngredient(0, 2);
        assertEquals(sauceIngredientMock, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientPreservesListSize() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.moveIngredient(0, 1);
        assertEquals(2, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidSourceIndexThrowsException() {
        burger.addIngredient(sauceIngredientMock);
        burger.moveIngredient(5, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidTargetIndexThrowsException() {
        burger.addIngredient(sauceIngredientMock);
        burger.moveIngredient(0, 5);
    }

    @Test
    public void getPriceWithBunAndIngredientReturnsCorrectValue() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(sauceIngredientMock.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredientMock);

        float expectedPrice = (100.0f * 2) + 50.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void getPriceWithoutBunThrowsException() {
        burger.addIngredient(sauceIngredientMock);
        burger.getPrice();
    }

    @Test
    public void getReceiptContainsBunName() {
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(sauceIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredientMock.getName()).thenReturn("Test Sauce");
        when(sauceIngredientMock.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredientMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Test Bun"));
    }

    @Test(expected = NullPointerException.class)
    public void getReceiptWithoutBunThrowsException() {
        burger.addIngredient(sauceIngredientMock);
        burger.getReceipt();
    }

    @Test
    public void getReceiptWithEmptyIngredientsContainsBunNameTwice() {
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();

        String bunLine = "(==== Test Bun ====)";
        int firstIndex = receipt.indexOf(bunLine);
        int lastIndex = receipt.lastIndexOf(bunLine);

        assertTrue(firstIndex != -1 && lastIndex != -1 && firstIndex != lastIndex);
    }
}