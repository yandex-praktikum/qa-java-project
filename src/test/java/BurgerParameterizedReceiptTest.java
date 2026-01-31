import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(Parameterized.class)
public class BurgerParameterizedReceiptTest {

    Burger burger = new Burger();
    Bun bun = mock(Bun.class);
    Ingredient sauce = mock(Ingredient.class);
    Ingredient filling = mock(Ingredient.class);

    private final String nameBun;
    private final String ingTypeFirst;
    private final String nameSauce;
    private final String ingTypeSecond;
    private final String nameFilling;
    private final float price;

    public BurgerParameterizedReceiptTest(String nameBun, String ingTypeFirst, String nameSauce, String ingTypeSecond, String nameFilling, float price) {
        this.nameBun = nameBun;
        this.ingTypeFirst = ingTypeFirst;
        this.nameSauce = nameSauce;
        this.ingTypeSecond = ingTypeSecond;
        this.nameFilling = nameFilling;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> allTest() {
        return Arrays.asList(new Object[][]{
                {"white bun", "sauce", "chili sauce", "filling", "cutlet", 200f},
                {"black bun", "sauce", "sour cream", "filling", "dinosaur", 200f},
                {"red bun", "sauce", "hot sauce", "filling", "sausage", 200f}
        });
    }

    @Before
    public void beforeAll() {

        Mockito.when(bun.getName()).thenReturn(nameBun);
        Mockito.when(bun.getPrice()).thenReturn(50f);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn(nameSauce);
        Mockito.when(sauce.getPrice()).thenReturn(50f);

        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getName()).thenReturn(nameFilling);
        Mockito.when(filling.getPrice()).thenReturn(50f);
    }

    @Test
    public void burgerParameterizedReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = "(==== " + nameBun + " ====)\r\n" +
                "= " + ingTypeFirst + " " + nameSauce + " =\r\n" +
                "= " + ingTypeSecond + " " + nameFilling + " =\r\n" +
                "(==== " + nameBun + " ====)\r\n" +
                "\r\n" + "Price: " + String.format("%.6f", price) + "\r\n";
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}