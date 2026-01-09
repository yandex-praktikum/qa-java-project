import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTests {
    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    private final String bunName;
    private final String firstIngredientType;
    private final String sauceName;
    private final String secondIngredientType;
    private final String fillingName;
    private final float price;

    public BurgerReceiptParameterizedTests(String bunName, String firstIngredientType, String sauceName, String secondIngredientType, String fillingName, float price) {
        this.bunName = bunName;
        this.firstIngredientType = firstIngredientType;
        this.sauceName = sauceName;
        this.secondIngredientType = secondIngredientType;
        this.fillingName = fillingName;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Булочка: {0}, первый ингредиент: {1}, название: {2}, второй ингредиент {3}, начинка: {4}, стоимость: {5}")
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {"black bun", "sauce", "hot sauce", "filling", "cutlet", 400f},
                {"white bun", "sauce", "sour cream", "filling", "dinosaur", 400f},
                {"red bun", "sauce", "chili sauce", "filling", "sausage", 400f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        sauce = mock(Ingredient.class);
        filling = mock(Ingredient.class);

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(100f);

        when(sauce.getName()).thenReturn(sauceName);
        when(sauce.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        when(filling.getName()).thenReturn(fillingName);
        when(filling.getPrice()).thenReturn(100f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void burgerReceiptParameterizedTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String actualReceipt = burger.getReceipt();
        String priceWithFormat = String.format("%.6f", price);
        // Как же долго я не мог понять почему падают тесты... А проблема была в "\r"
        String expectedReceipt = "(==== " + bunName + " ====)\r\n" +
                "= " + firstIngredientType + " " + sauceName + " =\r\n" +
                "= " + secondIngredientType + " " + fillingName + " =\r\n" +
                "(==== " + bunName + " ====)\r\n" +
                "\r\n" +
                "Price: " + priceWithFormat + "\r\n";
        assertEquals(expectedReceipt, actualReceipt);
    }
}