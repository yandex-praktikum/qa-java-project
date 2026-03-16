package praktikum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 Тестовый класс для проверки модели Bun (Булочка).
 Проверяет корректность работы конструктора и методов-геттеров.
 */
@DisplayName("Тестирование модели булочки (Bun)")
public class BunTest {/**
 Параметризованный тест: проверяет, что объект Bun корректно сохраняет
 и возвращает данные после инициализации.
 @param name Тестовое название
 @param price Тестовая цена
 */
@ParameterizedTest(name = "Кейс: {0} | Цена: {1}")
@MethodSource("provideBunData")
@DisplayName("Проверка конструктора и геттеров")
public void bunFullTest(String name, float price) {
// Arrange: Создание объекта с входными параметрами
    Bun bun = new Bun(name, price);// Act & Assert: Групповая проверка полей (name и price)
    assertAll("Проверка полей объекта Bun",
// Проверка возвращаемого имени
            () -> assertEquals(name, bun.getName(), "Имя должно соответствовать входному"),// Проверка цены с учетом точности float (дельта)
            () -> assertEquals(price, bun.getPrice(), 0.00001f, "Цена должна соответствовать входной")
    );
}
    /**
     Источник тестовых данных.
     @return Стрим с наборами аргументов (позитивные, негативные, граничные)
     */
    private static Stream provideBunData() {
        return Stream.of(
// Стандартные валидные данные
                Arguments.of("Флюоресцентная булка", 988.5f),
                Arguments.of("Краторная булка N-200i", 1255f),// Граничные случаи: пустое имя и нулевая цена
                Arguments.of("", 0f),// Негативные случаи: отсутствие ссылки (null) и отрицательная цена
                Arguments.of(null, -100.5f),// Экстремальные значения: спецсимволы и максимальное число
                Arguments.of("!@#$%^&*()", Float.MAX_VALUE),// Минимальные значения и пробелы
                Arguments.of(" ", Float.MIN_VALUE),// Проверка обработки длинных строк
                Arguments.of("Очень длинное название для булочки, которое может встретиться в меню", 0.01f)
        );
    }
}
