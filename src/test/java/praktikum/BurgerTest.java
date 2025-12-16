package praktikum;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    // Параметры для тестирования
    private final String bunName;
    private final float bunPrice;
    private final int ingredientCount;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerTest(String bunName, float bunPrice, int ingredientCount, float ingredientPrice, float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientCount = ingredientCount;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"black bun", 100f, 0, 0f, 200f},
                {"white bun", 150f, 1, 50f, 350f},
                {"red bun", 200f, 2, 50f, 500f},
                {"cheap bun", 0f, 3, 0f, 0f},
                {"expensive bun", 500f, 5, 100f, 1500f},
        };
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(mockBun);

        // Создаем разные mock объекты для каждого ингредиента
        for (int i = 0; i < ingredientCount; i++) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(ingredientPrice);
            when(ingredientMock.getName()).thenReturn("ingredient " + i);
            when(ingredientMock.getType()).thenReturn(i % 2 == 0 ? IngredientType.SAUCE : IngredientType.FILLING);
            burger.addIngredient(ingredientMock);
        }
    }

    @Test
    public void testGetPrice() {
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

    @Test
    public void testGetReceiptIngredientLinesCount() {
        String receipt = burger.getReceipt();
        String normalizedReceipt = receipt.replace("\r\n", "\n").replace("\r", "\n");

        String[] lines = normalizedReceipt.split("\n");
        int ingredientLines = 0;
        for (String line : lines) {
            if (line.trim().startsWith("=") && line.contains("ingredient") && !line.contains("====")) {
                ingredientLines++;
            }
        }

        assertEquals("Количество строк с ингредиентами должно соответствовать параметру",
                ingredientCount, ingredientLines);
    }
}