package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    private float bunPrice;
    private List<Float> ingredientPrices;
    private float expectedTotalPrice;

    public BurgerParameterizedTest(float bunPrice, List<Float> ingredientPrices, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, Arrays.asList(50f, 30f), 280f},
                {50f, Arrays.asList(20f), 120f},
                {80f, Arrays.asList(), 160f},
                {70f, Arrays.asList(10f, 15f, 25f), 190f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testGetPriceWithVariousParameters() {
        // Arrange (Подготовка данных)
        when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        for (float price : ingredientPrices) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(price);
            burger.addIngredient(mockIngredient);
        }

        // Act (Вызов целевого метода)
        float actualPrice = burger.getPrice();

        // Assert (Проверка РЕЗУЛЬТАТА вычислений)
        assertEquals("Total price is calculated incorrectly for bunPrice=" + bunPrice + " and ingredients=" + ingredientPrices, expectedTotalPrice, actualPrice, 0.001f);

        // Убираем все verify из этого теста
    }
}