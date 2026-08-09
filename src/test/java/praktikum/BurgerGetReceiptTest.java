package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetReceiptTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getReceiptShouldReturnCorrectReceiptWhenBunAndIngredientsExist() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredientFirst.getName()).thenReturn("hot sauce");
        when(mockIngredientFirst.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientFirst.getPrice()).thenReturn(50f);

        when(mockIngredientSecond.getName()).thenReturn("cutlet");
        when(mockIngredientSecond.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientSecond.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        String receipt = burger.getReceipt().replace("\r\n", "\n");

        String expectedReceipt = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling cutlet =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 325,000000\n";

        assertEquals("Чек должен соответствовать ожидаемому", expectedReceipt, receipt);
    }

    @Test
    public void getReceiptShouldReturnReceiptWithOnlyBunWhenNoIngredients() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200f);

        burger.setBuns(mockBun);
        String receipt = burger.getReceipt().replace("\r\n", "\n");

        String expectedReceipt = "(==== white bun ====)\n" +
                "(==== white bun ====)\n" +
                "\n" +
                "Price: 400,000000\n";

        assertEquals("Чек должен содержать только булочки", expectedReceipt, receipt);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceiptWithMultipleIngredients() {
        when(mockBun.getName()).thenReturn("red bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredientFirst.getName()).thenReturn("sauce1");
        when(mockIngredientFirst.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientFirst.getPrice()).thenReturn(10f);

        when(mockIngredientSecond.getName()).thenReturn("filling1");
        when(mockIngredientSecond.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientSecond.getPrice()).thenReturn(20f);

        when(mockIngredientThird.getName()).thenReturn("sauce2");
        when(mockIngredientThird.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientThird.getPrice()).thenReturn(30f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        String receipt = burger.getReceipt().replace("\r\n", "\n");

        String expectedReceipt = "(==== red bun ====)\n" +
                "= sauce sauce1 =\n" +
                "= filling filling1 =\n" +
                "= sauce sauce2 =\n" +
                "(==== red bun ====)\n" +
                "\n" +
                "Price: 260,000000\n";

        assertEquals("Чек должен соответствовать ожидаемому", expectedReceipt, receipt);
    }

    @Test
    public void getReceiptShouldContainCorrectPriceWhenBunPriceChanges() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(150f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt().replace("\r\n", "\n");

        assertTrue("В чеке цена - 300,000000", receipt.contains("Price: 300,000000"));
    }

    @Test(expected = NullPointerException.class)
    public void getReceiptShouldThrowNullPointerExceptionWhenBunNotSet() {
        Burger burger = new Burger();
        burger.getReceipt();
    }
}



