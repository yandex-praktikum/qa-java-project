package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BunTest {

    private static Stream<Arguments> bunTestData() {
        return Stream.of(
                Arguments.of("Краторная булка N-200i", 100.0f),
                Arguments.of("Флюоресцентная булка R2-D3", 200.5f),
                Arguments.of("", 0.0f),
                Arguments.of(null, -50.0f)
        );
    }

    @ParameterizedTest(name = "Тест булочки: имя={0}, цена={1}")
    @MethodSource("bunTestData")
    public void testBunGetters(String name, float price) {
        Bun bun = new Bun(name, price);
        Assertions.assertEquals(name, bun.getName());
        Assertions.assertEquals(price, bun.getPrice(), 0.0f);
    }
}
