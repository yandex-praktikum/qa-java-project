package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    private static final String NAME = "Булочка тестовая";
    private static final float PRICE = 10.0f;
    private static final float DELTA = 0.0001f;

    private Bun bun;

    @BeforeEach
    void initBun() {
        bun = new Bun(NAME, PRICE);
    }

    @Test
    @DisplayName("Проверка метода getName()")
    void getNameTest() {
        assertEquals(NAME, bun.getName());
    }

    @Test
    @DisplayName("Проверка метода getPrice()")
    void getPriceTest() {
        assertEquals(PRICE, bun.getPrice(), DELTA);
    }

    @ParameterizedTest(name = "[{index}] name={0}, price={1}")
    @MethodSource("bunProvider")
    @DisplayName("Конструктор корректно сохраняет name и price в соответствующие поля")
    void constructorTest(String expectedName, float expectedPrice) {
        Bun bunActual = new Bun(expectedName, expectedPrice);
        assertEquals(expectedName, bunActual.name);
        assertEquals(expectedPrice, bunActual.price, DELTA);
    }

    static Stream<Arguments> bunProvider() {
        return Stream.of(
                Arguments.of("Булочка обычная", 100f),
                Arguments.of("Булочка дорогая", Float.MAX_VALUE),
                Arguments.of("Булочка почти бесплатная", Float.MIN_VALUE),
                Arguments.of("Булочка бесплатная", 0f),
                Arguments.of("", 100f),
                Arguments.of(null, 100f)
        );
    }

}
