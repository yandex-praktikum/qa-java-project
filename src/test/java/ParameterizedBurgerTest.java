import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static constants.ExpectedReceipt.EXPECTED_RECEIPT_FOR_BLACK_BURGER;
import static constants.ExpectedReceipt.EXPECTED_RECEIPT_FOR_WHITE_BURGER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class ParameterizedBurgerTest {
    private Bun mockBun;
    private Ingredient mockIngredient;
    private String expectedReceipt;
    private Burger burger;

    public ParameterizedBurgerTest(Bun mockBun, Ingredient mockIngredient, String expectedReceipt) {
        this.mockBun = mockBun;
        this.mockIngredient = mockIngredient;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters (name = "mockBun - {0}, mockIngredient - {1}, expectedReceipt - {2}")
    public static Object[][] testData() {
        Bun blackBunMock = mock(Bun.class);
        when(blackBunMock.getName()).thenReturn("black bun");
        when(blackBunMock.getPrice()).thenReturn(10.0F);

        Ingredient sourCreamMock = mock(Ingredient.class);
        when(sourCreamMock.getType()).thenReturn(SAUCE);
        when(sourCreamMock.getName()).thenReturn("sour cream");
        when(sourCreamMock.getPrice()).thenReturn(20.0F);

        Bun whiteBunMock = mock(Bun.class);
        when(whiteBunMock.getName()).thenReturn("white bun");
        when(whiteBunMock.getPrice()).thenReturn(15.0F);

        Ingredient cutletMock = mock(Ingredient.class);
        when(cutletMock.getType()).thenReturn(FILLING);
        when(cutletMock.getName()).thenReturn("cutlet");
        when(cutletMock.getPrice()).thenReturn(25.0F);

        return new Object[][]{
                {blackBunMock, sourCreamMock, EXPECTED_RECEIPT_FOR_BLACK_BURGER},
                {whiteBunMock, cutletMock, EXPECTED_RECEIPT_FOR_WHITE_BURGER}
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testGetReceiptParametrized() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        System.out.println(burger.getReceipt());
        System.out.println(expectedReceipt);
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
