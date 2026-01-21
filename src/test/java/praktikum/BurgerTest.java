package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerTest(String bunName, float bunPrice,
                      IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f, IngredientType.SAUCE, "hot sauce", 50.0f},
                {"white bun", 200.0f, IngredientType.FILLING, "cutlet", 150.0f},
                {"red bun", 300.0f, IngredientType.SAUCE, "chili sauce", 80.0f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        // Настраиваем моки с параметрами из теста
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getPrice()).thenReturn(ingredientPrice);
    }

    @Test
    public void getPriceReturnsCorrectValueWithSingleIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        float expectedPrice = (bunPrice * 2) + ingredientPrice;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptContainsBunName() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        assertTrue("Receipt should contain bun name: " + bunName, receipt.contains(bunName));
    }

    @Test
    public void getReceiptContainsIngredientName() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        assertTrue("Receipt should contain ingredient name: " + ingredientName, receipt.contains(ingredientName));
    }

    @Test
    public void getReceiptContainsIngredientType() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        String expectedType = ingredientType.toString().toLowerCase();
        assertTrue("Receipt should contain ingredient type: " + expectedType, receipt.contains(expectedType));
    }

    @Test
    public void getReceiptContainsPriceSection() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        assertTrue("Receipt should contain price section", receipt.contains("Price:"));
    }
}