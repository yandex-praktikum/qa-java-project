import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    @ParameterizedTest
    @MethodSource("bunData")
    public void bunConstructorNameTest(String bunName, int bunPrice) {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunName, bun.getName());
    }

    @ParameterizedTest
    @MethodSource("bunData")
    public void bunConstructorPriceTest(String bunName, int bunPrice) {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunPrice, bun.getPrice());
    }

    static Stream<Arguments> bunData() {

        return Stream.of(
                Arguments.of("Краторная булка N-200i", 1255),
                Arguments.of("Флюоресцентная булка R2-D3", 999)
        );
    }
}