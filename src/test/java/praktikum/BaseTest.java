package praktikum;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class BaseTest {

    // Общие моки для всех тестов
    @Mock
    protected Bun mockBun;
    @Mock
    protected Ingredient mockIngredient;
    @Mock
    protected Ingredient mockSauce;
    @Mock
    protected Ingredient mockFilling;

    // Общий объект бургера
    protected Burger burger;

    @Before
    public void setUp() {
        // Используем try-with-resources для автоматического закрытия моков
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {

            // Инициаляция общих объектов
            burger = new Burger();

            // Общие стабы для моков — фиксированные значения, чтобы тесты были предсказуемыми
            when(mockBun.getName()).thenReturn("Test Bun");
            when(mockBun.getPrice()).thenReturn(100f);
            when(mockIngredient.getName()).thenReturn("Test Ingredient");
            when(mockIngredient.getPrice()).thenReturn(50f);
            when(mockSauce.getName()).thenReturn("Test Sauce");
            when(mockSauce.getPrice()).thenReturn(50f);
            when(mockFilling.getName()).thenReturn("Test Filling");
            when(mockFilling.getPrice()).thenReturn(75f);
        } catch (Exception e) {
            // Обработка исключений для безопасности
            throw new RuntimeException("Failed to initialize mocks", e);
        }
    }
}