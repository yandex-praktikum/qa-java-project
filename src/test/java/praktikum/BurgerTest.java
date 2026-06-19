package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockSauce;   // вместо mockIngredient1
    @Mock
    private Ingredient mockFilling; // вместо mockIngredient2

    @Parameterized.Parameter(0)
    public float bunPrice;
    @Parameterized.Parameter(1)
    public String bunName;
    @Parameterized.Parameter(2)
    public IngredientType ingredientType1;
    @Parameterized.Parameter(3)
    public String ingredientName1;
    @Parameterized.Parameter(4)
    public float ingredientPrice1;
    @Parameterized.Parameter(5)
    public IngredientType ingredientType2;
    @Parameterized.Parameter(6)
    public String ingredientName2;
    @Parameterized.Parameter(7)
    public float ingredientPrice2;
    @Parameterized.Parameter(8)
    public float expectedPrice;
    @Parameterized.Parameter(9)
    public String expectedReceipt;

    @Parameterized.Parameters(name = "Test case {index}: price={8}")
    public static Collection<Object[]> data() {
        String lineSep = System.lineSeparator();

        return Arrays.asList(new Object[][]{
                {2.5f, "Классическая", null, null, 0f, null, null, 0f, 5.0f,
                        "(==== Классическая ====)" + lineSep +
                                "(==== Классическая ====)" + lineSep +
                                lineSep +
                                "Price: 5,000000" + lineSep},

                {3.0f, "Булочка для бургера", IngredientType.SAUCE, "Кетчуп", 1.5f, null, null, 0f, 7.5f,
                        "(==== Булочка для бургера ====)" + lineSep +
                                "= sauce Кетчуп =" + lineSep +
                                "(==== Булочка для бургера ====)" + lineSep +
                                lineSep +
                                "Price: 7,500000" + lineSep},

                {4.0f, "Черная булочка", IngredientType.SAUCE, "Майонез", 1.0f, IngredientType.FILLING, "Говядина", 2.5f, 11.5f,
                        "(==== Черная булочка ====)" + lineSep +
                                "= sauce Майонез =" + lineSep +
                                "= filling Говядина =" + lineSep +
                                "(==== Черная булочка ====)" + lineSep +
                                lineSep +
                                "Price: 11,500000" + lineSep}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void getPriceShouldCalculateCorrectTotalPrice() {
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockBun.getName()).thenReturn(bunName);
        burger.setBuns(mockBun);

        if (ingredientType1 != null) {
            when(mockSauce.getType()).thenReturn(ingredientType1);
            when(mockSauce.getName()).thenReturn(ingredientName1);
            when(mockSauce.getPrice()).thenReturn(ingredientPrice1);
            burger.addIngredient(mockSauce);
        }
        if (ingredientType2 != null) {
            when(mockFilling.getType()).thenReturn(ingredientType2);
            when(mockFilling.getName()).thenReturn(ingredientName2);
            when(mockFilling.getPrice()).thenReturn(ingredientPrice2);
            burger.addIngredient(mockFilling);
        }

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.0001f);
    }

    @Test
    public void getReceiptShouldReturnFormattedReceipt() {
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockBun.getName()).thenReturn(bunName);
        burger.setBuns(mockBun);

        if (ingredientType1 != null) {
            when(mockSauce.getType()).thenReturn(ingredientType1);
            when(mockSauce.getName()).thenReturn(ingredientName1);
            when(mockSauce.getPrice()).thenReturn(ingredientPrice1);
            burger.addIngredient(mockSauce);
        }
        if (ingredientType2 != null) {
            when(mockFilling.getType()).thenReturn(ingredientType2);
            when(mockFilling.getName()).thenReturn(ingredientName2);
            when(mockFilling.getPrice()).thenReturn(ingredientPrice2);
            burger.addIngredient(mockFilling);
        }

        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt);
    }
}