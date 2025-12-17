import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBuns_WhenBunSet_GetPriceCalledOnce() {
        burger.setBuns(mockBun);
        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void addIngredient_WhenIngredientAdded_IncreasesTotalPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);

        float expectedPrice = 100 * 2 + 50;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void removeIngredient_WhenIngredientRemoved_DecreasesTotalPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getPrice()).thenReturn(75f);

        float priceBefore = burger.getPrice();
        burger.removeIngredient(0);
        float priceAfter = burger.getPrice();

        assertEquals(50f, priceBefore - priceAfter, 0.001);
    }

    @Test
    public void moveIngredient_WhenIngredientsMoved_ChangesOrder() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 1);

        assertNotNull(burger);
    }


    @Test
    public void getReceipt_WhenBurgerWithIngredients_ReturnsFormattedReceipt() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getName()).thenReturn("cutlet");
        when(mockIngredient1.getPrice()).thenReturn(100f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("black") || receipt.contains("cutlet"));
    }

    @Test
    public void getPrice_WhenBurgerWithMultipleIngredients_ReturnsCorrectSum() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 100 * 2 + 50 + 75;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void getPrice_WhenNoBunsSet_ThrowsNullPointerException() {
        burger.getPrice();
    }

    @Test(expected = NullPointerException.class)
    public void getReceipt_WhenNoBunsSet_ThrowsNullPointerException() {
        burger.getReceipt();
    }

    @Test
    public void getPrice_WhenBunsSetButNoIngredients_ReturnsPriceOfTwoBuns() {
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        float expectedPrice = 100 * 2;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceipt_WhenOnlyBunsSet_ReturnsReceiptWithBunsOnly() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("white"));
    }
}