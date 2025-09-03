package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BurgerReceiptTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauce;

    @Mock
    private Ingredient mockIngredientFilling;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        configureMocks();
    }

    @Test
    public void testGetReceiptReturnsFullCorrectReceipt() {
        // Arrange (Подготовка данных)
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);

        // Act (Вызов целевого метода)
        String actualReceipt = burger.getReceipt();

        // Assert (Проверка всего рецепта сразу с помощью регулярного выражения)
        // Проверяем основную структуру рецепта
        assertTrue("Receipt should contain bun lines",
                actualReceipt.contains("(==== white bun ====)"));
        assertTrue("Receipt should contain sauce ingredient",
                actualReceipt.contains("= sauce hot sauce ="));
        assertTrue("Receipt should contain filling ingredient",
                actualReceipt.contains("= filling cutlet ="));

        // Проверяем цену с помощью регулярного выражения, которое допускает и точку, и запятую
        assertTrue("Receipt should contain correct price format",
                Pattern.compile("Price: 280[.,]000000").matcher(actualReceipt).find());
    }

    @Test
    public void testGetReceiptCallsBunNameTwice() {
        burger.setBuns(mockBun);
        burger.getReceipt();
        verify(mockBun, times(2)).getName();
    }

    private void configureMocks() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientSauce.getName()).thenReturn("hot sauce");
        when(mockIngredientSauce.getPrice()).thenReturn(50f);

        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientFilling.getName()).thenReturn("cutlet");
        when(mockIngredientFilling.getPrice()).thenReturn(30f);
    }
}