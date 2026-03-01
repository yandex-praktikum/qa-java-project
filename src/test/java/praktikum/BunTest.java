package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @ParameterizedTest
    @DisplayName("Проверка создания булок с разными названиями и ценами")
    @CsvSource({
            "Бесплатная булка, 0f",
            "Отрицательная булка c длиииииииииииннныыыыыымммм назвааааааниемммммммм, -988f",
            "Обычная булка, 600f",
            "Флюоресцентная булка R2-D3, 988f"
    })
    public void testBunsWithDifferentPricesAndNames(String name, float price) {
        Bun testBun = new Bun(name, price);
        assertAll("Проверка цены булки " + price + " и названия булки " + name,
                () -> assertEquals(name, testBun.getName()),
                () -> assertEquals(price, testBun.getPrice())
        );
    }
}