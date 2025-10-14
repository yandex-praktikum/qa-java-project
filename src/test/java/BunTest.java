import praktikum.Bun;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тестирование класса Bun")
class BunTest {

    @ParameterizedTest(name = "Создание булочки: {0} за {1} рублей")
    @CsvSource({
            "Белая булочка, 100.0",
            "Черная булочка, 150.5",
            "Булочка с кунжутом, 200.0"
    })
    @DisplayName("Проверка корректного создания булочки и работы геттеров")
    @Description("Тест проверяет, что при создании булочки имя и цена сохраняются корректно, а методы getName() и getPrice() возвращают ожидаемые значения.")
    void testBunFieldsAndGetters(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName(), "Имя булочки должно совпадать с переданным значением");
        assertEquals(price, bun.getPrice(), 0.001, "Цена булочки должна совпадать с переданным значением");
    }
}
