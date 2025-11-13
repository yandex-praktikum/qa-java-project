import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.*;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient sauce;

    @Mock
    Ingredient filling;

    Burger burger;
    private final String bunName;
    private final float bunPrice;
    private final String sauceName;
    private final float saucePrice;
    private final String fillingName;
    private final float fillingPrice;
    private final float priceSum;

    public ParameterizedTest(String bunName, float bunPrice, String sauceName, float saucePrice, String fillingName, float fillingPrice, float priceSum) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.sauceName = sauceName;
        this.saucePrice = saucePrice;
        this.fillingName = fillingName;
        this.fillingPrice = fillingPrice;
        this.priceSum = priceSum;
    }

    @Before
    public void setUp() {
        burger = new Burger();
        MockitoAnnotations.initMocks(this);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(sauce.getName()).thenReturn(sauceName);
        Mockito.when(sauce.getType()).thenReturn(SAUCE);
        Mockito.when(sauce.getPrice()).thenReturn(saucePrice);
        Mockito.when(filling.getName()).thenReturn(fillingName);
        Mockito.when(filling.getType()).thenReturn(FILLING);
        Mockito.when(filling.getPrice()).thenReturn(fillingPrice);
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Collection<Object[]> getPrice() {
        return Arrays.asList(new Object[][]{
                {"Black Bun", 100f, "Hot Sauce", 100f, "Cutlet", 100f, 400f},
                {"White Bun", 200f, "Sour Cream", 200f, "Dinosaur", 200f, 800f}
        });
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        float expectedPrice = burger.getPrice();
        assertEquals(expectedPrice, priceSum, 1);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        String actualReceipt = burger.getReceipt();
        String expectedReceipt = "(==== " + bun.getName() + " ====)\r\n" +
                "= " + sauce.getType().toString().toLowerCase() + " " + sauce.getName() + " =\r\n" +
                "= " + filling.getType().toString().toLowerCase() + " " + filling.getName() + " =\r\n" +
                "(==== " + bun.getName() + " ====)\r\n" + "\r\n" + "Price: " + String.format("%f%n", burger.getPrice());
        assertEquals(expectedReceipt, actualReceipt);
    }
}
