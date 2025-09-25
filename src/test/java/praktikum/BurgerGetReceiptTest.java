package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerGetReceiptTest {
    private Burger burger;
    private Bun bunMock;
    private Ingredient sauceMock;
    private Ingredient fillingMock;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Black Bun");
        when(bunMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);

        sauceMock = mock(Ingredient.class);
        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("chili");
        when(sauceMock.getPrice()).thenReturn(30f);

        fillingMock = mock(Ingredient.class);
        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("cutlet");
        when(fillingMock.getPrice()).thenReturn(70f);

        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
    }

    @Test
    public void getReceiptShouldReturnFullyFormattedReceipt() {
        String bunName = "Black Bun";
        float totalPrice = 2 * 100f + 30f + 70f;

        String expectedReceipt = new StringBuilder()
                .append(String.format("(==== %s ====)%n", bunName))
                .append(String.format("= %s %s =%n", "sauce", "chili"))
                .append(String.format("= %s %s =%n", "filling", "cutlet"))
                .append(String.format("(==== %s ====)%n", bunName))
                .append(String.format("%nPrice: %f%n", totalPrice))
                .toString();

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}
