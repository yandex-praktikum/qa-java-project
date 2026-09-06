package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import io.qameta.allure.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @DisplayName("Получение названия и цены булочки")
    @Description("Проверяем, что методы getName() и getPrice() возвращают верные значения")
    @ParameterizedTest
    @CsvSource({
            "black bun 100.0",
            "white bun, 200.0",
            "red bun, 300.0"
    })
    void getNameAndPriceReturnsConstructorValues(String name, float price){
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice());
    }
}
