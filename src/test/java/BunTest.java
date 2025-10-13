package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @ParameterizedTest
    @MethodSource("bunParameters")
    public void getNameTest(String name, float price) {
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        assertEquals(name, actual);
    }

    @ParameterizedTest
    @MethodSource("bunParameters")
    public void getPriceTest(String name, float price) {
        Bun bun = new Bun(name, price);
        float actual = bun.getPrice();
        assertEquals(price, actual);
    }

    private static Stream<Arguments> bunParameters() {
        return Stream.of(
                Arguments.of("black bun", 100.0f),
                Arguments.of("white bun", 200.0f),
                Arguments.of("red bun", 300.0f)
        );
    }
}