package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки модели Burger.
 * Основная задача: убедиться, что бургер корректно собирается,
 * ингредиенты перемещаются/удаляются, а цена и чек формируются правильно.
 */
@DisplayName("Тестирование сборки и расчета бургера")
public class BurgerTest {

    private Burger burger;

    // Создаем моки для зависимостей, чтобы изолировать тест Burger от реальных Bun и Ingredient
    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    //Метод подготовки: выполняется перед каждым тестом
    @BeforeEach
    public void setUp() {
        // Инициализируем моки (аннотации @Mock)
        MockitoAnnotations.openMocks(this);
        // Создаем свежий объект бургера для каждого теста
        burger = new Burger();
    }

    @Test
    @DisplayName("Выбор булки: проверка сохранения объекта в поле bun")
    public void setBunsTest() {
        // Arrange: Устанавливаем булку
        burger.setBuns(mockBun);
        // Assert: Проверяем, что поле bun теперь ссылается на наш мок
        assertEquals(mockBun, burger.bun, "Булочка должна быть корректно сохранена в поле класса");
    }

    @Test
    @DisplayName("Работа со списком: добавление и удаление ингредиентов")
    public void ingredientsManagementTest() {
        // Act: Добавляем ингредиент
        burger.addIngredient(mockIngredient);
        // Проверяем, что в списке теперь 1 элемент
        assertEquals(1, burger.ingredients.size(), "Размер списка должен быть равен 1 после добавления");

        // Act: Удаляем ингредиент по индексу 0
        burger.removeIngredient(0);
        // Assert: Проверяем, что список стал пустым
        assertTrue(burger.ingredients.isEmpty(), "Список должен стать пустым после удаления единственного элемента");
    }

    @Test
    @DisplayName("Перемещение ингредиента: проверка корректной смены индекса в списке")
    public void moveIngredientTest() {
        // Arrange: Создаем второй мок ингредиента локально
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(mockIngredient);   // Изначально на индексе 0
        burger.addIngredient(secondIngredient); // Изначально на индексе 1

        // Act: Перемещаем первый ингредиент на позицию второго (с 0 на 1)
        burger.moveIngredient(0, 1);

        // Assert: Проверяем, что mockIngredient теперь стоит вторым в списке
        assertEquals(mockIngredient, burger.ingredients.get(1), "Ингредиент должен сменить свою позицию на новую");
    }

    /**
     * Параметризованный тест: проверяет расчет цены по формуле (Булка * 2) + Ингредиент.
     * @param bunPrice Тестовая цена булки
     * @param ingredientPrice Тестовая цена ингредиента
     * @param expected Ожидаемый результат
     */
    @ParameterizedTest(name = "Булка: {0}, Начинка: {1} -> Итог: {2}")
    @MethodSource("providePriceData")
    @DisplayName("Расчет цены: проверка математической формулы стоимости")
    public void getPriceTest(float bunPrice, float ingredientPrice, float expected) {
        // Arrange: Обучаем моки возвращать нужные цены
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);
        Mockito.when(mockIngredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        // Act & Assert: Сверяем результат с дельтой 0.0001 для точности float
        assertEquals(expected, burger.getPrice(), 0.0001f, "Математика расчета итоговой цены бургера неверна");
    }

    /**
     * Метод-источник данных для параметризованного теста цены.
     */
    private static Stream<Arguments> providePriceData() {
        return Stream.of(
                Arguments.of(100f, 50f, 250f), // (100 * 2) + 50 = 250
                Arguments.of(5.5f, 10f, 21f)    // (5.5 * 2) + 10 = 21
        );
    }

    @Test
    @DisplayName("Печать чека: проверка формата текста и итоговой суммы")
    public void getReceiptTest() {
        // 1. Arrange: Настройка данных (имена и цены)
        Mockito.when(mockBun.getName()).thenReturn("Булка");
        Mockito.when(mockBun.getPrice()).thenReturn(100f); // 100 * 2 = 200

        Mockito.when(mockIngredient.getName()).thenReturn("Соус");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50f); // Итого: 250

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        // 2. Act: Генерируем чек
        String receipt = burger.getReceipt();

        /**
         * Нормализация строки для исключения падений из-за системных настроек:
         * - Заменяем запятые на точки (решаем проблему локалей).
         * - Удаляем переносы строк (\r, \n), чтобы contains работал стабильно на Windows и Linux.
         */
        String cleanReceipt = receipt.replace(",", ".").replace("\r", "").replace("\n", "");

        // 3. Assert: Проверяем наличие всех ключевых элементов чека
        assertAll("Валидация содержимого чека",
                // Проверка названия булки в шапке
                () -> assertTrue(cleanReceipt.contains("(==== Булка ====)"),
                        "В чеке не найдено корректное название булки в шапке"),

                // Проверка типа (должен быть в нижнем регистре sauce) и названия
                () -> assertTrue(cleanReceipt.contains("= sauce Соус ="),
                        "Тип ингредиента должен быть приведен к нижнему регистру (sauce)"),

                /**
                 * Проверка цены: ищем вхождение "Price: 250".
                 * Игнорируем количество нулей после точки (250.000000), проверяя только основное число.
                 */
                () -> assertTrue(cleanReceipt.contains("Price: 250"),
                        "В чеке не найдена ожидаемая цена 250. Фактически текст чека: " + receipt)
        );
    }
}





