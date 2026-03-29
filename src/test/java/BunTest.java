import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private static Stream<Arguments> bun() {
        return Stream.of(
                Arguments.of("Булочка с кунжутом", 490.99F),
                Arguments.of("Булочка черная", 300.00F),
                Arguments.of("Булочка бородинская", 342.00F)
        );
    }

    @ParameterizedTest
    @MethodSource("bun")
    void BunConstructorTest(String testName, float testPrice) {
        Bun bigBun = new Bun(testName, testPrice);
        assertEquals(testName, bigBun.getName(), "Название булочки не совпадает с переданным");
        assertEquals(testPrice, bigBun.getPrice(), "Цена булочки не совпадает с переданной");
    }
}
