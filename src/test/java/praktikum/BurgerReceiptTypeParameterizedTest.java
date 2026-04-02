package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTypeParameterizedTest {

    @Parameterized.Parameter(0)
    public IngredientType type;

    @Parameterized.Parameter(1)
    public String expectedLowercase;

    private Bun bun;
    private Ingredient ingredient;
    private Burger burger;

    @Parameterized.Parameters(name = "type={0} => \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"},
        });
    }

    @Before
    public void setUp() {
        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(100f);

        ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn("test ingredient");
        when(ingredient.getPrice()).thenReturn(50f);

        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @Test
    public void getReceiptIncludesIngredientLineWithLowercasedType() {
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(String.format("= %s %s =%n", expectedLowercase, "test ingredient")));
    }

    @Test
    public void getReceiptCallsBunGetNameTwice() {
        burger.getReceipt();

        verify(bun, times(2)).getName();
    }

    @Test
    public void getReceiptCallsBunGetPriceOnce() {
        burger.getReceipt();

        verify(bun, times(1)).getPrice();
    }

    @Test
    public void getReceiptCallsIngredientGetTypeOnce() {
        burger.getReceipt();

        verify(ingredient, times(1)).getType();
    }

    @Test
    public void getReceiptCallsIngredientGetNameOnce() {
        burger.getReceipt();

        verify(ingredient, times(1)).getName();
    }

    @Test
    public void getReceiptCallsIngredientGetPriceOnce() {
        burger.getReceipt();

        verify(ingredient, times(1)).getPrice();
    }
}
